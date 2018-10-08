package vex;

import vex.geom.Point;

public interface Graphics {

  void drawDropShadow(int x, int y, int width, int height, int offsetX, int offsetY, int blur);

  void drawImage(int x, int y, String path);

  void drawImage(int x, int y, float scale, String path);

  void drawRect(int x, int y, int width, int height);

  void drawRoundRect(int x, int y, int width, int height, int cornerRadius);

  void drawString(String string, int x, int y);

  void drawString(String string, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight);

  void fillRect(int x, int y, int width, int height);

  void fillRoundRect(int x, int y, int width, int height, int cornerRadius);

  Point getSize(String string);

  void setColor(Color color);

  void setFont(String fontName, FontStyle style, int pointSize, boolean strikeThrough);

  void setStroke(int width);
}
