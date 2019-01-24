package vex.widgets;

import java.util.List;

import vex.Rect;

public class WidgetStatus {

  public static WidgetStatus click(boolean clicked, boolean lostFocus) {
    WidgetStatus status = new WidgetStatus();
    status.clicked = clicked;
    status.lostFocus = lostFocus;
    return status;
  }

  public static WidgetStatus open(boolean open, List<Rect> rowRects) {
    WidgetStatus status = new WidgetStatus();
    status.open = open;
    status.rowRects = rowRects;
    return status;
  }

  public static WidgetStatus text(boolean updated, String text, String keyText, boolean lostFocus) {
    WidgetStatus status = new WidgetStatus();
    status.updated = updated;
    status.text = text;
    status.keyText = keyText;
    status.lostFocus = lostFocus;
    return status;
  }

  public static WidgetStatus selected(String selectedKey) {
    WidgetStatus status = new WidgetStatus();
    status.selectedKey = selectedKey;
    return status;
  }

  public static WidgetStatus time(boolean updated, long timeMs, boolean lostFocus) {
    WidgetStatus status = new WidgetStatus();
    status.updated = updated;
    status.timeMs = timeMs;
    status.lostFocus = lostFocus;
    return status;
  }

  public boolean open;
  public List<Rect> rowRects;
  public boolean clicked;
  public String text;
  public long timeMs;
  public String keyText;
  public boolean updated;
  public boolean lostFocus;
  public String selectedKey;
  public String selected;
}
