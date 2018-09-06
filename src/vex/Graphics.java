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

  void clearClip();

  void drawRect(int x, int y, int width, int height);

  void drawString(String string, int x, int y);

  void fillRect(int x, int y, int width, int height);

  Point getSize(String string);

  void setClip(int x, int y, int width, int height);

  void setColor(Color color);

  void setFont(String fontName, FontStyle style, int pointSize);

  void setStroke(int width);
}
