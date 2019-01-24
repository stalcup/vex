package scratch;

import vex.Align;
import vex.BaseStyles;
import vex.Color;
import vex.FontStyle;
import vex.styles.ButtonStyle;
import vex.styles.Style;
import vex.styles.TextBoxStyle;

class ScratchTheme {
  static Style<?> panelStyle =
      BaseStyles.style()
          .backgroundColor(Color.GRAY_95)
          .border(1, Color.MEDIUM_HAZE)
          .cornerRadius(20);

  static TextBoxStyle<?> queryTextBoxStyle =
      BaseStyles.textBox()
          .textColor(Color.BLACK)
          .backgroundColor(Color.WHITE)
          .border(1, Color.MEDIUM_HAZE)
          .cornerRadius(10)
          .font("Times New Roman", FontStyle.ITALIC, 14, false);

  static ButtonStyle<?> buttonStyle =
      BaseStyles.button()
          .backgroundColor(Color.WHITE)
          .border(1, Color.MEDIUM_HAZE)
          .cornerRadius(10)
          .textColor(Color.BLACK)
          .font("Arial", FontStyle.BOLD, 14, false)
          .hoverBackgroundColor(Color.BLUE_95);

  static ButtonStyle<?> dropButtonTriggerStyle =
      BaseStyles.button()
          .backgroundColor(Color.WHITE)
          .border(1, Color.MEDIUM_HAZE)
          .cornerRadius(10)
          .textColor(Color.BLACK)
          .textAlignX(Align.MIN)
          .paddingLeft(7)
          .font("Arial", FontStyle.BOLD, 14, false)
          .hoverBackgroundColor(Color.BLUE_95);

  static ButtonStyle<?> labelStyle =
      BaseStyles.button()
          .textColor(Color.BLACK)
          .font("Arial", FontStyle.BOLD, 14, false)
          .textAlignX(Align.MIN);
}
