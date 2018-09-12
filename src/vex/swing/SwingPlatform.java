package vex.swing;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
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
  private Runnable ui;
  private JFrame window;

  public SwingPlatform(boolean fullScreen) {
    window = new JFrame();
    window.setState(Frame.MAXIMIZED_BOTH);

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
            g.drawImage(buffer, 0, 0, null);
          }
        };

    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.add(canvas);

    window.addComponentListener(
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

    window.addKeyListener(
        new SimpleKeyListener() {
          @Override
          public void keyPressed(java.awt.event.KeyEvent e) {
            keyEvents.addLast(
                new KeyEvent(
                    e.getKeyChar() + "",
                    java.awt.event.KeyEvent.getKeyText(e.getKeyCode()),
                    KeyEvent.Type.TYPE,
                    g.graphics.getFont().canDisplay(e.getKeyChar())));
            doFrame();
            doFrame();
          }
        });
  }

  @Override
  public Graphics getGraphics() {
    return g;
  }

  @Override
  public int getHeight() {
    return window.getRootPane().getHeight();
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
  public int getWidth() {
    return window.getRootPane().getWidth();
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

  private synchronized void doFrame() {
    if (ui == null) {
      return;
    }
    if (getWidth() == 0 || getHeight() == 0) {
      return;
    }

    startFrame();
    ui.run();
    endFrame();
  }

  private void endFrame() {
    window.getRootPane().getGraphics().drawImage(buffer, 0, 0, null);
    mouseEvent = null;
    keyEvent = null;
  }

  private void resizeBuffer() {
    buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
  }

  private void startFrame() {
    if (buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()) {
      resizeBuffer();
    }

    if (!mouseEvents.isEmpty()) {
      mouseEvent = mouseEvents.removeFirst();
      mouseLocation = getMouseEvent().point;
    }
    if (!keyEvents.isEmpty()) {
      keyEvent = keyEvents.removeFirst();
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
    if (g == null) {
      g = new SwingGraphics(swingGraphics);
    } else {
      g.graphics = swingGraphics;
    }
  }
}
