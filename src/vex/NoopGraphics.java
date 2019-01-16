package vex;

import vex.geom.Point;

public class NoopGraphics implements Graphics {

  @Override
  public void drawDropShadow(
      int x, int y, int width, int height, int offsetX, int offsetY, int blur) {}

  @Override
  public void drawImage(int x, int y, String path) {}

  @Override
  public void drawImage(int x, int y, float scale, String path) {}

  @Override
  public void drawImage(int x, int y, Base64Image image) {}

  @Override
  public void drawRect(int x, int y, int width, int height) {}

  @Override
  public void drawRoundRect(int x, int y, int width, int height, int cornerRadius) {}

  @Override
  public void drawString(String string, int x, int y) {}

  @Override
  public void drawString(
      String string, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight) {}

  @Override
  public void fillRect(int x, int y, int width, int height) {}

  @Override
  public void fillRoundRect(int x, int y, int width, int height, int cornerRadius) {}

  @Override
  public void drawOval(int x, int y, int width, int height) {}

  @Override
  public void fillOval(int x, int y, int width, int height) {}

  @Override
  public Point getSize(String string) {
    return new Point(1, 1);
  }

  @Override
  public void setColor(Color color) {}

  @Override
  public void setFont(String fontName, FontStyle style, int pointSize, boolean strikeThrough) {}

  @Override
  public void setStroke(int width) {}

  @Override
  public boolean canDisplay(char c) {
    return false;
  }
}
