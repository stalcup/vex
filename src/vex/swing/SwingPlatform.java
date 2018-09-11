package vex.swing;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import vex.Graphics;
import vex.Platform;
import vex.events.KeyEvent;
import vex.events.MouseEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;
import vex.swing.util.SimpleComponentListener;
import vex.swing.util.SimpleKeyListener;
import vex.swing.util.SimpleMouseListener;

@SuppressWarnings("serial")
public class SwingPlatform implements Platform {

  private BufferedImage buffer;
  private JPanel canvas;
  private SwingGraphics g;
  private KeyEvent keyEvent;
  private LinkedList<KeyEvent> keyEvents = new LinkedList<>();
  private MouseEvent mouseEvent;
  private LinkedList<MouseEvent> mouseEvents = new LinkedList<>();
  private Point mouseLocation = new Point(0, 0);
  private int textCursorPosition;
  private Runnable ui;
  private JFrame window;

  {
    this.window = new JFrame();
    this.window.setState(JFrame.MAXIMIZED_BOTH);

    // fullscreen for aligned comparison with web version
    // this.window.setUndecorated(true);
    // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // this.window.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());

    this.window.setLocation(0, 0);
    this.window.setSize(1400, 900);
    this.window.setVisible(true);

    this.canvas =
        new JPanel() {
          @Override
          public void paint(java.awt.Graphics g) {
            g.drawImage(buffer, 0, 0, null);
          }
        };

    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.window.add(this.canvas);
    //    this.window.setSize(1400, 900);

    this.window.addComponentListener(
        new SimpleComponentListener() {
          @Override
          public void componentResized(ComponentEvent e) {
            resizeBuffer();
            doFrame();
          }
        });

    canvas.addMouseMotionListener(
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

    canvas.addMouseListener(
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

    this.window.addKeyListener(
        new SimpleKeyListener() {
          @Override
          public void keyPressed(java.awt.event.KeyEvent e) {
            keyEvents.addLast(
                new KeyEvent(
                    e.getKeyChar() + "",
                    java.awt.event.KeyEvent.getKeyText(e.getKeyCode()),
                    KeyEvent.Type.TYPE,
                    g.g.getFont().canDisplay(e.getKeyChar())));
            doFrame();
            doFrame();
          }
        });
  }

  @Override
  public int getHeight() {
    return this.window.getRootPane().getHeight();
  }

  @Override
  public int getWidth() {
    return this.window.getRootPane().getWidth();
  }

  @Override
  public Graphics getGraphics() {
    return g;
  }

  @Override
  public KeyEvent getKeyEvent() {
    return keyEvent;
  }

  @Override
  public MouseEvent getMouseEvent() {
    return mouseEvent;
  }

  @Override
  public Point getMouseLocation() {
    return mouseLocation;
  }

  @Override
  public int getTextCursorPosition() {
    return textCursorPosition;
  }

  @Override
  public void setTextCursorPosition(int textCursorPosition) {
    this.textCursorPosition = textCursorPosition;
  }

  @Override
  public void setUi(Runnable ui) {
    this.ui = ui;
    doFrame();
  }

  private void resizeBuffer() {
    this.buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
  }

  private synchronized void doFrame() {
    if (this.ui == null) {
      return;
    }
    if (getWidth() == 0 || getHeight() == 0) {
      return;
    }

    this.startFrame();
    this.ui.run();
    this.endFrame();
  }

  private void endFrame() {
    this.window.getRootPane().getGraphics().drawImage(buffer, 0, 0, null);
    this.mouseEvent = null;
    this.keyEvent = null;
  }

  private void startFrame() {
    if (buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()) {
      resizeBuffer();
    }

    if (!mouseEvents.isEmpty()) {
      this.mouseEvent = mouseEvents.removeFirst();
      this.mouseLocation = getMouseEvent().point;
    }
    if (!keyEvents.isEmpty()) {
      this.keyEvent = keyEvents.removeFirst();
    }

    Graphics2D swingGraphics = (Graphics2D) buffer.getGraphics();
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
    if (this.g == null) {
      this.g = new SwingGraphics(swingGraphics);
    } else {
      this.g.g = swingGraphics;
    }
  }

  private void addMouseEvent(java.awt.event.MouseEvent e, Type type) {
    Point point = new Point(e.getPoint().x, e.getPoint().y);
    mouseEvents.addLast(new MouseEvent(point, type, Point.createDelta(getMouseLocation(), point)));
    doFrame();
    doFrame();
  }
}
