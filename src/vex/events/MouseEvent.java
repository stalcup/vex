package vex.events;

import vex.geom.Point;

public class MouseEvent {
  public static enum Type {
    UP,
    DOWN,
    CLICK,
    MOVE,
    DRAG
  }

  public Point point;
  public Point delta;
  public Type type;

  public MouseEvent(Point point, Type type, Point delta) {
    this.point = point;
    this.type = type;
    this.delta = delta;
  }
}
