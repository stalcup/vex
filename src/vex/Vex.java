package vex;

import vex.geom.Point;

public class Vex {
  public static Platform platform;
  public static Graphics graphics;

  public static boolean canDisplay(char c) {
    return graphics.canDisplay(c);
  }

  public static void drawDropShadow(
      int x, int y, int width, int height, int offsetX, int offsetY, int blur) {
    graphics.drawDropShadow(x, y, width, height, offsetX, offsetY, blur);
  }

  public static void drawImage(int x, int y, String path) {
    graphics.drawImage(x, y, path);
  }

  public static void drawImage(int x, int y, float scale, String path) {
    graphics.drawImage(x, y, scale, path);
  }

  public static void drawImage(int x, int y, Base64Image image) {
    graphics.drawImage(x, y, image);
  }

  public static void drawRect(int x, int y, int width, int height) {
    graphics.drawRect(x, y, width, height);
  }

  public static void drawRoundRect(int x, int y, int width, int height, int cornerRadius) {
    graphics.drawRoundRect(x, y, width, height, cornerRadius);
  }

  public static void drawString(String string, int x, int y) {
    graphics.drawString(string, x, y);
  }

  public static void drawString(
      String string, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight) {
    graphics.drawString(string, x, y, clipX, clipY, clipWidth, clipHeight);
  }

  public static void fillRect(int x, int y, int width, int height) {
    graphics.fillRect(x, y, width, height);
  }

  public static void fillRoundRect(int x, int y, int width, int height, int cornerRadius) {
    graphics.fillRoundRect(x, y, width, height, cornerRadius);
  }

  public static void drawOval(int x, int y, int width, int height) {
    graphics.drawOval(x, y, width, height);
  }

  public static void fillOval(int x, int y, int width, int height) {
    graphics.fillOval(x, y, width, height);
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

  public static void drawRect(Rect rect) {
    graphics.drawRect(rect.x, rect.y, rect.width, rect.height);
  }

  public static void fillRoundRect(Rect rect, int cornerRadius) {
    graphics.fillRoundRect(rect.x, rect.y, rect.width, rect.height, cornerRadius);
  }

  public static void fillRect(Rect rect) {
    graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
  }
}
