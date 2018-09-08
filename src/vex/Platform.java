package vex;

import vex.events.KeyEvent;
import vex.events.MouseEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;

public interface Platform {

  int getHeight();

  int getWidth();

  Graphics getGraphics();

  KeyEvent getKeyEvent();

  MouseEvent getMouseEvent();

  Point getMouseLocation();

  int getTextCursorPosition();

  void setTextCursorPosition(int textCursorPosition);

  void setUi(Runnable ui);

  static boolean mouseEventIs(Type type) {
    return Vex.platform.getMouseEvent() != null && Vex.platform.getMouseEvent().type == type;
  }

  static boolean mouseEventIsIn(int x, int y, int width, int height, Type type) {
    return Vex.platform.getMouseEvent() != null
        && Vex.platform.getMouseEvent().type == type
        && mouseLocationIsIn(x, y, width, height);
  }

  public static boolean mouseLocationIsIn(int x, int y, int width, int height) {
    Point point = Vex.platform.getMouseLocation();
    return point != null
        && point.x >= x
        && point.y >= y
        && point.x <= x + width
        && point.y <= y + height;
  }
}
