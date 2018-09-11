package vex.widgets;

public class WidgetStatus {

  public static WidgetStatus click(boolean clicked) {
    WidgetStatus status = new WidgetStatus();
    status.clicked = clicked;
    return status;
  }

  public static WidgetStatus text(boolean updated, String text, String keyText) {
    WidgetStatus status = new WidgetStatus();
    status.updated = updated;
    status.text = text;
    status.keyText = keyText;
    return status;
  }

  public boolean clicked;
  public String text;
  public String keyText;
  public boolean updated;
}
