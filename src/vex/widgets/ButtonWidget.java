package vex.widgets;

import vex.Color;
import vex.Platform;
import vex.Rect;
import vex.Vex;
import vex.Widgets;
import vex.events.KeyEvent;
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
    if (!disabled && focusId != null && receivesTabFocus) {
      Widgets.maybeFocusMeNext(focusId);
    }
    boolean clicked =
        !disabled
            && Platform.mouseEventIsIn(bounds.x, bounds.y, bounds.width, bounds.height, Type.DOWN);
    if (clicked) {
      if (focusId != null) {
        Widgets.setCurrentFocusId(focusId);
      }
    }

    renderBackground(style);
    renderFont(style);
    renderText(style);
    renderImage(style);
    renderBorder(style);
    renderTooltip(style);
    setCursor(style);

    if (!disabled && isFocused()) {
      KeyEvent keyEvent = Vex.platform.getKeyEvent();
      if (keyEvent != null) {
        boolean tab = "Tab".equals(keyEvent.keyText);
        if (tab) {
          Widgets.focusNext = tab;
        }
      }
    }

    return WidgetStatus.click(clicked, false);
  }

  @Override
  public Color computeBackgroundColor(Style<?> style) {
    ButtonStyle<?> buttonStyle = (ButtonStyle<?>) style;
    if (selected && buttonStyle.selectedBackgroundColor != null) {
      return buttonStyle.selectedBackgroundColor;
    }

    return super.computeBackgroundColor(style);
  }

  @Override
  public Color computeTextColor(Style<?> style) {
    ButtonStyle<?> buttonStyle = (ButtonStyle<?>) style;
    if (selected && buttonStyle.selectedTextColor != null) {
      return buttonStyle.selectedTextColor;
    }

    return super.computeTextColor(style);
  }

  private void renderImage(ButtonStyle<?> style) {
    if (style.image != null) {
      Vex.drawAlignedImage(
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

  @Override
  public ButtonWidget disabled(boolean disabled) {
    return (ButtonWidget) super.disabled(disabled);
  }

  @Override
  public ButtonWidget focusId(String focusId) {
    return (ButtonWidget) super.focusId(focusId);
  }
}
