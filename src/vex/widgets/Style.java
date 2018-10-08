package vex.widgets;

import vex.Color;
import vex.FontStyle;
import vex.HorizontalAlignment;

public class Style<T extends Style<?>> {
  public static int NOT_SET = -1;

  public Color hoverBackgroundColor;
  public Color backgroundColor;
  public Color hoverBorderColor;
  public int hoverBorderWidth = NOT_SET;

  public Color borderColor;
  public int borderWidth;

  public int cornerRadius;
  public String text;
  public Color textColor;
  public HorizontalAlignment textHorizontalAlignment = HorizontalAlignment.CENTER;
  public int paddingLeft;
  public String fontName;
  public FontStyle fontStyle;
  public int fontPointSize;
  public boolean fontStrikeThrough;

  @SuppressWarnings("unchecked")
  protected T self() {
    return (T) this;
  }

  public T paddingLeft(int paddingLeft) {
    this.paddingLeft = paddingLeft;
    return self();
  }

  public T textHorizontalAlignment(HorizontalAlignment textHorizontalAlignment) {
    this.textHorizontalAlignment = textHorizontalAlignment;
    return self();
  }

  public T hoverBackgroundColor(Color hoverBackgroundColor) {
    this.hoverBackgroundColor = hoverBackgroundColor;
    return self();
  }

  public T hoverBorderWidth(int hoverBorderWidth) {
    this.hoverBorderWidth = hoverBorderWidth;
    return self();
  }

  public T hoverBorder(int hoverBorderWidth, Color hoverBorderColor) {
    this.hoverBorderWidth = hoverBorderWidth;
    this.hoverBorderColor = hoverBorderColor;
    return self();
  }

  public T hoverBorderColor(Color hoverBorderColor) {
    this.hoverBorderColor = hoverBorderColor;
    return self();
  }

  public T backgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return self();
  }

  public T border(int borderWidth, Color borderColor) {
    this.borderWidth = borderWidth;
    this.borderColor = borderColor;
    return self();
  }

  public T borderColor(Color borderColor) {
    this.borderColor = borderColor;
    return self();
  }

  public T borderWidth(int borderWidth) {
    this.borderWidth = borderWidth;
    return self();
  }

  public T cornerRadius(int cornerRadius) {
    this.cornerRadius = cornerRadius;
    return self();
  }

  public T font(String name, FontStyle style, int pointSize, boolean strikeThrough) {
    fontName = name;
    fontStyle = style;
    fontPointSize = pointSize;
    fontStrikeThrough = strikeThrough;
    return self();
  }

  public T text(String text) {
    this.text = text;
    return self();
  }

  public T text(String text, Color textColor) {
    this.text = text;
    this.textColor = textColor;
    return self();
  }

  public T textColor(Color textColor) {
    this.textColor = textColor;
    return self();
  }
}
