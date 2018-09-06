package vex.swing;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import vex.Color;
import vex.Graphics;
import vex.geom.Point;

public class SwingVexGraphics implements Graphics {

  public Graphics2D g;

  public SwingVexGraphics(Graphics2D g) {
    this.g = g;
  }

  @Override
  public void clearClip() {
    g.setClip(null);
  }

  @Override
  public void drawRect(int x, int y, int width, int height) {
    g.drawRect(x, y, width, height);
  }

  @Override
  public void drawString(String string, int x, int y) {
    g.drawString(string, x, y);
  }

  @Override
  public void fillRect(int x, int y, int width, int height) {
    g.fillRect(x, y, width, height);
  }

  @Override
  public Point getSize(String string) {
    Rectangle bounds =
        g.getFont().createGlyphVector(g.getFontRenderContext(), string).getPixelBounds(null, 0, 0);
    return new Point(bounds.width, bounds.height);
  }

  @Override
  public void setClip(int x, int y, int width, int height) {
    g.setClip(x, y, width, height);
  }

  @Override
  public void setColor(Color color) {
    g.setColor(new java.awt.Color(color.r, color.g, color.b, color.a));
  }

  @Override
  public void setFont(String fontName, FontStyle style, int pointSize) {
    g.setFont(new Font(fontName, style.code, pointSize));
  }

  @Override
  public void setStroke(int width) {
    g.setStroke(new BasicStroke(width));
  }
}
