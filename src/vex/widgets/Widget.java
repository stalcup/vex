package vex.widgets;

import com.google.common.base.Preconditions;

import vex.Align;
import vex.Color;
import vex.FontStyle;
import vex.Strings;
import vex.Vex;
import vex.Widgets;
import vex.geom.Point;
import vex.geom.Rect;
import vex.styles.Style;

public class Widget {
  protected Rect bounds;

  public Widget(Rect bounds) {
    this.bounds = bounds;
  }

  public WidgetStatus render(Style<?> style) {
    if (isFocused()) {
      renderFocusHalo(style);
    }
    renderBackground(style);
    renderFont(style);
    renderText(style);
    renderBorder(style);
    renderTooltip(style);
    setCursor(style);

    return null;
  }

  protected void setCursor(Style<?> style) {
    if (style.cursor != null && isHovered()) {
      Vex.setCursor(style.cursor);
    }
  }

  protected void renderTooltip(Style<?> style) {
    if (tooltip == null) {
      return;
    }

    if (isHovered()) {
      Vex.setFont("SanSerif", FontStyle.PLAIN, 12, false);
      Point tooltipSize = Vex.getSize(tooltip);

      Rect tooltipBounds =
          bounds
              .dupe("tooltip")
              .onRight(0)
              .panRight(10)
              .toWidth(tooltipSize.x + 20)
              .toCenterHeight(tooltipSize.y + 6);

      Vex.setColor(Color.BLACK);
      Vex.fillRoundRect(tooltipBounds, 4);
      Vex.setColor(Color.WHITE);
      Vex.drawAlignedString(tooltipBounds, tooltip, Align.MID);
    }
  }

  protected boolean isHovered() {
    return Vex.mouseLocationIsIn(bounds.x, bounds.y, bounds.width, bounds.height);
  }

  protected void renderFocusHalo(Style<?> style) {
    Color focusHaloColor = computeFocusHaloColor(style);
    int focusHaloWidth = computeFocusHaloWidth(style);

    if (focusHaloWidth > 0 && focusHaloColor != null) {
      Vex.setColor(focusHaloColor);
      Vex.setStroke(focusHaloWidth + 2);

      Rect focusHaloBounds = computeHaloBounds(focusHaloWidth, style);
      drawFocusHaloShape(style, focusHaloBounds, focusHaloWidth);
    }
  }

  protected void drawFocusHaloShape(Style<?> style, Rect focusHaloBounds, int focusHaloWidth) {
    if (style.cornerRadius > 0) {
      Vex.drawRoundRect(focusHaloBounds, style.cornerRadius);
    } else {
      int pad = focusHaloWidth;
      Vex.drawRect(focusHaloBounds.dupe("halo2?").expand(pad));
    }
  }

  protected Rect computeHaloBounds(int focusHaloWidth, Style<?> style) {
    return bounds.dupe("halo1?").expand(focusHaloWidth);
  }

  protected void renderBorder(Style<?> style) {
    Color borderColor = computeBorderColor(style);
    int borderWidth = computeBorderWidth(style);

    if (borderWidth > 0 && borderColor != null) {
      Vex.setColor(borderColor);
      Vex.setStroke(borderWidth);

      if (style.cornerRadius > 0) {
        Vex.drawRoundRect(bounds, style.cornerRadius);
      } else {
        int pad = borderWidth;
        Vex.drawRect(bounds.dupe("border").expand(pad));
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

  protected void renderText(Style<?> style) {
    if (style.text != null) {
      Preconditions.checkState(style.textColor != null);
      Vex.setColor(computeTextColor(style));
      Vex.drawAlignedString(
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

  protected void renderFont(Style<?> style) {
    if (style.fontName != null) {
      Vex.setFont(style.fontName, style.fontStyle, style.fontPointSize, style.fontStrikeThrough);
    }
  }

  protected void renderBackground(Style<?> style) {
    Color color = computeBackgroundColor(style);

    if (color != null) {
      Vex.setColor(color);
      fillRectOrRoundRect(style);
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

  protected void fillRectOrRoundRect(Style<?> style) {
    if (style.cornerRadius > 0) {
      Vex.fillRoundRect(bounds, style.cornerRadius);
    } else {
      Vex.fillRect(bounds);
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
