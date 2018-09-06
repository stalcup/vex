package vex;

import vex.Graphics.FontStyle;
import vex.events.KeyEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;

public class Widgets {

  public static class WidgetStatus {
    static WidgetStatus click(boolean clicked) {
      WidgetStatus status = new WidgetStatus();
      status.clicked = clicked;
      return status;
    }

    static WidgetStatus text(boolean updated, String text) {
      WidgetStatus status = new WidgetStatus();
      status.updated = updated;
      status.text = text;
      return status;
    }

    public boolean clicked;

    public String text;

    public boolean updated;
  }

  public static String currentFocusId;

  public static WidgetStatus doButton(
      int x,
      int y,
      int width,
      int height,
      String title,
      Color textColor,
      Color backgroundColor,
      Color mouseOverColor,
      Color selectedColor) {

    if (backgroundColor != null) {
      g().setColor(backgroundColor);
      g().fillRect(x, y, width, height);
    }

    if (mouseOverColor != null && Platform.mouseLocationIsIn(x, y, width, height)) {
      g().setColor(mouseOverColor);
      g().fillRect(x, y, width, height);
    }

    if (selectedColor != null) {
      g().setColor(selectedColor);
      g().fillRect(x, y, width, height);
    }

    if (title != null) {
      g().setColor(textColor);
      Widgets.renderStringCentered(x, y, width, height, title);
    }

    return WidgetStatus.click(Platform.mouseEventIsIn(x, y, width, height, Type.DOWN));
  }

  public static float doVerticalScrollBar(
      String focusId,
      int x,
      int y,
      int width,
      int height,
      int visibleRange,
      int totalRange,
      float scrollPercent,
      Color backgroundColor,
      Color barColor) {
    scrollPercent = Math.max(0, scrollPercent);
    scrollPercent = Math.min(100, scrollPercent);

    int visiblePixels = (int) (height * visibleRange / (float) totalRange);
    int hiddenPixels = height - visiblePixels;
    int offsetPixels = (int) (hiddenPixels * scrollPercent / 100f);

    if (backgroundColor != null) {
      g().setColor(backgroundColor);
      g().fillRect(x, y, width, height);
    }

    g().setColor(barColor);
    g().fillRect(x, y + offsetPixels, width, visiblePixels);

    if (Platform.mouseLocationIsIn(x, y, width, height) || getCurrentFocusId() == focusId) {
      g().fillRect(x, y + offsetPixels, width, visiblePixels);
    }

    if (Platform.mouseEventIsIn(x, y, width, height, Type.DOWN)) {
      setCurrentFocusId(focusId);
      // Aka, start dragging.
    }

    if (getCurrentFocusId() == focusId
        && Vex.platform.getMouseEvent() != null
        && Vex.platform.getMouseEvent().type == Type.DRAG) {
      scrollPercent += Vex.platform.getMouseEvent().delta.y * 100f / hiddenPixels;

      scrollPercent = Math.max(0, scrollPercent);
      scrollPercent = Math.min(100, scrollPercent);
    }

    if (getCurrentFocusId() == focusId && Platform.mouseEventIs(Type.UP)) {
      setCurrentFocusId(null);
    }

    return scrollPercent;
  }

  public static String getCurrentFocusId() {
    return currentFocusId;
  }

  public static Point getStringSize(String text) {
    return g().getSize(text);
  }

  public static void renderInnerBorder(int x, int y, int width, int height, int border) {
    g().setStroke(border);
    g().setColor(Color.GRAY);
    g().drawRect(x, y, width - border, height - border);
  }

  public static void renderRect(int x, int y, int width, int height, Color color) {
    g().setColor(color);
    g().fillRect(x, y, width, height);
  }

  public static void renderStringCentered(int x, int y, int width, int height, String text) {
    Point stringSize = Widgets.getStringSize(text);
    g().drawString(text, x + width / 2 - stringSize.x / 2, y + height / 2 + stringSize.y / 2);
  }

  public static void renderStringLeft(int x, int y, int width, int height, String text) {
    Point stringSize = Widgets.getStringSize(text);
    g().drawString(text, x, y + height / 2 + stringSize.y / 2);
  }

  public static void renderStringRight(int x, int y, int width, int height, String text) {
    g().setColor(Color.BLACK);
    Point stringSize = Widgets.getStringSize(text);
    g().drawString(text, x + width - stringSize.x, y + height / 2 + stringSize.y / 2);
  }

  public static WidgetStatus renderTextBox(
      String focusId,
      int x,
      int y,
      int width,
      int height,
      String text,
      Color underlineColor,
      Color textColor) {
    Vex.platform.pushClip(x, y, width, height);

    if (Platform.mouseEventIsIn(x, y, width, height, Type.DOWN)) {
      setCurrentFocusId(focusId);
      Vex.platform.setTextCursorPosition(text.length());
    }

    int margin = 3;

    boolean focused = getCurrentFocusId() == focusId;

    if (underlineColor != null) {
      g().setColor(underlineColor);
      g().fillRect(x, y + height - 1, width, 1);
    }

    boolean updatedText = false;

    if (focused) {
      KeyEvent keyEvent = Vex.platform.getKeyEvent();
      if (keyEvent != null) {
        if (keyEvent.keyText.equals("Left")) {
          Vex.platform.setTextCursorPosition(Vex.platform.getTextCursorPosition() - 1);
        } else if (keyEvent.keyText.equals("Right")) {
          Vex.platform.setTextCursorPosition(Vex.platform.getTextCursorPosition() + 1);
        }
      }
      Vex.platform.setTextCursorPosition(
          Math.min(Vex.platform.getTextCursorPosition(), text.length()));
      Vex.platform.setTextCursorPosition(Math.max(Vex.platform.getTextCursorPosition(), 0));

      if (keyEvent != null) {
        Vex.platform.getKeyEvent();
        boolean delete = keyEvent.keyText.equals("Delete");
        boolean backspace = keyEvent.keyText.equals("Backspace");
        if (keyEvent.printable || delete || backspace) {
          String left =
              Vex.platform.getTextCursorPosition() > 0
                  ? text.substring(0, Vex.platform.getTextCursorPosition())
                  : "";
          String right =
              Vex.platform.getTextCursorPosition() < text.length()
                  ? text.substring(Vex.platform.getTextCursorPosition())
                  : "";

          if (keyEvent.printable) {
            text = left + keyEvent.key + right;
            Vex.platform.setTextCursorPosition(Vex.platform.getTextCursorPosition() + 1);
            updatedText = true;
          }
          if (delete && Vex.platform.getTextCursorPosition() < text.length()) {
            text = left + right.substring(1);
            updatedText = true;
          }
          if (backspace && Vex.platform.getTextCursorPosition() > 0) {
            text = left.substring(0, left.length() - 1) + right;
            Vex.platform.setTextCursorPosition(Vex.platform.getTextCursorPosition() - 1);
            updatedText = true;
          }
        }
      }

      int textCursorX = getStringSize(text.substring(0, Vex.platform.getTextCursorPosition())).x;

      g().fillRect(x + margin + textCursorX, y + margin, 1, height - margin * 2);
    }

    g().setColor(textColor);
    renderStringLeft(x + margin, y, width - margin * 2, height, text);

    Vex.platform.popClip();
    return WidgetStatus.text(updatedText, text);
  }

  public static void renderTitleBar(
      int x, int y, int width, int height, String title, Color backgroundColor, Color textColor) {
    g().setColor(backgroundColor);
    g().fillRect(x, y, width, height);

    g().setColor(textColor);
    Widgets.renderStringCentered(x, y, width, height, title);
  }

  public static void setColor(Color color) {
    g().setColor(color);
  }

  public static void setCurrentFocusId(String currentFocusId) {
    Widgets.currentFocusId = currentFocusId;
  }

  public static void setFont(String fontName, FontStyle fontStyle, int fontSize) {
    g().setFont(fontName, fontStyle, fontSize);
  }

  private static Graphics g() {
    return Vex.platform.getGraphics();
  }
}
