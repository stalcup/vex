package vex;

import vex.styles.ButtonStyle;
import vex.styles.DatePickerAreaStyle;
import vex.styles.RadioButtonStyle;
import vex.styles.Style;
import vex.styles.StyleShape;
import vex.styles.TextBoxStyle;

public class BaseStyles {

  public static Style<Style<?>> style() {
    return new Style<>();
  }

  public static ButtonStyle<?> button() {
    return new ButtonStyle<>().cursor(Cursor.HAND);
  }

  public static TextBoxStyle<?> textBox() {
    return new TextBoxStyle<>().cursor(Cursor.TEXT);
  }

  public static RadioButtonStyle<?> radioButton() {
    return new RadioButtonStyle<>()
        .textHorizontalAlignment(Align.MIN)
        .triggerShape(StyleShape.OVAL);
  }

  public static DatePickerAreaStyle<?> datePickerAreaStyle() {
    return new DatePickerAreaStyle<>();
  }
}
