package vex.swing;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import vex.Color;
import vex.FontStyle;
import vex.Graphics;
import vex.geom.Point;

public class SwingGraphics implements Graphics {

  public Graphics2D graphics;
  private boolean strikeThrough;
  private int pointSize;

  public SwingGraphics(Graphics2D graphics) {
    this.graphics = graphics;
  }

  @Override
  public void drawRect(int x, int y, int width, int height) {
    graphics.drawRect(x, y, width, height);
  }

  @Override
  public void drawString(String string, int x, int y) {
    Font originalFont = null;
    // Maybe use a fallback for unicode symbols.
    if (string.length() == 1) {
      if (!graphics.getFont().canDisplay(string.charAt(0))) {
        originalFont = graphics.getFont();
        graphics.setFont(new Font("SanSerif", originalFont.getStyle(), originalFont.getSize()));
      }
    }
    graphics.drawString(string, x, y);
    if (originalFont != null) {
      graphics.setFont(originalFont);
    }
    if (strikeThrough) {
      Point size = getSize(string);
      graphics.fillRect(
          x, y - (int) Math.round(size.y * .25), size.x, (int) Math.ceil(pointSize / 9));
    }
  }

  @Override
  public void drawString(
      String string, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight) {
    graphics.clipRect(clipX, clipY, clipWidth, clipHeight);
    drawString(string, x, y);
    graphics.setClip(null);
  }

  @Override
  public void fillRect(int x, int y, int width, int height) {
    graphics.fillRect(x, y, width, height);
  }

  @Override
  public Point getSize(String string) {
    Font font = graphics.getFont();
    font =
        string.length() == 1 && !font.canDisplay(string.charAt(0))
            ? new Font("SanSerif", font.getStyle(), font.getSize())
            : font;
    GlyphVector glyphVector = font.createGlyphVector(graphics.getFontRenderContext(), string);
    Rectangle2D bounds = glyphVector.getLogicalBounds();
    return new Point((int) Math.round(bounds.getWidth()), (int) Math.round(bounds.getHeight()));
  }

  @Override
  public void setColor(Color color) {
    graphics.setColor(new java.awt.Color(color.r, color.g, color.b, color.a));
  }

  @Override
  public void setFont(String fontName, FontStyle style, int pointSize, boolean strikeThrough) {
    this.strikeThrough = strikeThrough;
    this.pointSize = pointSize;
    graphics.setFont(new Font(fontName, style.code, pointSize));
  }

  @Override
  public void setStroke(int width) {
    graphics.setStroke(new BasicStroke(width));
  }

  @Override
  public void drawDropShadow(
      int x, int y, int width, int height, int offsetX, int offsetY, int blur) {
    java.awt.Color startingColor = graphics.getColor();

    int blurOffset = startingColor.getAlpha() / (blur + 1) - 1;

    for (int i = 0; i < blur; i++) {
      graphics.setColor(
          new java.awt.Color(
              startingColor.getRed(),
              startingColor.getGreen(),
              startingColor.getBlue(),
              startingColor.getAlpha() / (i + 1) - blurOffset));

      graphics.fillRoundRect(
          x - i + offsetX, y - i + offsetY, width + i * 2, height + i * 2, i * 2, i * 2);
    }
    graphics.setColor(startingColor);
  }

  @Override
  public void drawRoundRect(int x, int y, int width, int height, int cornerRadius) {
    graphics.drawRoundRect(x, y, width, height, cornerRadius, cornerRadius);
  }

  @Override
  public void fillRoundRect(int x, int y, int width, int height, int cornerRadius) {
    graphics.fillRoundRect(x, y, width, height, cornerRadius, cornerRadius);
  }

  @Override
  public void drawImage(int x, int y, String imagePath) {
    BufferedImage image = getOrLoadImage(imagePath);
    graphics.drawImage(image, x, y, null);
  }

  @Override
  public void drawImage(int x, int y, float scale, String imagePath) {
    BufferedImage image = getOrLoadImage(imagePath);
    graphics.drawImage(
        image,
        x,
        y,
        (int) (x + image.getWidth() * scale),
        (int) (y + image.getHeight() * scale),
        null);
  }

  private Map<String, BufferedImage> imagesByPath = new HashMap<>();

  private BufferedImage getOrLoadImage(String imagePath) {
    if (imagesByPath.containsKey(imagePath)) {
      return imagesByPath.get(imagePath);
    }
    try {
      BufferedImage image = ImageIO.read(new File(imagePath));
      imagesByPath.put(imagePath, image);
      return image;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
