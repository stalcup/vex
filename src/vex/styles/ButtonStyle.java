package vex.styles;

import vex.Base64Image;
import vex.Color;
import vex.Align;

public class ButtonStyle<T extends ButtonStyle<?>> extends Style<T> {

  public Color selectedBackgroundColor;

  public T selectedBackgroundColor(Color selectedBackgroundColor) {
    this.selectedBackgroundColor = selectedBackgroundColor;
    return self();
  }

  public Color selectedTextColor;

  public T selectedTextColor(Color selectedTextColor) {
    this.selectedTextColor = selectedTextColor;
    return self();
  }

  public Base64Image image;

  public T image(Base64Image image) {
    this.image = image;
    return self();
  }

  public Align imageHorizontalAlignment = Align.MID;

  public T imageHorizontalAlignment(Align imageHorizontalAlignment) {
    this.imageHorizontalAlignment = imageHorizontalAlignment;
    return self();
  }

  public int imageShiftX;

  public T imageShiftX(int imageShiftX) {
    this.imageShiftX = imageShiftX;
    return self();
  }

  public int imageShiftY;

  public T imageShiftY(int imageShiftY) {
    this.imageShiftY = imageShiftY;
    return self();
  }

  public ButtonStyle<?> dupe() {
    return new ButtonStyle<>()
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
        .selectedBackgroundColor(selectedBackgroundColor)
        .image(image)
        .imageHorizontalAlignment(imageHorizontalAlignment)
        .imageShiftX(imageShiftX)
        .imageShiftY(imageShiftY)
        .cursor(cursor);
  }
}
