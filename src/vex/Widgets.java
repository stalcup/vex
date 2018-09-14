package vex;

import vex.events.MouseEvent.Type;
import vex.geom.Point;
import vex.widgets.AreaWidget;
import vex.widgets.ButtonWidget;
import vex.widgets.TextBoxWidget;

public class Widgets {

  public static String currentFocusId;
  public static int textCursorPosition;

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

    if (Platform.mouseLocationIsIn(x, y, width, height) || currentFocusId == focusId) {
      g().fillRect(x, y + offsetPixels, width, visiblePixels);
    }

    if (Platform.mouseEventIsIn(x, y, width, height, Type.DOWN)) {
      currentFocusId = focusId;
      // Aka, start dragging.
    }

    if (currentFocusId == focusId
        && Vex.platform.getMouseEvent() != null
        && Vex.platform.getMouseEvent().type == Type.DRAG) {
      scrollPercent += Vex.platform.getMouseEvent().delta.y * 100f / hiddenPixels;

      scrollPercent = Math.max(0, scrollPercent);
      scrollPercent = Math.min(100, scrollPercent);
    }

    if (currentFocusId == focusId && Platform.mouseEventIs(Type.UP)) {
      currentFocusId = null;
    }

    return scrollPercent;
  }

  public static Graphics g() {
    return Vex.platform.getGraphics();
  }

  public static Point getStringSize(String text) {
    return g().getSize(text);
  }

  public static void renderInnerBorder(int x, int y, int width, int height, int border) {
    g().setStroke(border);
    g().drawRect(x, y, width - border, height - border);
  }

  public static void renderRect(int x, int y, int width, int height, Color color) {
    g().setColor(color);
    g().fillRect(x, y, width, height);
  }

  public static void renderStringCenteredBoth(int x, int y, int width, int height, String text) {
    Point stringSize = getStringSize(text);
    g().drawString(
            text,
            x + width / 2 - stringSize.x / 2,
            y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f),
            x,
            y,
            width,
            height);
  }

  public static void renderStringCenteredHorizontal(int x, int y, int width, String text) {
    Point stringSize = getStringSize(text);
    g().drawString(text, x + width / 2 - stringSize.x / 2, y);
  }

  public static void renderAlignedString(
      int x, int y, int width, int height, String text, HorizontalAlignment horizontalAlignment) {
    Point stringSize = getStringSize(text);

    if (horizontalAlignment == HorizontalAlignment.LEFT) {
      g().drawString(
              text,
              x,
              y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f),
              x,
              y,
              width,
              height);
    } else if (horizontalAlignment == HorizontalAlignment.CENTER) {
      g().drawString(
              text,
              x + width / 2 - stringSize.x / 2,
              y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f));
    } else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
      g().drawString(
              text,
              x + width - stringSize.x,
              y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f),
              x,
              y,
              width,
              height);
    }
  }

  public static void renderStringLeft(int x, int y, int width, int height, String text) {
    renderAlignedString(x, y, width, height, text, HorizontalAlignment.LEFT);
  }

  public static void renderStringRight(int x, int y, int width, int height, String text) {
    renderAlignedString(x, y, width, height, text, HorizontalAlignment.RIGHT);
  }

  public static void renderTitleBar(
      int x, int y, int width, int height, String title, Color backgroundColor, Color textColor) {
    g().setColor(backgroundColor);
    g().fillRect(x, y, width, height);

    g().setColor(textColor);
    renderStringCenteredBoth(x, y, width, height, title);
  }

  public static void setColor(Color color) {
    g().setColor(color);
  }

  public static void setFont(String fontName, FontStyle fontStyle, int fontSize) {
    g().setFont(fontName, fontStyle, fontSize, false);
  }

  public static ButtonWidget button(String focusId, int x, int y, int width, int height) {
    return new ButtonWidget(focusId, x, y, width, height);
  }

  public static TextBoxWidget textBox(String focusId, int x, int y, int width, int height) {
    return new TextBoxWidget(focusId, x, y, width, height);
  }

  public static AreaWidget area(int x, int y, int width, int height) {
    return new AreaWidget(x, y, width, height);
  }
}
