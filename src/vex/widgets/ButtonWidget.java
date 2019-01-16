package vex.widgets;

import vex.Color;
import vex.Graphics;
import vex.Platform;
import vex.Rect;
import vex.Vex;
import vex.Widgets;
import vex.events.MouseEvent.Type;
import vex.styles.ButtonStyle;
import vex.styles.Style;

public class ButtonWidget extends Widget {

  protected boolean selected;

  public ButtonWidget selected(boolean selected) {
    this.selected = selected;
    return this;
  }

  public ButtonWidget(Rect bounds) {
    super(bounds);
  }

  public WidgetStatus render(ButtonStyle<?> style) {
    Graphics g = Vex.platform.getGraphics();

    renderBackground(g, style);
    renderFont(g, style);
    renderText(g, style);
    renderImage(g, style);
    renderBorder(g, style);
    renderTooltip(g, style);
    setCursor(style);

    return WidgetStatus.click(
        Platform.mouseEventIsIn(bounds.x, bounds.y, bounds.width, bounds.height, Type.DOWN), false);
  }

  @Override
  public Color computeBackgroundColor(Style<?> style) {
    ButtonStyle<?> buttonStyle = (ButtonStyle<?>) style;
    if (selected && buttonStyle.selectedBackgroundColor != null) {
      return ((ButtonStyle<?>) style).selectedBackgroundColor;
    }

    return super.computeBackgroundColor(style);
  }

  @Override
  public Color computeTextColor(Style<?> style) {
    ButtonStyle<?> buttonStyle = (ButtonStyle<?>) style;
    if (selected && buttonStyle.selectedTextColor != null) {
      return ((ButtonStyle<?>) style).selectedTextColor;
    }

    return super.computeTextColor(style);
  }

  private void renderImage(Graphics g, ButtonStyle<?> style) {
    if (style.image != null) {
      Widgets.drawAlignedImage(
          bounds.x,
          bounds.y,
          bounds.width,
          bounds.height,
          style.image,
          style.imageHorizontalAlignment,
          style.imageShiftX,
          style.imageShiftY);
    }
  }

  @Override
  public ButtonWidget tooltip(String tooltip) {
    return (ButtonWidget) super.tooltip(tooltip);
  }
}
