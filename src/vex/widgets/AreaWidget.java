package vex.widgets;

import vex.Color;
import vex.FontStyle;
import vex.Graphics;
import vex.HorizontalAlignment;
import vex.Platform;
import vex.Vex;
import vex.Widgets;

public class AreaWidget {
  private Color mouseOverColor;
  private Color backgroundColor;
  private Color borderColor;
  private int borderWidth;
  private int cornerRadius;
  private String text;
  private Color textColor;
  private HorizontalAlignment textHorizontalAlignment = HorizontalAlignment.CENTER;
  protected int height;
  protected int width;
  protected int x;
  protected int y;
  protected String fontName;
  protected FontStyle fontStyle;
  protected int fontPointSize;
  protected boolean fontStrikeThrough;

  public AreaWidget hoverColor(Color mouseOverColor) {
    this.mouseOverColor = mouseOverColor;
    return this;
  }

  public AreaWidget(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public AreaWidget backgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }

  public AreaWidget border(int borderWidth, Color borderColor) {
    this.borderWidth = borderWidth;
    this.borderColor = borderColor;
    return this;
  }

  public AreaWidget cornerRadius(int cornerRadius) {
    this.cornerRadius = cornerRadius;
    return this;
  }

  public AreaWidget font(String name, FontStyle style, int pointSize, boolean strikeThrough) {
    fontName = name;
    fontStyle = style;
    fontPointSize = pointSize;
    fontStrikeThrough = strikeThrough;
    return this;
  }

  public WidgetStatus render() {
    Graphics g = Vex.platform.getGraphics();

    renderBackground(g);
    renderHoverColor(g);
    renderFont(g);
    renderText(g);
    renderBorder(g);

    return null;
  }

  protected void renderHoverColor(Graphics g) {
    if (mouseOverColor != null && Platform.mouseLocationIsIn(x, y, width, height)) {
      g.setColor(mouseOverColor);
      fillRectOrRoundRect(g);
    }
  }

  protected void renderBorder(Graphics g) {
    if (borderWidth > 0) {
      g.setColor(borderColor);
      g.setStroke(borderWidth);
      int pad = (int) Math.floor(borderWidth / 2);
      if (cornerRadius > 0) {
        g.drawRoundRect(x - 1, y - 1, width + 1, height + 1, cornerRadius);
      } else {
        g.drawRect(x - 1 - pad, y - 1 - pad, width + 1 + pad * 2, height + 1 + pad * 2);
      }
    }
  }

  protected void renderText(Graphics g) {
    if (text != null) {
      g.setColor(textColor);
      Widgets.renderAlignedString(x, y, width, height, text, textHorizontalAlignment);
    }
  }

  protected void renderFont(Graphics g) {
    if (fontName != null) {
      g.setFont(fontName, fontStyle, fontPointSize, fontStrikeThrough);
    }
  }

  protected void renderBackground(Graphics g) {
    if (backgroundColor != null) {
      g.setColor(backgroundColor);

      fillRectOrRoundRect(g);
    }
  }

  protected void fillRectOrRoundRect(Graphics g) {
    if (cornerRadius > 0) {
      g.fillRoundRect(x, y, width, height, cornerRadius);
    } else {
      g.fillRect(x, y, width, height);
    }
  }

  public AreaWidget text(String text, Color textColor) {
    this.text = text;
    this.textColor = textColor;
    return this;
  }
}
