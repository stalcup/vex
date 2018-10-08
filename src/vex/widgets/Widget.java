package vex.widgets;

import vex.Color;
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

  public WidgetStatus render(Style<?> style) {
    Graphics g = Vex.platform.getGraphics();

    renderBackground(g, style);
    renderFont(g, style);
    renderText(g, style);
    renderBorder(g, style);

    return null;
  }

  protected void renderBorder(Graphics g, Style<?> style) {
    int borderWidth = style.borderWidth;
    Color borderColor = style.borderColor;

    if (Platform.mouseLocationIsIn(x, y, width, height)) {
      if (style.hoverBorderWidth != Style.NOT_SET) {
        borderWidth = style.hoverBorderWidth;
      }
      if (style.hoverBorderColor != null) {
        borderColor = style.hoverBorderColor;
      }
    }

    if (borderWidth > 0) {
      g.setColor(borderColor);
      g.setStroke(borderWidth);
      int pad = (int) Math.floor(borderWidth / 2);
      if (style.cornerRadius > 0) {
        g.drawRoundRect(x - 1, y - 1, width + 1, height + 1, style.cornerRadius);
      } else {
        g.drawRect(x - 1 - pad, y - 1 - pad, width + 1 + pad * 2, height + 1 + pad * 2);
      }
    }
  }

  protected void renderText(Graphics g, Style<?> style) {
    if (style.text != null) {
      g.setColor(style.textColor);
      Widgets.renderAlignedString(
          x + style.paddingLeft,
          y,
          width - style.paddingLeft,
          height,
          style.text,
          style.textHorizontalAlignment);
    }
  }

  protected void renderFont(Graphics g, Style<?> style) {
    if (style.fontName != null) {
      g.setFont(style.fontName, style.fontStyle, style.fontPointSize, style.fontStrikeThrough);
    }
  }

  protected void renderBackground(Graphics g, Style<?> style) {
    Color color = style.backgroundColor;

    if (style.hoverBackgroundColor != null && Platform.mouseLocationIsIn(x, y, width, height)) {
      color = style.hoverBackgroundColor;
    }

    if (color != null) {
      g.setColor(color);
      fillRectOrRoundRect(g, style);
    }
  }

  protected void fillRectOrRoundRect(Graphics g, Style<?> style) {
    if (style.cornerRadius > 0) {
      g.fillRoundRect(x, y, width, height, style.cornerRadius);
    } else {
      g.fillRect(x, y, width, height);
    }
  }
}
