package vex.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.common.base.Preconditions;

import fi.iki.elonen.NanoHTTPD.Response.Status;
import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import vex.Cursor;
import vex.Graphics;
import vex.Platform;
import vex.Vex;
import vex.events.KeyEvent;
import vex.events.MouseEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;
import vex.swing.util.SimpleComponentListener;
import vex.swing.util.SimpleKeyListener;
import vex.swing.util.SimpleMouseListener;

public class SwingPlatform implements Platform {

  private BufferedImage baseBuffer;
  private BufferedImage currentBuffer;
  private LinkedList<BufferedImage> bufferLayers = new LinkedList<>();

  private JPanel canvas;
  private String location = "";
  private Graphics g;
  private KeyEvent keyEvent;
  private LinkedList<KeyEvent> keyEvents = new LinkedList<>();
  private MouseEvent mouseEvent;
  private LinkedList<MouseEvent> mouseEvents = new LinkedList<>();
  private Point mouseLocation = new Point(0, 0);
  private Runnable ui;
  private JFrame window;

  private HttpBrowser httpBrowser = new HttpBrowser();

  public SwingPlatform(boolean fullScreen) {
    window = new JFrame();

    try {
      window.setIconImage(ImageIO.read(this.getClass().getResource("icon.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    if (fullScreen) {
      window.setUndecorated(true);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      window.setLocation(0, 0);
      window.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
    } else {
      window.setSize(1400, 900);
      window.setLocationRelativeTo(null);
    }
    window.setVisible(true);

    canvas =
        new JPanel() {
          @Override
          public void paint(java.awt.Graphics g) {
            g.drawImage(getFrontBuffer(), 0, 0, null);
          }
        };

    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.add(canvas);

    window.addComponentListener(
        new SimpleComponentListener() {
          @Override
          public void componentResized(ComponentEvent e) {
            doFrame();
          }
        });

    Component mouseMotionTarget = fullScreen ? window : canvas;
    mouseMotionTarget.addMouseMotionListener(
        new MouseMotionListener() {
          @Override
          public void mouseDragged(java.awt.event.MouseEvent e) {
            addMouseEvent(e, Type.DRAG);
          }

          @Override
          public void mouseMoved(java.awt.event.MouseEvent e) {
            addMouseEvent(e, Type.MOVE);
          }
        });

    mouseMotionTarget.addMouseListener(
        new SimpleMouseListener() {
          @Override
          public void mouseClicked(java.awt.event.MouseEvent e) {
            addMouseEvent(e, Type.CLICK);
          }

          @Override
          public void mouseEntered(java.awt.event.MouseEvent e) {
            addMouseEvent(e, Type.MOVE);
          }

          @Override
          public void mousePressed(java.awt.event.MouseEvent e) {
            addMouseEvent(e, Type.DOWN);
          }

          @Override
          public void mouseReleased(java.awt.event.MouseEvent e) {
            addMouseEvent(e, Type.UP);
          }
        });

    // Make sure key listeners see Tab and Shift key presses.
    window.setFocusTraversalKeysEnabled(false);

    window.addKeyListener(
        new SimpleKeyListener() {
          @Override
          public void keyPressed(java.awt.event.KeyEvent e) {
            String keyText = java.awt.event.KeyEvent.getKeyText(e.getKeyCode());
            boolean isDelete = e.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE;
            boolean isBackspace = e.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE;
            boolean printable = g.canDisplay(e.getKeyChar()) && !isDelete && !isBackspace;
            keyEvents.addLast(
                new KeyEvent(e.getKeyChar() + "", keyText, KeyEvent.Type.TYPE, printable));
            doFrame();
            doFrame();
          }
        });

    Thread httpProcessingQueue =
        new Thread() {
          @Override
          public void run() {
            while (true) {
              try {
                queuedHttpRequests.takeFirst().run();
              } catch (InterruptedException e) {
              }
            }
          }
        };
    httpProcessingQueue.start();
  }

  @Override
  public int getHeight() {
    return canvas.getHeight();
  }

  @Override
  public KeyEvent getKeyEvent() {
    return keyEvent;
  }

  @Override
  public MouseEvent getMouseEvent() {
    if (currentLayerIndex != interactiveLayerIndex) {
      return null;
    }

    return mouseEvent;
  }

  @Override
  public Point getMouseLocation() {
    return mouseLocation;
  }

  @Override
  public int getWidth() {
    return canvas.getWidth();
  }

  @Override
  public void println(String line) {
    System.out.println(line);
  }

  @Override
  public void setUi(Runnable ui) {
    this.ui = ui;
    doFrame();
  }

  private void addMouseEvent(java.awt.event.MouseEvent e, Type type) {
    Point point = new Point(e.getPoint().x, e.getPoint().y);
    mouseEvents.addLast(new MouseEvent(point, type, Point.createDelta(getMouseLocation(), point)));
    doFrame();
    doFrame();
  }

  private int frameId = 0;

  private void doFrame() {
    synchronized (theWebIsSingleThreaded) {
      if (ui == null) {
        return;
      }
      if (getWidth() == 0 || getHeight() == 0) {
        return;
      }

      startFrame();
      frameId++;
      ui.run();
      endFrame();
    }
  }

  private boolean bufferAIsFront = true;
  private BufferedImage compositeBufferA;
  private BufferedImage compositeBufferB;

  private BufferedImage getBackBuffer() {
    return bufferAIsFront ? compositeBufferB : compositeBufferA;
  }

  protected Image getFrontBuffer() {
    return bufferAIsFront ? compositeBufferA : compositeBufferB;
  }

  private void endFrame() {
    compositeBuffers();
    flipBuffers();

    canvas.repaint();

    consumeMouseEvent();
    consumeKeyEvent();
    applyCursor();

    for (Runnable afterFrameCallback : afterFrameCallbacks) {
      afterFrameCallback.run();
    }
    afterFrameCallbacks.clear();
  }

  private void flipBuffers() {
    bufferAIsFront = !bufferAIsFront;
  }

  private void applyCursor() {
    if (previousCursor == currentCursor) {
      setCursor(Cursor.DEFAULT);
      return;
    }

    int swingCursorId = 0;
    switch (currentCursor) {
      case CROSSHAIR:
        swingCursorId = java.awt.Cursor.CROSSHAIR_CURSOR;
        break;
      case DEFAULT:
        swingCursorId = java.awt.Cursor.DEFAULT_CURSOR;
        break;
      case E_RESIZE:
        swingCursorId = java.awt.Cursor.E_RESIZE_CURSOR;
        break;
      case HAND:
        swingCursorId = java.awt.Cursor.HAND_CURSOR;
        break;
      case MOVE:
        swingCursorId = java.awt.Cursor.MOVE_CURSOR;
        break;
      case N_RESIZE:
        swingCursorId = java.awt.Cursor.N_RESIZE_CURSOR;
        break;
      case NE_RESIZE:
        swingCursorId = java.awt.Cursor.NE_RESIZE_CURSOR;
        break;
      case NW_RESIZE:
        swingCursorId = java.awt.Cursor.NW_RESIZE_CURSOR;
        break;
      case S_RESIZE:
        swingCursorId = java.awt.Cursor.S_RESIZE_CURSOR;
        break;
      case SE_RESIZE:
        swingCursorId = java.awt.Cursor.SE_RESIZE_CURSOR;
        break;
      case SW_RESIZE:
        swingCursorId = java.awt.Cursor.SW_RESIZE_CURSOR;
        break;
      case TEXT:
        swingCursorId = java.awt.Cursor.TEXT_CURSOR;
        break;
      case W_RESIZE:
        swingCursorId = java.awt.Cursor.W_RESIZE_CURSOR;
        break;
      case WAIT:
        swingCursorId = java.awt.Cursor.WAIT_CURSOR;
        break;
      default:
        swingCursorId = java.awt.Cursor.DEFAULT_CURSOR;
        break;
    }

    canvas.setCursor(java.awt.Cursor.getPredefinedCursor(swingCursorId));
    previousCursor = currentCursor;
    setCursor(Cursor.DEFAULT);
  }

  @Override
  public void consumeMouseEvent() {
    mouseEvent = null;
  }

  @Override
  public void consumeKeyEvent() {
    keyEvent = null;
  }

  private void startFrame() {
    bufferLayers.clear();
    currentLayerIndex = 0;
    interactiveLayerIndex = highestLayerIndex;
    highestLayerIndex = 0;

    beginLayer();

    if (!mouseEvents.isEmpty()) {
      mouseEvent = mouseEvents.removeFirst();
      mouseLocation = mouseEvent.point;
    }
    if (!keyEvents.isEmpty()) {
      keyEvent = keyEvents.removeFirst();
    }
  }

  private BlockingDeque<Runnable> queuedHttpRequests = new LinkedBlockingDeque<>();
  private int currentLayerIndex;
  private int highestLayerIndex;
  private int interactiveLayerIndex;

  private Cursor previousCursor;
  private Cursor currentCursor;

  private List<Runnable> afterFrameCallbacks = new ArrayList<>();

  private Object theWebIsSingleThreaded = new Object();

  @Override
  public void httpGet(String path, ResponseMessageHandler responseMessageHandler) {
    if (!path.contains("://")) {
      if (!path.startsWith("/")) {
        path = "/" + path;
      }
      path = "http://localhost:80" + path;
    }
    final String absolutePath = path;

    queuedHttpRequests.add(
        () -> {
          HttpResponse httpResponse = httpBrowser.sendRequest(HttpRequest.get(absolutePath));

          if (httpResponse.statusCode() != Status.OK.getRequestStatus()) {
            System.err.println(
                "requested " + absolutePath + " but got status code " + httpResponse.statusCode());
            return;
          }

          synchronized (theWebIsSingleThreaded) {
            responseMessageHandler.handleResponseMessage(httpResponse.bodyText());
          }
          doFrame();
        });
  }

  @Override
  public void httpPost(String path, String body, ResponseMessageHandler responseMessageHandler) {
    if (!path.contains("://")) {
      if (!path.startsWith("/")) {
        path = "/" + path;
      }
      path = "http://localhost" + path;
    }
    final String absolutePath = path;

    queuedHttpRequests.add(
        () -> {
          HttpResponse httpResponse = httpBrowser.sendRequest(HttpRequest.post(absolutePath));

          if (httpResponse.statusCode() != Status.OK.getRequestStatus()) {
            System.err.println(
                "requested " + absolutePath + " but got status code " + httpResponse.statusCode());
            return;
          }

          responseMessageHandler.handleResponseMessage(httpResponse.bodyText());
          doFrame();
        });
  }

  @Override
  public String getLocation() {
    return location;
  }

  @Override
  public void setLocation(String location) {
    Preconditions.checkArgument(location != null);

    System.out.println("client going to " + location);

    this.location = location;
  }

  @Override
  public void setTitle(String title) {
    window.setTitle(title);
  }

  @Override
  public void beginLayer() {
    if (bufferLayers.size() <= currentLayerIndex) {
      if (baseBuffer == null
          || baseBuffer.getWidth() != getWidth()
          || baseBuffer.getHeight() != getHeight()) {
        baseBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        compositeBufferA = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        compositeBufferB = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
      }

      if (bufferLayers.isEmpty()) {
        currentBuffer = baseBuffer;
      } else {
        currentBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
      }

      Graphics2D swingGraphics = (Graphics2D) currentBuffer.getGraphics();
      swingGraphics.setRenderingHint(
          RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      swingGraphics.setRenderingHint(
          RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      swingGraphics.setRenderingHint(
          RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      swingGraphics.setRenderingHint(
          RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
      swingGraphics.setRenderingHint(
          RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
      swingGraphics.setRenderingHint(
          RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      g = new SwingGraphics(swingGraphics);
      Vex.graphics = g;

      bufferLayers.addLast(currentBuffer);
    }

    currentLayerIndex++;
    highestLayerIndex = Math.max(highestLayerIndex, currentLayerIndex);
  }

  @Override
  public void endLayer() {
    Preconditions.checkState(currentLayerIndex > 0);

    currentLayerIndex--;
    currentBuffer = bufferLayers.get(currentLayerIndex - 1);

    Graphics2D swingGraphics = (Graphics2D) currentBuffer.getGraphics();
    swingGraphics.setRenderingHint(
        RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    swingGraphics.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    swingGraphics.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    swingGraphics.setRenderingHint(
        RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    swingGraphics.setRenderingHint(
        RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    swingGraphics.setRenderingHint(
        RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g = new SwingGraphics(swingGraphics);
    Vex.graphics = g;
  }

  private void compositeBuffers() {
    BufferedImage compositeBuffer = null;
    for (BufferedImage thisBuffer : bufferLayers) {
      if (compositeBuffer == null) {
        compositeBuffer = thisBuffer;
      } else {
        compositeBuffer.getGraphics().drawImage(thisBuffer, 0, 0, null);
      }
    }

    BufferedImage a = getBackBuffer();
    java.awt.Graphics b = a.getGraphics();
    b.drawImage(compositeBuffer, 0, 0, null);
  }

  @Override
  public void doAfterFrame(Runnable callback) {
    afterFrameCallbacks.add(callback);
  }

  @Override
  public void setCursor(Cursor currentCursor) {
    this.currentCursor = currentCursor;
  }

  @Override
  public int getFrameid() {
    return frameId;
  }
}
