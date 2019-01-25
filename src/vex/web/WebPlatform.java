package vex.web;

import java.util.LinkedList;

import elemental2.dom.CanvasRenderingContext2D;
import elemental2.dom.DomGlobal;
import elemental2.dom.Event;
import elemental2.dom.HTMLCanvasElement;
import elemental2.dom.KeyboardEvent;
import vex.Cursor;
import vex.Graphics;
import vex.Platform;
import vex.events.KeyEvent;
import vex.events.MouseEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;

public class WebPlatform implements Platform {

  private HTMLCanvasElement canvasElement;
  private CanvasRenderingContext2D context2d;
  private Graphics graphics;
  private KeyEvent keyEvent;
  private LinkedList<KeyEvent> keyEvents = new LinkedList<>();
  private MouseEvent mouseEvent;
  private LinkedList<MouseEvent> mouseEvents = new LinkedList<>();
  private Point mouseLocation = new Point(0, 0);
  private Runnable ui;

  public WebPlatform(HTMLCanvasElement canvasElement) {
    this.canvasElement = canvasElement;
    context2d = (CanvasRenderingContext2D) (Object) canvasElement.getContext("2d");
    graphics = new WebGraphics(context2d);

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

  @Override
  public int getHeight() {
    return canvasElement.clientHeight;
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
    return canvasElement.clientWidth;
  }

  @Override
  public void println(String line) {
    DomGlobal.console.log(line);
  }

  @Override
  public void setUi(Runnable ui) {
    this.ui = ui;
    doFrame();
  }

  private void addMouseEvent(Event e, Type type) {
    elemental2.dom.MouseEvent mouseEvent = (elemental2.dom.MouseEvent) e;
    Point point = new Point((int) mouseEvent.clientX, (int) mouseEvent.clientY);
    mouseEvents.addLast(new MouseEvent(point, type, Point.createDelta(getMouseLocation(), point)));
    doFrame();
    doFrame();
  }

  private synchronized void doFrame() {
    startFrame();
    ui.run();
    endFrame();
  }

  private void endFrame() {
    consumeMouseEvent();
    keyEvent = null;
  }

  @Override
  public void consumeMouseEvent() {
    mouseEvent = null;
  }

  private void fitCanvasToWindow() {
    canvasElement.height = DomGlobal.window.innerHeight;
    canvasElement.width = DomGlobal.window.innerWidth;
  }

  private void startFrame() {
    if (!mouseEvents.isEmpty()) {
      mouseEvent = mouseEvents.removeFirst();
      mouseLocation = getMouseEvent().point;
    }
    if (!keyEvents.isEmpty()) {
      keyEvent = keyEvents.removeFirst();
    }
  }

  @Override
  public void httpPost(
      String path, String requestMessage, ResponseMessageHandler responseMessageHandler) {}

  @Override
  public void httpGet(String path, ResponseMessageHandler responseMessageHandler) {}

  @Override
  public void consumeKeyEvent() {}

  @Override
  public String getLocation() {
    return null;
  }

  @Override
  public void setLocation(String location) {}

  @Override
  public void setTitle(String title) {}

  @Override
  public void beginLayer() {}

  @Override
  public void endLayer() {}

  @Override
  public void doAfterFrame(Runnable callback) {}

  @Override
  public int getFrameid() {
    return 0;
  }

  @Override
  public void setCursor(Cursor cursor) {}
}
