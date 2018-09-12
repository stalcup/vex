package vex;

import jsinterop.annotations.JsType;
import vex.events.KeyEvent;
import vex.events.MouseEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;

@JsType
public interface Platform {

  int getHeight();

  int getWidth();

  Graphics getGraphics();

  KeyEvent getKeyEvent();

  MouseEvent getMouseEvent();

  Point getMouseLocation();

  void println(String line);

  void setUi(Runnable ui);

  static boolean mouseEventIs(Type type) {
    return Vex.platform.getMouseEvent() != null && Vex.platform.getMouseEvent().type == type;
  }

  static boolean mouseEventIsIn(int x, int y, int width, int height, Type type) {
    return Vex.platform.getMouseEvent() != null
        && Vex.platform.getMouseEvent().type == type
        && mouseLocationIsIn(x, y, width, height);
  }

  static boolean mouseLocationIsIn(int x, int y, int width, int height) {
    Point point = Vex.platform.getMouseLocation();
    return point != null
        && point.x >= x
        && point.y >= y
        && point.x < x + width
        && point.y < y + height;
  }
}
