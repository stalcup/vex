package vex.widgets;

import vex.Graphics;
import vex.Platform;
import vex.Vex;
import vex.Widgets;

public class Widget {
  protected int height;
  protected int width;
  protected int x;
  protected int y;

  public Widget(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public WidgetStatus render(Style style) {
    Graphics g = Vex.platform.getGraphics();

    renderBackground(g, style);
    renderHoverColor(g, style);
    renderFont(g, style);
    renderText(g, style);
    renderBorder(g, style);

    return null;
  }

  protected void renderHoverColor(Graphics g, Style style) {
    if (style.hoverColor != null && Platform.mouseLocationIsIn(x, y, width, height)) {
      g.setColor(style.hoverColor);
      fillRectOrRoundRect(g, style);
    }
  }

  protected void renderBorder(Graphics g, Style style) {
    if (style.borderWidth > 0) {
      g.setColor(style.borderColor);
      g.setStroke(style.borderWidth);
      int pad = (int) Math.floor(style.borderWidth / 2);
      if (style.cornerRadius > 0) {
        g.drawRoundRect(x - 1, y - 1, width + 1, height + 1, style.cornerRadius);
      } else {
        g.drawRect(x - 1 - pad, y - 1 - pad, width + 1 + pad * 2, height + 1 + pad * 2);
      }
    }
  }

  protected void renderText(Graphics g, Style style) {
    if (style.text != null) {
      g.setColor(style.textColor);
      Widgets.renderAlignedString(x, y, width, height, style.text, style.textHorizontalAlignment);
    }
  }

  protected void renderFont(Graphics g, Style style) {
    if (style.fontName != null) {
      g.setFont(style.fontName, style.fontStyle, style.fontPointSize, style.fontStrikeThrough);
    }
  }

  protected void renderBackground(Graphics g, Style style) {
    if (style.backgroundColor != null) {
      g.setColor(style.backgroundColor);

      fillRectOrRoundRect(g, style);
    }
  }

  protected void fillRectOrRoundRect(Graphics g, Style style) {
    if (style.cornerRadius > 0) {
      g.fillRoundRect(x, y, width, height, style.cornerRadius);
    } else {
      g.fillRect(x, y, width, height);
    }
  }
}
