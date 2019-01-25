package vex.widgets;

import com.google.common.base.Preconditions;

import vex.Align;
import vex.Color;
import vex.Platform;
import vex.Rect;
import vex.Vex;
import vex.Widgets;
import vex.events.KeyEvent;
import vex.events.MouseEvent.Type;
import vex.styles.RadioButtonStyle;
import vex.styles.Style;

public class RadioButtonWidget extends Widget {

  protected boolean selected;

  public RadioButtonWidget selected(boolean selected) {
    this.selected = selected;
    return this;
  }

  protected boolean disabled;

  @Override
  public RadioButtonWidget disabled(boolean disabled) {
    this.disabled = disabled;
    return this;
  }

  public RadioButtonWidget(String focusId, Rect bounds) {
    super(bounds);
    this.focusId = focusId;
  }

  @Override
  protected Rect computeHaloBounds(int focusHaloWidth, Style<?> style) {
    RadioButtonStyle<?> radioButtonStyle = (RadioButtonStyle<?>) style;
    return bounds
        .dupe()
        .onLeft(radioButtonStyle.triggerDiameter)
        .toCenterHeight(radioButtonStyle.triggerDiameter)
        .shrink(-focusHaloWidth);
  }

  @Override
  protected void drawFocusHaloShape(Style<?> style, Rect focusHaloBounds, int focusHaloWidth) {
    RadioButtonStyle<?> radioButtonStyle = (RadioButtonStyle<?>) style;
    radioButtonStyle.triggerShape.fill(focusHaloBounds);
  }

  @Override
  public Color computeBackgroundColor(Style<?> style) {
    RadioButtonStyle<?> radioButtonStyle = (RadioButtonStyle<?>) style;
    if (disabled && radioButtonStyle.disabledBackgroundColor != null) {
      return radioButtonStyle.disabledBackgroundColor;
    }
    if (selected && radioButtonStyle.selectedBackgroundColor != null) {
      return radioButtonStyle.selectedBackgroundColor;
    }

    return super.computeBackgroundColor(style);
  }

  @Override
  protected void renderBackground(Style<?> style) {
    Color backgroundColor = computeBackgroundColor(style);

    RadioButtonStyle<?> radioButtonStyle = (RadioButtonStyle<?>) style;

    if (backgroundColor != null) {
      Vex.setColor(backgroundColor);
      radioButtonStyle.triggerShape.fill(
          bounds
              .dupe()
              .onLeft(radioButtonStyle.triggerDiameter)
              .toCenterHeight(radioButtonStyle.triggerDiameter));
    }
  }

  @Override
  protected void renderBorder(Style<?> style) {
    Color borderColor = computeBorderColor(style);
    int borderWidth = computeBorderWidth(style);

    RadioButtonStyle<?> radioButtonStyle = (RadioButtonStyle<?>) style;

    if (borderWidth > 0 && borderColor != null) {
      Vex.setColor(borderColor);
      Vex.setStroke(borderWidth);
      radioButtonStyle.triggerShape.draw(
          bounds
              .dupe()
              .onLeft(radioButtonStyle.triggerDiameter)
              .toCenterHeight(radioButtonStyle.triggerDiameter));
    }

    if (selected) {
      Rect imageBounds =
          bounds
              .dupe()
              .onLeft(radioButtonStyle.triggerDiameter)
              .toCenterHeight(radioButtonStyle.triggerDiameter);
      if (radioButtonStyle.selectedImage != null) {
        Vex.drawAlignedImage(
            imageBounds.x,
            imageBounds.y,
            imageBounds.width,
            imageBounds.height,
            radioButtonStyle.selectedImage,
            Align.MID,
            0,
            0);
      } else if (borderColor != null) {
        Vex.setColor(borderColor);
        Vex.fillOval(
            bounds.x + (radioButtonStyle.triggerDiameter - radioButtonStyle.dimpleDiameter) / 2,
            bounds.y + (bounds.height - radioButtonStyle.dimpleDiameter) / 2,
            radioButtonStyle.dimpleDiameter,
            radioButtonStyle.dimpleDiameter);
      }
    }
  }

  @Override
  protected Color computeBorderColor(Style<?> style) {
    RadioButtonStyle<?> radioButtonStyle = (RadioButtonStyle<?>) style;

    if (disabled && radioButtonStyle.disabledBorderColor != null) {
      return radioButtonStyle.disabledBorderColor;
    }

    if (selected && radioButtonStyle.selectedBorderColor != null) {
      return radioButtonStyle.selectedBorderColor;
    }

    return super.computeBorderColor(style);
  }

  @Override
  protected void renderText(Style<?> style) {
    if (style.text != null) {
      Preconditions.checkState(style.textColor != null);

      RadioButtonStyle<?> radioButtonStyle = (RadioButtonStyle<?>) style;

      Vex.setColor(computeTextColor(style));
      int usedUpLeft = radioButtonStyle.triggerDiameter + style.paddingLeft;
      Vex.drawAlignedString(
          bounds.x + usedUpLeft,
          bounds.y,
          bounds.width - usedUpLeft,
          bounds.height,
          style.text,
          style.textAlignX);
    }
  }

  @Override
  public WidgetStatus render(Style<?> style) {
    Widgets.maybeFocusMeNext(focusId);
    boolean triedToClick =
        Platform.mouseEventIsIn(bounds.x, bounds.y, bounds.width, bounds.height, Type.DOWN);
    if (focusId != null && triedToClick) {
      Widgets.setCurrentFocusId(focusId);
    }

    super.render(style);

    if (isFocused()) {
      KeyEvent keyEvent = disabled ? null : Vex.platform.getKeyEvent();
      if (keyEvent != null) {
        String keyText = keyEvent.keyText;

        if ("Tab".equals(keyText)) {
          Widgets.focusNext = true;
        }

        triedToClick |= "Space".equals(keyText);
        triedToClick |= "Enter".equals(keyText);
      }
    }

    return WidgetStatus.click(!disabled && triedToClick, Widgets.lostFocus(focusId));
  }
}
