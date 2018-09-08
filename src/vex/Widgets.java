package vex;

import vex.Graphics.FontStyle;
import vex.events.KeyEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;

public class Widgets {

  public static class TextBoxBuilder {

    private Color backgroundColor;
    private int border;
    private Color borderColor;
    private String focusId;
    private int height;
    private int margin;
    private String placeholderText;
    private Color placeholderTextColor;
    private String text;
    private Color textColor;
    private Color underlineColor;
    private int width;
    private int x;
    private int y;

    public TextBoxBuilder(String focusId, int x, int y, int width, int height) {
      this.focusId = focusId;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    public TextBoxBuilder backgroundColor(Color backgroundColor) {
      this.backgroundColor = backgroundColor;
      return this;
    }

    public TextBoxBuilder border(int border, Color borderColor) {
      this.border = border;
      this.borderColor = borderColor;
      return this;
    }

    public TextBoxBuilder margin(int margin) {
      this.margin = margin;
      return this;
    }

    public TextBoxBuilder placeholderText(String placeholderText, Color placeholderTextColor) {
      this.placeholderText = placeholderText;
      this.placeholderTextColor = placeholderTextColor;
      return this;
    }

    public WidgetStatus render() {
      if (Platform.mouseEventIsIn(x, y, width, height, Type.DOWN)) {
        setCurrentFocusId(focusId);
        Vex.platform.setTextCursorPosition(text.length());
      }

      Graphics g = g();
      if (backgroundColor != null) {
        g.setColor(backgroundColor);
        g.fillRect(x, y, width, height);
      }

      if (borderColor != null) {
        g.setColor(borderColor);
        g.setStroke(border);
        g.drawRect(x - border, y - border, width + border * 2 - 1, height + border * 2 - 1);
      }

      if (underlineColor != null) {
        g.setColor(underlineColor);
        g.fillRect(x, y + height - 1, width, 1);
      }

      if ((text == null || text.isEmpty())
          && placeholderText != null
          && placeholderTextColor != null) {
        g.setColor(placeholderTextColor);
        renderStringLeft(x + margin, y, width - margin, height, placeholderText);
      }

      boolean updatedText = false;

      g.setColor(textColor);
      boolean focused = getCurrentFocusId() == focusId;
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

        Point stringSize = getStringSize(text.substring(0, Vex.platform.getTextCursorPosition()));
        int textCursorX = stringSize.x;

        int textCursorPixelX = x + margin + textCursorX;
        if (textCursorPixelX < x + width) {
          g.fillRect(
              textCursorPixelX,
              y + (height - stringSize.y) / 2 + Math.round(stringSize.y / 7f),
              2,
              stringSize.y);
        }
      }

      renderStringLeft(x + margin, y, width - margin, height, text);

      return WidgetStatus.text(updatedText, text);
    }

    public TextBoxBuilder text(String text, Color textColor) {
      this.text = text;
      this.textColor = textColor;
      return this;
    }

    public TextBoxBuilder underlineColor(Color underlineColor) {
      this.underlineColor = underlineColor;
      return this;
    }
  }

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

  private static Graphics g() {
    return Vex.platform.getGraphics();
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
    g().drawString(
            text,
            x + width / 2 - stringSize.x / 2,
            y + height / 2 + stringSize.y / 2,
            x,
            y,
            width,
            height);
  }

  public static void renderStringLeft(int x, int y, int width, int height, String text) {
    g().drawString(
            text, x, y + height / 2 + Widgets.getStringSize(text).y / 2, x, y, width, height);
  }

  public static void renderStringRight(int x, int y, int width, int height, String text) {
    g().setColor(Color.BLACK);
    Point stringSize = Widgets.getStringSize(text);
    g().drawString(
            text, x + width - stringSize.x, y + height / 2 + stringSize.y / 2, x, y, width, height);
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

  public static TextBoxBuilder textBox(String focusId, int x, int y, int width, int height) {
    return new TextBoxBuilder(focusId, x, y, width, height);
  }
}
