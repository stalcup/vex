package vex;

import vex.Platform.ResponseMessageHandler;
import vex.events.KeyEvent;
import vex.events.MouseEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;
import vex.geom.Rect;

public class Vex {
  public static Graphics graphics;
  public static Platform platform;

  public static void beginLayer() {
    platform.beginLayer();
  }

  public static boolean canDisplay(char c) {
    return graphics.canDisplay(c);
  }

  public static void consumeKeyEvent() {
    platform.consumeKeyEvent();
  }

  public static void consumeMouseEvent() {
    platform.consumeMouseEvent();
  }

  public static void doAfterFrame(Runnable callback) {
    platform.doAfterFrame(callback);
  }

  public static void drawAlignedImage(
      int x,
      int y,
      int width,
      int height,
      Base64Image image,
      Align horizontalAlignment,
      int imageShiftX,
      int imageShiftY) {
    if (horizontalAlignment == Align.MIN) {
      graphics.drawImage(x + imageShiftX, y + imageShiftY + (height - image.height) / 2, image);
    } else if (horizontalAlignment == Align.MID) {
      graphics.drawImage(
          x + imageShiftX + (width - image.width) / 2,
          y + imageShiftY + (height - image.height) / 2,
          image);
    } else if (horizontalAlignment == Align.MAX) {
      graphics.drawImage(
          x + imageShiftX + width - image.width,
          y + imageShiftY + (height - image.height) / 2,
          image);
    }
  }

  public static void drawAlignedImage(
      Rect rect, Base64Image image, Align horizontalAlignment, int imageShiftX, int imageShiftY) {
    drawAlignedImage(
        rect.x,
        rect.y,
        rect.width,
        rect.height,
        image,
        horizontalAlignment,
        imageShiftX,
        imageShiftY);
  }

  public static void drawAlignedString(
      int x, int y, int width, int height, String text, Align horizontalAlignment) {
    Point stringSize = getSize(text);

    if (horizontalAlignment == Align.MIN) {
      graphics.drawString(
          text,
          x,
          y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f),
          x,
          y,
          width,
          height);
    } else if (horizontalAlignment == Align.MID) {
      graphics.drawString(
          text,
          x + width / 2 - stringSize.x / 2,
          y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f));
    } else if (horizontalAlignment == Align.MAX) {
      graphics.drawString(
          text,
          x + width - stringSize.x,
          y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f),
          x,
          y,
          width,
          height);
    }
  }

  public static void drawAlignedString(Rect rect, String text, Align horizontalAlignment) {
    drawAlignedString(rect.x, rect.y, rect.width, rect.height, text, horizontalAlignment);
  }

  public static void drawDropShadow(
      int x, int y, int width, int height, int offsetX, int offsetY, int blur) {
    graphics.drawDropShadow(x, y, width, height, offsetX, offsetY, blur);
  }

  public static void drawImage(int x, int y, Base64Image image) {
    graphics.drawImage(x, y, image);
  }

  public static void drawImage(int x, int y, float scale, String path) {
    graphics.drawImage(x, y, scale, path);
  }

  public static void drawImage(int x, int y, String path) {
    graphics.drawImage(x, y, path);
  }

  public static void drawOval(int x, int y, int width, int height) {
    graphics.drawOval(x, y, width, height);
  }

  public static void drawOval(Rect rect) {
    drawOval(rect.x, rect.y, rect.width, rect.height);
  }

  public static void drawRect(int x, int y, int width, int height) {
    graphics.drawRect(x, y, width, height);
  }

  public static void drawRect(Rect rect) {
    graphics.drawRect(rect.x, rect.y, rect.width, rect.height);
  }

  public static void drawRoundRect(int x, int y, int width, int height, int cornerRadius) {
    graphics.drawRoundRect(x, y, width, height, cornerRadius);
  }

  public static void drawRoundRect(Rect rect, int cornerRadius) {
    drawRoundRect(rect.x, rect.y, rect.width, rect.height, cornerRadius);
  }

  public static void drawString(String string, int x, int y) {
    graphics.drawString(string, x, y);
  }

  public static void drawString(
      String string, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight) {
    graphics.drawString(string, x, y, clipX, clipY, clipWidth, clipHeight);
  }

  public static void endLayer() {
    platform.endLayer();
  }

  public static void fillOval(int x, int y, int width, int height) {
    graphics.fillOval(x, y, width, height);
  }

  public static void fillOval(Rect rect) {
    fillOval(rect.x, rect.y, rect.width, rect.height);
  }

  public static void fillRect(int x, int y, int width, int height) {
    graphics.fillRect(x, y, width, height);
  }

  public static void fillRect(int x, int y, int width, int height, Color color) {
    graphics.setColor(color);
    graphics.fillRect(x, y, width, height);
  }

  public static void fillRect(Rect rect) {
    graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
  }

  public static void fillRect(Rect rect, Color color) {
    fillRect(rect.x, rect.y, rect.width, rect.height, color);
  }

  public static void fillRoundRect(int x, int y, int width, int height, int cornerRadius) {
    graphics.fillRoundRect(x, y, width, height, cornerRadius);
  }

  public static void fillRoundRect(Rect rect, int cornerRadius) {
    graphics.fillRoundRect(rect.x, rect.y, rect.width, rect.height, cornerRadius);
  }

  public static int getFrameid() {
    return platform.getFrameid();
  }

  public static int getHeight() {
    return platform.getHeight();
  }

  public static KeyEvent getKeyEvent() {
    return platform.getKeyEvent();
  }

  public static String getLocation() {
    return platform.getLocation();
  }

  public static MouseEvent getMouseEvent() {
    return platform.getMouseEvent();
  }

  public static Point getMouseLocation() {
    return platform.getMouseLocation();
  }

  public static Point getSize(String string) {
    return graphics.getSize(string);
  }

  public static int getWidth() {
    return platform.getWidth();
  }

  public static void httpGet(String path, ResponseMessageHandler responseMessageHandler) {
    platform.httpGet(path, responseMessageHandler);
  }

  public static void httpPost(
      String path, String body, ResponseMessageHandler responseMessageHandler) {
    platform.httpPost(path, body, responseMessageHandler);
  }

  public static void httpPost(String path, ResponseMessageHandler responseMessageHandler) {
    platform.httpPost(path, "", responseMessageHandler);
  }

  public static boolean mouseEventIs(Type type) {
    return getMouseEvent() != null && getMouseEvent().type == type;
  }

  public static boolean mouseEventIsIn(int x, int y, int width, int height, Type type) {
    return getMouseEvent() != null
        && getMouseEvent().type == type
        && mouseLocationIsIn(x, y, width, height);
  }

  public static boolean mouseEventIsIn(Rect rect, Type type) {
    return Vex.mouseEventIsIn(rect.x, rect.y, rect.width, rect.height, type);
  }

  public static boolean mouseLocationIsIn(int x, int y, int width, int height) {
    Point point = getMouseLocation();
    return point != null
        && point.x >= x
        && point.y >= y
        && point.x < x + width
        && point.y < y + height;
  }

  public static boolean mouseLocationIsIn(Rect rect) {
    return Vex.mouseLocationIsIn(rect.x, rect.y, rect.width, rect.height);
  }

  public static void println(String line) {
    platform.println(line);
  }

  public static void setColor(Color color) {
    graphics.setColor(color);
  }

  public static void setCursor(Cursor cursor) {
    platform.setCursor(cursor);
  }

  public static void setFont(
      String fontName, FontStyle style, int pointSize, boolean strikeThrough) {
    graphics.setFont(fontName, style, pointSize, strikeThrough);
  }

  public static void setLocation(String location) {
    platform.setLocation(location);
  }

  public static void setStroke(int width) {
    graphics.setStroke(width);
  }

  public static void setTitle(String title) {
    platform.setTitle(title);
  }

  public static void setUi(Runnable ui) {
    platform.setUi(ui);
  }

  public static void repaint() {
    platform.repaint();
  }
}
