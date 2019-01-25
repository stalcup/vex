package vex;

import vex.geom.Point;
import vex.geom.Rect;

public class Vex {
  public static Graphics graphics;
  public static Platform platform;

  public static boolean canDisplay(char c) {
    return graphics.canDisplay(c);
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

  public static Point getSize(String string) {
    return graphics.getSize(string);
  }

  public static void setColor(Color color) {
    graphics.setColor(color);
  }

  public static void setFont(
      String fontName, FontStyle style, int pointSize, boolean strikeThrough) {
    graphics.setFont(fontName, style, pointSize, strikeThrough);
  }

  public static void setStroke(int width) {
    graphics.setStroke(width);
  }
}
