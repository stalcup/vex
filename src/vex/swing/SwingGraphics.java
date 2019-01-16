package vex.swing;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import vex.Base64Image;
import vex.Color;
import vex.FontStyle;
import vex.Graphics;
import vex.geom.Point;

public class SwingGraphics implements Graphics {

  public Graphics2D graphics;
  private boolean strikeThrough;
  private int pointSize;
  private int strokeWidth;

  public SwingGraphics(Graphics2D graphics) {
    this.graphics = graphics;
  }

  @Override
  public void drawRoundRect(int x, int y, int width, int height, int cornerRadius) {
    graphics.draw(
        new RoundRectangle2D.Float(
            x + (strokeWidth / 2f),
            y + (strokeWidth / 2f),
            width - strokeWidth,
            height - strokeWidth,
            cornerRadius * 2,
            cornerRadius * 2));
  }

  @Override
  public void drawRect(int x, int y, int width, int height) {
    graphics.draw(
        new Rectangle2D.Float(
            x + (strokeWidth / 2f),
            y + (strokeWidth / 2f),
            width - strokeWidth,
            height - strokeWidth));
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
    Font fallbackFont = new Font("SanSerif", font.getStyle(), font.getSize());
    font = string.length() == 1 && !font.canDisplay(string.charAt(0)) ? fallbackFont : font;
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
    strokeWidth = width;
    graphics.setStroke(new BasicStroke(width));
  }

  @Override
  public void drawDropShadow(
      int x, int y, int width, int height, int offsetX, int offsetY, int blur) {
    throw new RuntimeException("don't use this, it's too expensive");

    //    java.awt.Color startingColor = graphics.getColor();
    //
    //    int blurOffset = startingColor.getAlpha() / (blur + 1) - 1;
    //
    //    for (int i = 0; i < blur; i++) {
    //      graphics.setColor(
    //          new java.awt.Color(
    //              startingColor.getRed(),
    //              startingColor.getGreen(),
    //              startingColor.getBlue(),
    //              startingColor.getAlpha() / (i + 1) - blurOffset));
    //
    //      graphics.fillRoundRect(
    //          x - i + offsetX, y - i + offsetY, width + i * 2, height + i * 2, i * 2, i * 2);
    //    }
    //    graphics.setColor(startingColor);
  }

  @Override
  public void fillRoundRect(int x, int y, int width, int height, int cornerRadius) {
    graphics.fillRoundRect(x, y, width, height, cornerRadius * 2, cornerRadius * 2);
  }

  @Override
  public void drawOval(int x, int y, int width, int height) {
    graphics.draw(
        new Ellipse2D.Float(
            x + (strokeWidth / 2f),
            y + (strokeWidth / 2f),
            width - strokeWidth,
            height - strokeWidth));
  }

  @Override
  public void fillOval(int x, int y, int width, int height) {
    graphics.fillOval(x, y, width, height);
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

  @Override
  public void drawImage(int x, int y, Base64Image base64Image) {
    BufferedImage image = getOrConvertImage(base64Image);
    graphics.drawImage(image, x, y, null);
  }

  private static Map<String, BufferedImage> imagesByPath = new HashMap<>();

  private static Map<Base64Image, BufferedImage> imagesByEncoding = new HashMap<>();

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

  private BufferedImage getOrConvertImage(Base64Image base64Image) {
    if (imagesByEncoding.containsKey(base64Image)) {
      return imagesByEncoding.get(base64Image);
    }
    try {
      BufferedImage image =
          ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64Image.data)));
      imagesByEncoding.put(base64Image, image);
      return image;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean canDisplay(char c) {
    return graphics.getFont().canDisplay(c);
  }
}
