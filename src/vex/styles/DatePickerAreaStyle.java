package vex.styles;

import vex.Base64Image;
import vex.Color;

public class DatePickerAreaStyle<T extends DatePickerAreaStyle<?>> extends Style<T> {

  public int outerMargin;

  public DatePickerAreaStyle<T> outerMargin(int outerMargin) {
    this.outerMargin = outerMargin;
    return this;
  }

  public int headerHeight;

  public DatePickerAreaStyle<T> headerHeight(int headerHeight) {
    this.headerHeight = headerHeight;
    return this;
  }

  public Base64Image leftImage;

  public DatePickerAreaStyle<T> leftImage(Base64Image leftImage) {
    this.leftImage = leftImage;
    return this;
  }

  public Base64Image rightImage;

  public DatePickerAreaStyle<T> rightImage(Base64Image rightImage) {
    this.rightImage = rightImage;
    return this;
  }

  public int innerMargin;

  public DatePickerAreaStyle<T> innerMargin(int innerMargin) {
    this.innerMargin = innerMargin;
    return this;
  }

  public int dayWidth;

  public DatePickerAreaStyle<T> dayWidth(int dayWidth) {
    this.dayWidth = dayWidth;
    return this;
  }

  public int dayHeight;

  public DatePickerAreaStyle<T> dayHeight(int dayHeight) {
    this.dayHeight = dayHeight;
    return this;
  }

  public Color innerBackgroundColor;

  public DatePickerAreaStyle<T> innerBackgroundColor(Color innerBackgroundColor) {
    this.innerBackgroundColor = innerBackgroundColor;
    return this;
  }
}
