package vex.styles;

import vex.Align;
import vex.Color;
import vex.Cursor;
import vex.FontStyle;

public class Style<T extends Style<?>> {
  public static int NOT_SET = -1;

  @SuppressWarnings("unchecked")
  protected T self() {
    return (T) this;
  }

  public int paddingLeft;

  public T paddingLeft(int paddingLeft) {
    this.paddingLeft = paddingLeft;
    return self();
  }

  public Align textAlignX = Align.MID;

  public T textAlignX(Align textAlignX) {
    this.textAlignX = textAlignX;
    return self();
  }

  public Color hoverBackgroundColor;

  public T hoverBackgroundColor(Color hoverBackgroundColor) {
    this.hoverBackgroundColor = hoverBackgroundColor;
    return self();
  }

  public int hoverBorderWidth = NOT_SET;

  public T hoverBorderWidth(int hoverBorderWidth) {
    this.hoverBorderWidth = hoverBorderWidth;
    return self();
  }

  public Color hoverBorderColor;

  public T hoverBorderColor(Color hoverBorderColor) {
    this.hoverBorderColor = hoverBorderColor;
    return self();
  }

  public Color focusBorderColor;

  public T focusBorderColor(Color focusBorderColor) {
    this.focusBorderColor = focusBorderColor;
    return self();
  }

  public Color focusHaloColor;

  public T focusHaloColor(Color focusHaloColor) {
    this.focusHaloColor = focusHaloColor;
    return self();
  }

  public Color backgroundColor;

  public T backgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return self();
  }

  public T border(int borderWidth, Color borderColor) {
    this.borderWidth = borderWidth;
    this.borderColor = borderColor;
    return self();
  }

  public Color borderColor;

  public T borderColor(Color borderColor) {
    this.borderColor = borderColor;
    return self();
  }

  public int borderWidth;

  public T borderWidth(int borderWidth) {
    this.borderWidth = borderWidth;
    return self();
  }

  public int focusHaloWidth;

  public T focusHaloWidth(int focusHaloWidth) {
    this.focusHaloWidth = focusHaloWidth;
    return self();
  }

  public int cornerRadius;

  public T cornerRadius(int cornerRadius) {
    this.cornerRadius = cornerRadius;
    return self();
  }

  public String fontName;

  public boolean fontStrikeThrough;

  public T font(String name, FontStyle style, int pointSize, boolean strikeThrough) {
    fontName = name;
    fontStyle = style;
    fontPointSize = pointSize;
    fontStrikeThrough = strikeThrough;
    return self();
  }

  public String text;

  public T text(String text) {
    this.text = text;
    return self();
  }

  public T text(String text, Color textColor) {
    this.text = text;
    this.textColor = textColor;
    return self();
  }

  public Color textColor;

  public T textColor(Color textColor) {
    this.textColor = textColor;
    return self();
  }

  public int fontPointSize;

  public T fontPointSize(int fontPointSize) {
    this.fontPointSize = fontPointSize;
    return self();
  }

  public FontStyle fontStyle;

  public T fontStyle(FontStyle fontStyle) {
    this.fontStyle = fontStyle;
    return self();
  }

  public Cursor cursor;

  public T cursor(Cursor cursor) {
    this.cursor = cursor;
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
}
