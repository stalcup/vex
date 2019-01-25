package vex;

import jsinterop.annotations.JsType;
import vex.events.KeyEvent;
import vex.events.MouseEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;
import vex.geom.Rect;

@JsType
public interface Platform {

  @FunctionalInterface
  interface ResponseMessageHandler {
    void handleResponseMessage(String responseMessage);
  }

  public static boolean mouseEventIs(Type type) {
    return Vex.platform.getMouseEvent() != null && Vex.platform.getMouseEvent().type == type;
  }

  public static boolean mouseEventIsIn(int x, int y, int width, int height, Type type) {
    return Vex.platform.getMouseEvent() != null
        && Vex.platform.getMouseEvent().type == type
        && mouseLocationIsIn(x, y, width, height);
  }

  public static boolean mouseEventIsIn(Rect rect, Type type) {
    return mouseEventIsIn(rect.x, rect.y, rect.width, rect.height, type);
  }

  public static boolean mouseLocationIsIn(int x, int y, int width, int height) {
    Point point = Vex.platform.getMouseLocation();
    return point != null
        && point.x >= x
        && point.y >= y
        && point.x < x + width
        && point.y < y + height;
  }

  public static boolean mouseLocationIsIn(Rect rect) {
    return mouseLocationIsIn(rect.x, rect.y, rect.width, rect.height);
  }

  void beginLayer();

  void consumeKeyEvent();

  void consumeMouseEvent();

  void doAfterFrame(Runnable callback);

  void endLayer();

  int getFrameid();

  int getHeight();

  KeyEvent getKeyEvent();

  String getLocation();

  MouseEvent getMouseEvent();

  Point getMouseLocation();

  int getWidth();

  void httpGet(String path, ResponseMessageHandler responseMessageHandler);

  void httpPost(String path, String body, ResponseMessageHandler responseMessageHandler);

  void println(String line);

  void setCursor(Cursor cursor);

  void setLocation(String location);

  void setTitle(String title);

  void setUi(Runnable ui);
}
