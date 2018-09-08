package vex.swing;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import vex.Color;
import vex.Graphics;
import vex.geom.Point;

public class SwingGraphics implements Graphics {

  public Graphics2D g;

  public SwingGraphics(Graphics2D g) {
    this.g = g;
  }

  @Override
  public void drawRect(int x, int y, int width, int height) {
    g.drawRect(x, y, width, height);
  }

  @Override
  public void drawString(
      String string, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight) {
    g.clipRect(clipX, clipY, clipWidth, clipHeight);
    g.drawString(string, x, y);
    g.setClip(null);
  }

  @Override
  public void fillRect(int x, int y, int width, int height) {
    g.fillRect(x, y, width, height);
  }

  @Override
  public Point getSize(String string) {
    GlyphVector glyphVector = g.getFont().createGlyphVector(g.getFontRenderContext(), string);
    Rectangle2D bounds = glyphVector.getLogicalBounds();
    return new Point((int) Math.round(bounds.getWidth()), (int) Math.round(bounds.getHeight()));
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
