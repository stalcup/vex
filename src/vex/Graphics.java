package vex;

import vex.geom.Point;

public interface Graphics {

  public static enum FontStyle {
    BOLD("bold", 1),
    ITALIC("italic", 2),
    PLAIN("normal", 0);

    public final int code;
    public final String name;

    FontStyle(String name, int code) {
      this.name = name;
      this.code = code;
    }
  }

  void drawRect(int x, int y, int width, int height);

  void drawString(String string, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight);

  void fillRect(int x, int y, int width, int height);

  Point getSize(String string);

  void setColor(Color color);

  void setFont(String fontName, FontStyle style, int pointSize);

  void setStroke(int width);
}
