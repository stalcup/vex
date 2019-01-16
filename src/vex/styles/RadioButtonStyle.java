package vex.styles;

import vex.Base64Image;
import vex.Color;

public class RadioButtonStyle<T extends RadioButtonStyle<?>> extends Style<T> {

  public int triggerDiameter;

  public T triggerDiameter(int triggerDiameter) {
    this.triggerDiameter = triggerDiameter;
    return self();
  }

  public int dimpleDiameter;

  public T dimpleDiameter(int dimpleDiameter) {
    this.dimpleDiameter = dimpleDiameter;
    return self();
  }

  public Color selectedBorderColor;

  public T selectedBorderColor(Color selectedBorderColor) {
    this.selectedBorderColor = selectedBorderColor;
    return self();
  }

  public Color selectedBackgroundColor;

  public T selectedBackgroundColor(Color selectedBackgroundColor) {
    this.selectedBackgroundColor = selectedBackgroundColor;
    return self();
  }

  public Color disabledBorderColor;

  public T disabledBorderColor(Color disabledBorderColor) {
    this.disabledBorderColor = disabledBorderColor;
    return self();
  }

  public Color disabledBackgroundColor;

  public T disabledBackgroundColor(Color disabledBackgroundColor) {
    this.disabledBackgroundColor = disabledBackgroundColor;
    return self();
  }

  public Base64Image selectedImage;

  public T selectedImage(Base64Image selectedImage) {
    this.selectedImage = selectedImage;
    return self();
  }

  public StyleShape triggerShape;

  public RadioButtonStyle<?> triggerShape(StyleShape triggerShape) {
    this.triggerShape = triggerShape;
    return self();
  }

  public RadioButtonStyle<?> dupe() {
    return new RadioButtonStyle<>()
        .focusHaloColor(focusHaloColor)
        .focusHaloWidth(focusHaloWidth)
        .backgroundColor(backgroundColor)
        .border(borderWidth, borderColor)
        .cornerRadius(cornerRadius)
        .font(fontName, fontStyle, fontPointSize, fontStrikeThrough)
        .hoverBackgroundColor(hoverBackgroundColor)
        .hoverBorderWidth(hoverBorderWidth)
        .hoverBorderColor(hoverBorderColor)
        .focusBorderColor(focusBorderColor)
        .text(text, textColor)
        .textHorizontalAlignment(textHorizontalAlignment)
        .triggerDiameter(triggerDiameter)
        .dimpleDiameter(dimpleDiameter)
        .selectedBorderColor(selectedBorderColor)
        .selectedBackgroundColor(selectedBackgroundColor)
        .selectedImage(selectedImage)
        .disabledBorderColor(disabledBorderColor)
        .disabledBackgroundColor(disabledBackgroundColor)
        .triggerShape(triggerShape)
        .cursor(cursor);
  }
}
