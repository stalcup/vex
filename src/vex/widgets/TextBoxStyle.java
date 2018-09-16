package vex.widgets;

import vex.Color;

public class TextBoxStyle<T extends TextBoxStyle<?>> extends Style<T> {
  public static int NOT_SET = -1;
  public int margin = NOT_SET;
  public String placeholderText;
  public Color placeholderTextColor;
  public Color underlineColor;

  public T margin(int margin) {
    this.margin = margin;
    return self();
  }

  public T placeholderText(String placeholderText, Color placeholderTextColor) {
    this.placeholderText = placeholderText;
    this.placeholderTextColor = placeholderTextColor;
    return self();
  }

  public T underlineColor(Color underlineColor) {
    this.underlineColor = underlineColor;
    return self();
  }
}
