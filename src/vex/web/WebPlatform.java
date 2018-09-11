package vex.web;

import java.util.LinkedList;

import elemental2.dom.CanvasRenderingContext2D;
import elemental2.dom.DomGlobal;
import elemental2.dom.Event;
import elemental2.dom.HTMLCanvasElement;
import elemental2.dom.KeyboardEvent;
import vex.Graphics;
import vex.Platform;
import vex.events.KeyEvent;
import vex.events.MouseEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;

public class WebPlatform implements Platform {

  private HTMLCanvasElement canvasElement;
  private CanvasRenderingContext2D context2d;
  private Graphics g;
  private int textCursorPosition;
  private Runnable ui;
  private KeyEvent keyEvent;
  private LinkedList<KeyEvent> keyEvents = new LinkedList<>();
  private MouseEvent mouseEvent;
  private LinkedList<MouseEvent> mouseEvents = new LinkedList<>();
  private Point mouseLocation = new Point(0, 0);

  public WebPlatform(HTMLCanvasElement canvasElement) {
    this.canvasElement = canvasElement;
    context2d = (CanvasRenderingContext2D) (Object) canvasElement.getContext("2d");
    g = new WebGraphics(context2d);

    fitCanvasToWindow();

    DomGlobal.setTimeout((o) -> doFrame(), 1000);
    DomGlobal.document.addEventListener(
        "keydown",
        (e) -> {
          KeyboardEvent keyboardEvent = (KeyboardEvent) e;
          keyEvents.addLast(
              new KeyEvent(
                  keyboardEvent.key,
                  keyboardEvent.code,
                  KeyEvent.Type.TYPE,
                  keyboardEvent.key.length() == 1));
          doFrame();
          doFrame();
        });
    DomGlobal.window.addEventListener(
        "resize",
        (e) -> {
          fitCanvasToWindow();
          doFrame();
        });
    canvasElement.addEventListener("mousedown", (e) -> addMouseEvent(e, Type.DOWN));
    canvasElement.addEventListener("mouseup", (e) -> addMouseEvent(e, Type.UP));
    canvasElement.addEventListener(
        "mousemove",
        (e) -> {
          elemental2.dom.MouseEvent mouseEvent = (elemental2.dom.MouseEvent) e;
          addMouseEvent(e, mouseEvent.buttons > 0 ? Type.DRAG : Type.MOVE);
        });
  }

  private void addMouseEvent(Event e, Type type) {
    elemental2.dom.MouseEvent mouseEvent = (elemental2.dom.MouseEvent) e;
    Point point = new Point((int) mouseEvent.clientX, (int) mouseEvent.clientY);
    mouseEvents.addLast(new MouseEvent(point, type, Point.createDelta(getMouseLocation(), point)));
    doFrame();
    doFrame();
  }

  private void fitCanvasToWindow() {
    this.canvasElement.height = DomGlobal.window.innerHeight;
    this.canvasElement.width = DomGlobal.window.innerWidth;
  }

  @Override
  public int getHeight() {
    return canvasElement.clientHeight;
  }

  @Override
  public int getWidth() {
    return canvasElement.clientWidth;
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

  private synchronized void doFrame() {
    this.startFrame();
    this.ui.run();
    this.endFrame();
  }

  private void endFrame() {
    this.mouseEvent = null;
    this.keyEvent = null;
  }

  private void startFrame() {
    if (!mouseEvents.isEmpty()) {
      this.mouseEvent = mouseEvents.removeFirst();
      this.mouseLocation = getMouseEvent().point;
    }
    if (!keyEvents.isEmpty()) {
      this.keyEvent = keyEvents.removeFirst();
    }
  }
}
