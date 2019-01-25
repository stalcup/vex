package vex.themes.flatui;

import vex.Align;
import vex.BaseStyles;
import vex.Color;
import vex.FontStyle;
import vex.geom.Rect;
import vex.styles.ButtonStyle;
import vex.styles.DatePickerAreaStyle;
import vex.styles.RadioButtonStyle;
import vex.styles.StyleShape;
import vex.styles.TextBoxStyle;

public class FlatUiTheme {

  public static Rect textBoxRect = new Rect(0, 0, 227, 41);

  public static Rect textAreaRect = new Rect(0, 0, 227, 108);

  public static TextBoxStyle<?> baseTextBoxStyle =
      BaseStyles.textBox()
          .backgroundColor(Color.WHITE)
          .border(2, new Color(0xdc, 0xe4, 0xec))
          .font("SanSerif", FontStyle.PLAIN, 16, false)
          .paddingLeft(8)
          .cornerRadius(5);

  public static TextBoxStyle<?> defaultTextBoxStyle =
      baseTextBoxStyle
          .dupe()
          .placeholderTextColor(new Color(0xdc, 0xe4, 0xec))
          .textColor(new Color(0x34, 0x49, 0x5e))
          .focusBorderColor(new Color(26, 188, 156))
          .disabledBorderColor(new Color(213, 219, 219))
          .disabledBackgroundColor(new Color(244, 246, 246));

  public static TextBoxStyle<?> errorTextBoxStyle =
      baseTextBoxStyle
          .dupe()
          .borderColor(new Color(0xe7, 0x4c, 0x3c))
          .placeholderTextColor(new Color(0xe7, 0x4c, 0x3c))
          .textColor(new Color(0xe7, 0x4c, 0x3c));

  public static TextBoxStyle<?> successTextBoxStyle =
      baseTextBoxStyle
          .dupe()
          .borderColor(new Color(0x2e, 0xcc, 0x71))
          .placeholderTextColor(new Color(0x2e, 0xcc, 0x71))
          .textColor(new Color(0x2e, 0xcc, 0x71));

  public static TextBoxStyle<?> disabledTextBoxStyle =
      baseTextBoxStyle
          .dupe()
          .borderColor(new Color(213, 219, 219))
          .backgroundColor(new Color(244, 246, 246))
          .placeholderTextColor(new Color(213, 219, 219))
          .textColor(new Color(213, 219, 219));

  private static ButtonStyle<?> baseButtonStyle =
      BaseStyles.button()
          .textColor(Color.WHITE)
          .font("SanSerif", FontStyle.BOLD, 16, false)
          .cornerRadius(6);

  public static ButtonStyle<?> primaryButtonStyle =
      baseButtonStyle
          .dupe()
          .backgroundColor(new Color(0x1a, 0xbc, 0x9c))
          .hoverBackgroundColor(new Color(0x48, 0xc9, 0xb0));

  public static ButtonStyle<?> warningButtonStyle =
      baseButtonStyle
          .dupe()
          .backgroundColor(new Color(241, 196, 15))
          .hoverBackgroundColor(new Color(244, 211, 19));

  public static ButtonStyle<?> defaultButtonStyle =
      baseButtonStyle
          .dupe()
          .backgroundColor(new Color(189, 195, 199))
          .hoverBackgroundColor(new Color(202, 207, 210));

  public static ButtonStyle<?> dangerButtonStyle =
      baseButtonStyle
          .dupe()
          .backgroundColor(new Color(231, 76, 60))
          .hoverBackgroundColor(new Color(236, 112, 99));

  public static ButtonStyle<?> successButtonStyle =
      baseButtonStyle
          .dupe()
          .backgroundColor(new Color(46, 204, 113))
          .hoverBackgroundColor(new Color(88, 214, 141));

  public static ButtonStyle<?> inverseButtonStyle =
      baseButtonStyle
          .dupe()
          .backgroundColor(new Color(52, 73, 94))
          .hoverBackgroundColor(new Color(65, 91, 118));

  public static ButtonStyle<?> infoButtonStyle =
      baseButtonStyle
          .dupe()
          .backgroundColor(new Color(52, 152, 219))
          .hoverBackgroundColor(new Color(93, 173, 226));

  public static ButtonStyle<?> disabledButtonStyle =
      baseButtonStyle.dupe().backgroundColor(new Color(189, 195, 199)).textColor(Color.HIGH_MIST);

  public static ButtonStyle<?> baseHeaderLabelStyle =
      BaseStyles.button()
          .cursor(null)
          .textColor(new Color(52, 73, 94))
          .font("SanSerif", FontStyle.PLAIN, 24, false);

  public static ButtonStyle<?> h1LabelStyle = baseHeaderLabelStyle.dupe().fontPointSize(61);

  public static ButtonStyle<?> h2LabelStyle = baseHeaderLabelStyle.dupe().fontPointSize(53);

  public static ButtonStyle<?> h3LabelStyle = baseHeaderLabelStyle.dupe().fontPointSize(40);

  public static ButtonStyle<?> h4LabelStyle = baseHeaderLabelStyle.dupe().fontPointSize(29);

  public static ButtonStyle<?> h5LabelStyle = baseHeaderLabelStyle.dupe().fontPointSize(28);

  public static ButtonStyle<?> h6LabelStyle = baseHeaderLabelStyle.dupe().fontPointSize(24);

  public static ButtonStyle<?> dropdownTriggerStyle =
      BaseStyles.button()
          .backgroundColor(new Color(0x1a, 0xbc, 0x9c))
          .hoverBackgroundColor(new Color(0x48, 0xc9, 0xb0))
          .cornerRadius(6)
          .textColor(Color.WHITE)
          .textAlignX(Align.MIN)
          .paddingLeft(16)
          .font("SanSerif", FontStyle.BOLD, 16, false)
          .image(ImageResources.whiteCarret)
          .imageHorizontalAlignment(Align.MAX)
          .imageShiftX(-12)
          .imageShiftY(2);

  public static ButtonStyle<?> dropdownOptionStyle =
      BaseStyles.button()
          .backgroundColor(new Color(243, 244, 245))
          .hoverBackgroundColor(new Color(225, 228, 231))
          .textColor(new Color(52, 73, 94))
          .textAlignX(Align.MIN)
          .selectedBackgroundColor(new Color(0x1a, 0xbc, 0x9c))
          .selectedTextColor(Color.WHITE)
          .paddingLeft(7)
          .font("SanSerif", FontStyle.BOLD, 14, false);

  public static RadioButtonStyle<?> defaultRadioButtonStyle =
      BaseStyles.radioButton()
          .triggerDiameter(21)
          .dimpleDiameter(7)
          .borderWidth(4)
          .backgroundColor(Color.WHITE)
          .paddingLeft(11)
          .borderColor(new Color(189, 195, 199))
          .selectedBorderColor(new Color(26, 188, 156))
          .disabledBorderColor(new Color(230, 232, 234))
          .textColor(Color.BLACK)
          .font("SanSerif", FontStyle.PLAIN, 14, false);

  public static RadioButtonStyle<?> defaultCheckboxStyle =
      BaseStyles.radioButton()
          .triggerShape(StyleShape.roundRect(0.25f))
          .triggerDiameter(21)
          .backgroundColor(new Color(189, 195, 199))
          .paddingLeft(11)
          .textColor(Color.BLACK)
          .font("SanSerif", FontStyle.PLAIN, 14, false)
          .selectedImage(ImageResources.whiteCheck)
          .disabledBackgroundColor(new Color(230, 232, 234))
          .selectedBackgroundColor(new Color(26, 188, 156))
          .focusHaloColor(new Color(206, 231, 237))
          .focusHaloWidth(4);

  public static DatePickerAreaStyle<?> datePickerAreaStyle =
      BaseStyles.datePickerAreaStyle()
          .textColor(Color.WHITE)
          .font("SanSerif", FontStyle.BOLD, 14, false)
          .outerMargin(7)
          .innerMargin(3)
          .headerHeight(37 + 3)
          .dayWidth(37 + 3)
          .dayHeight(32 + 3)
          .innerBackgroundColor(Color.WHITE)
          .leftImage(ImageResources.whiteLeftArrow)
          .rightImage(ImageResources.whiteRightArrow)
          .backgroundColor(new Color(0x1a, 0xbc, 0x9c))
          .cornerRadius(6);

  public static ButtonStyle<?> datePickerDayStyle =
      BaseStyles.button()
          .font("SanSerif", FontStyle.PLAIN, 14, false)
          .textColor(new Color(52, 73, 94))
          .selectedBackgroundColor(new Color(0x1a, 0xbc, 0x9c))
          .selectedTextColor(Color.WHITE)
          .hoverBackgroundColor(new Color(202, 207, 210))
          .cornerRadius(6);

  public static TextBoxStyle<?> dateTextBoxStyle =
      defaultTextBoxStyle
          .dupe()
          .font("SanSerif", FontStyle.BOLD, 14, false)
          .image(ImageResources.greyCalendar)
          .imageHorizontalAlignment(Align.MIN)
          .imageShiftX(9)
          .margin(41);
}
