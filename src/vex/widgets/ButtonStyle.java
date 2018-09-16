package vex.widgets;

import vex.Color;

public class ButtonStyle<T extends ButtonStyle<?>> extends Style<T> {

  public Color selectedColor;

  public T setSelectedColor(Color selectedColor) {
    this.selectedColor = selectedColor;
    return self();
  }
}
