package vex;

import vex.geom.Point;

public interface Graphics {

  void drawRoundRect(int x, int y, int width, int height, int cornerRadius);

  void drawRect(int x, int y, int width, int height);

  void drawString(String string, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight);

  void drawString(String string, int x, int y);

  void fillRoundRect(int x, int y, int width, int height, int cornerRadius);

  void fillRect(int x, int y, int width, int height);

  void drawDropShadow(int x, int y, int width, int height, int offsetX, int offsetY, int blur);

  Point getSize(String string);

  void setColor(Color color);

  void setFont(String fontName, FontStyle style, int pointSize, boolean strikeThrough);

  void setStroke(int width);
}
