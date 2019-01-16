package vex.styles;

import vex.Base64Image;
import vex.Color;
import vex.Graphics;
import vex.Align;
import vex.Widgets;

public class TextBoxStyle<T extends TextBoxStyle<?>> extends Style<T> {
  public int margin = NOT_SET;
  public String placeholderText;
  public Color placeholderTextColor;
  public Color underlineColor;
  public boolean password;

  public Color disabledBackgroundColor;

  public T disabledBackgroundColor(Color disabledBackgroundColor) {
    this.disabledBackgroundColor = disabledBackgroundColor;
    return self();
  }

  public Color disabledTextColor;

  public T disabledTextColor(Color disabledTextColor) {
    this.disabledTextColor = disabledTextColor;
    return self();
  }

  public Color disabledPlaceholderTextColor;

  public T disabledPlaceholderTextColor(Color disabledPlaceholderTextColor) {
    this.disabledPlaceholderTextColor = disabledPlaceholderTextColor;
    return self();
  }

  public Color disabledBorderColor;

  public T disabledBorderColor(Color disabledBorderColor) {
    this.disabledBorderColor = disabledBorderColor;
    return self();
  }

  public T margin(int margin) {
    this.margin = margin;
    return self();
  }

  public T placeholderText(String placeholderText, Color placeholderTextColor) {
    this.placeholderText = placeholderText;
    this.placeholderTextColor = placeholderTextColor;
    return self();
  }

  public T placeholderText(String placeholderText) {
    this.placeholderText = placeholderText;
    return self();
  }

  public T placeholderTextColor(Color placeholderTextColor) {
    this.placeholderTextColor = placeholderTextColor;
    return self();
  }

  public T underlineColor(Color underlineColor) {
    this.underlineColor = underlineColor;
    return self();
  }

  public T password(boolean password) {
    this.password = password;
    return self();
  }

  public TextBoxStyle<?> dupe() {
    return new TextBoxStyle<>()
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
        .margin(margin)
        .paddingLeft(margin)
        .placeholderText(placeholderText, placeholderTextColor)
        .text(text, textColor)
        .textHorizontalAlignment(textHorizontalAlignment)
        .underlineColor(underlineColor)
        .password(password)
        .disabledBackgroundColor(disabledBackgroundColor)
        .disabledBorderColor(disabledBorderColor)
        .disabledPlaceholderTextColor(disabledPlaceholderTextColor)
        .disabledTextColor(disabledTextColor)
        .cursor(cursor);
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
}
