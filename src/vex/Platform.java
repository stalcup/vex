package vex;

import jsinterop.annotations.JsType;
import vex.events.KeyEvent;
import vex.events.MouseEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;

@JsType
public interface Platform {

  @FunctionalInterface
  interface ResponseMessageHandler {
    void handleResponseMessage(String responseMessage);
  }

  int getHeight();

  int getWidth();

  Graphics getGraphics();

  KeyEvent getKeyEvent();

  MouseEvent getMouseEvent();

  Point getMouseLocation();

  void consumeMouseEvent();

  void consumeKeyEvent();

  void println(String line);

  void setUi(Runnable ui);

  void doAfterFrame(Runnable callback);

  void httpPost(String path, String body, ResponseMessageHandler responseMessageHandler);

  void httpGet(String path, ResponseMessageHandler responseMessageHandler);

  String getLocation();

  void setLocation(String location);

  void setTitle(String title);

  //  void setLocalStorage(String key, String value);
  //
  //  String getLocalStorage(String key);

  public static boolean mouseEventIs(Type type) {
    return Vex.platform.getMouseEvent() != null && Vex.platform.getMouseEvent().type == type;
  }

  public static boolean mouseEventIsIn(int x, int y, int width, int height, Type type) {
    return Vex.platform.getMouseEvent() != null
        && Vex.platform.getMouseEvent().type == type
        && mouseLocationIsIn(x, y, width, height);
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

  public static boolean mouseEventIsIn(Rect rect, Type type) {
    return mouseEventIsIn(rect.x, rect.y, rect.width, rect.height, type);
  }

  void beginLayer();

  void endLayer();

  void setCursor(Cursor cursor);

  int getFrameid();
}
