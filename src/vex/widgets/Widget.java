package vex.widgets;

import com.google.common.base.Preconditions;

import vex.Align;
import vex.Color;
import vex.FontStyle;
import vex.Graphics;
import vex.Platform;
import vex.Rect;
import vex.Strings;
import vex.Vex;
import vex.Widgets;
import vex.geom.Point;
import vex.styles.Style;

public class Widget {
  protected Rect bounds;

  public Widget(Rect bounds) {
    this.bounds = bounds;
  }

  public WidgetStatus render(Style<?> style) {
    Graphics g = Vex.platform.getGraphics();

    if (isFocused()) {
      renderFocusHalo(g, style);
    }
    renderBackground(g, style);
    renderFont(g, style);
    renderText(g, style);
    renderBorder(g, style);
    renderTooltip(g, style);
    setCursor(style);

    return null;
  }

  protected void setCursor(Style<?> style) {
    if (style.cursor != null && isHovered()) {
      Vex.platform.setCursor(style.cursor);
    }
  }

  protected void renderTooltip(Graphics g, Style<?> style) {
    if (tooltip == null) {
      return;
    }

    if (isHovered()) {
      g.setFont("SanSerif", FontStyle.PLAIN, 12, false);
      Point tooltipSize = g.getSize(tooltip);

      Rect tooltipBounds =
          bounds
              .dupe()
              .onRight(0)
              .panRight(10)
              .toWidth(tooltipSize.x + 20)
              .toCenterHeight(tooltipSize.y + 6);

      g.setColor(Color.BLACK);
      g.fillRoundRect(
          tooltipBounds.x, tooltipBounds.y, tooltipBounds.width, tooltipBounds.height, 4);
      g.setColor(Color.WHITE);
      Widgets.renderAlignedString(tooltipBounds, tooltip, Align.MID);
    }
  }

  protected boolean isHovered() {
    return Platform.mouseLocationIsIn(bounds.x, bounds.y, bounds.width, bounds.height);
  }

  protected void renderFocusHalo(Graphics g, Style<?> style) {
    Color focusHaloColor = computeFocusHaloColor(style);
    int focusHaloWidth = computeFocusHaloWidth(style);

    if (focusHaloWidth > 0 && focusHaloColor != null) {
      g.setColor(focusHaloColor);
      g.setStroke(focusHaloWidth + 2);

      Rect focusHaloBounds = computeHaloBounds(focusHaloWidth, style);
      drawFocusHaloShape(g, style, focusHaloBounds, focusHaloWidth);
    }
  }

  protected void drawFocusHaloShape(
      Graphics g, Style<?> style, Rect focusHaloBounds, int focusHaloWidth) {
    if (style.cornerRadius > 0) {
      g.drawRoundRect(
          focusHaloBounds.x,
          focusHaloBounds.y,
          focusHaloBounds.width,
          focusHaloBounds.height,
          style.cornerRadius);
    } else {
      int pad = focusHaloWidth;
      g.drawRect(
          focusHaloBounds.x - pad,
          focusHaloBounds.y - pad,
          focusHaloBounds.width + pad * 2,
          focusHaloBounds.height + pad * 2);
    }
  }

  protected Rect computeHaloBounds(int focusHaloWidth, Style<?> style) {
    return bounds.dupe().shrink(-focusHaloWidth);
  }

  protected void renderBorder(Graphics g, Style<?> style) {
    Color borderColor = computeBorderColor(style);
    int borderWidth = computeBorderWidth(style);

    if (borderWidth > 0 && borderColor != null) {
      g.setColor(borderColor);
      g.setStroke(borderWidth);

      if (style.cornerRadius > 0) {
        g.drawRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, style.cornerRadius);
      } else {
        int pad = borderWidth;
        g.drawRect(bounds.x - pad, bounds.y - pad, bounds.width + pad * 2, bounds.height + pad * 2);
      }
    }
  }

  protected int computeFocusHaloWidth(Style<?> style) {
    return style.focusHaloWidth;
  }

  protected int computeBorderWidth(Style<?> style) {
    int borderWidth = style.borderWidth;
    if (isHovered()) {
      if (style.hoverBorderWidth != Style.NOT_SET) {
        borderWidth = style.hoverBorderWidth;
      }
    }
    return borderWidth;
  }

  protected Color computeFocusHaloColor(Style<?> style) {
    return style.focusHaloColor;
  }

  protected Color computeBorderColor(Style<?> style) {
    Color borderColor = style.borderColor;
    if (isFocused()) {
      if (style.focusBorderColor != null) {
        borderColor = style.focusBorderColor;
      }
    }
    if (isHovered()) {
      if (style.hoverBorderColor != null) {
        borderColor = style.hoverBorderColor;
      }
    }
    return borderColor;
  }

  boolean isFocused() {
    return focusId != null && Strings.equals(Widgets.getCurrentFocusId(), focusId);
  }

  protected void renderText(Graphics g, Style<?> style) {
    if (style.text != null) {
      Preconditions.checkState(style.textColor != null);
      g.setColor(computeTextColor(style));
      Widgets.renderAlignedString(
          bounds.x + style.paddingLeft,
          bounds.y,
          bounds.width - style.paddingLeft,
          bounds.height,
          style.text,
          style.textAlignX);
    }
  }

  protected Color computeTextColor(Style<?> style) {
    return style.textColor;
  }

  protected void renderFont(Graphics g, Style<?> style) {
    if (style.fontName != null) {
      g.setFont(style.fontName, style.fontStyle, style.fontPointSize, style.fontStrikeThrough);
    }
  }

  protected void renderBackground(Graphics g, Style<?> style) {
    Color color = computeBackgroundColor(style);

    if (color != null) {
      g.setColor(color);
      fillRectOrRoundRect(g, style);
    }
  }

  public Color computeBackgroundColor(Style<?> style) {
    Color color = style.backgroundColor;

    if (disabled && style.disabledBackgroundColor != null) {
      return style.disabledBackgroundColor;
    }
    if (style.hoverBackgroundColor != null && isHovered()) {
      color = style.hoverBackgroundColor;
    }

    return color;
  }

  protected void fillRectOrRoundRect(Graphics g, Style<?> style) {
    if (style.cornerRadius > 0) {
      g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, style.cornerRadius);
    } else {
      g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
  }

  protected String tooltip;

  protected Widget tooltip(String tooltip) {
    this.tooltip = tooltip;
    return this;
  }

  protected boolean disabled;

  public Widget disabled(boolean disabled) {
    this.disabled = disabled;
    return this;
  }

  protected String focusId;

  public Widget focusId(String focusId) {
    this.focusId = focusId;
    return this;
  }

  protected boolean receivesTabFocus = true;

  public Widget receivesTabFocus(boolean receivesTabFocus) {
    this.receivesTabFocus = receivesTabFocus;
    return this;
  }
}
