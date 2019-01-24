package flatuithemegallery;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vex.Align;
import vex.Color;
import vex.FontStyle;
import vex.Rect;
import vex.Widgets;
import vex.styles.ButtonStyle;
import vex.themes.flatui.FlatUiTheme;
import vex.widgets.TextBoxWidget;
import vex.widgets.WidgetStatus;

public class FlatUiThemeGallery {

  private static ButtonStyle<?> sectionLabelStyle =
      FlatUiTheme.h6LabelStyle.dupe().textAlignX(Align.MIN);

  private static List<String> dropDownOptions =
      Arrays.asList("Action", "Another Action", "Something else here");
  private static Set<String> selectedOptions = new HashSet<>();
  private static boolean dropDownOpen;
  private static Map<String, String> radioButtonsData;
  private static String selectedRadioOption = "radio-on";

  private static boolean uncheckedChecked = false;
  private static boolean checkedChecked = true;

  private static long timeMs = System.currentTimeMillis();

  private static String defaultTextBoxContent = "";

  static {
    radioButtonsData = new LinkedHashMap<>();
    radioButtonsData.put("radio-off", "Radio is off");
    radioButtonsData.put("radio-on", "Radio is on");
  }

  public static void doUi(Rect bounds) {
    Widgets.renderRect(bounds, Color.WHITE);

    Rect basicElementsLabelBounds = bounds.dupe().offLeft(20).offTop(20).onLeft(300).onTop(40);
    Widgets.label(basicElementsLabelBounds)
        .render(
            FlatUiTheme.baseHeaderLabelStyle
                .dupe()
                .fontPointSize(32)
                .fontStyle(FontStyle.BOLD)
                .textAlignX(Align.MIN)
                .text("Basic Elements"));

    Rect buttonsLabelBounds = basicElementsLabelBounds.dupe().panDown(60);
    Widgets.label(buttonsLabelBounds).render(sectionLabelStyle.text("Buttons"));

    Rect buttonBounds = buttonsLabelBounds.dupe().panDown(60).to(212, 45);
    Widgets.button(buttonBounds).render(FlatUiTheme.primaryButtonStyle.text("Primary Button"));
    Widgets.button(buttonBounds.panRight(212 + 30))
        .render(FlatUiTheme.warningButtonStyle.text("Warning Button"));
    Widgets.button(buttonBounds.panRight(212 + 30))
        .render(FlatUiTheme.defaultButtonStyle.text("Default Button"));
    Widgets.button(buttonBounds.panRight(212 + 30))
        .render(FlatUiTheme.dangerButtonStyle.text("Danger Button"));

    buttonBounds = buttonsLabelBounds.dupe().panDown(125).to(212, 45);
    Widgets.button(buttonBounds).render(FlatUiTheme.successButtonStyle.text("Success Button"));
    Widgets.button(buttonBounds.panRight(212 + 30))
        .render(FlatUiTheme.inverseButtonStyle.text("Inverse Button"));
    Widgets.button(buttonBounds.panRight(212 + 30))
        .render(FlatUiTheme.infoButtonStyle.text("Info Button"));
    Widgets.button(buttonBounds.panRight(212 + 30))
        .render(FlatUiTheme.disabledButtonStyle.text("Disabled Button"));

    Rect inputLabelBounds = buttonsLabelBounds.dupe().panDown(205).to(212, 45);
    Widgets.label(inputLabelBounds).render(sectionLabelStyle.text("Input"));

    Rect textBoxBounds = inputLabelBounds.dupe().panDown(60).to(212, 42);
    if (Widgets.textBox("default-textbox", textBoxBounds)
        .text(defaultTextBoxContent)
        .render(FlatUiTheme.defaultTextBoxStyle.placeholderText("Inactive"))
        .updated) {
      defaultTextBoxContent = TextBoxWidget.updatedText;
    }

    Widgets.textBox("error-textbox", textBoxBounds.panRight(212 + 30))
        .render(FlatUiTheme.errorTextBoxStyle.placeholderText("Error"));
    Widgets.textBox("success-textbox", textBoxBounds.panRight(212 + 30))
        .render(FlatUiTheme.successTextBoxStyle.placeholderText("Success"));
    Widgets.textBox("disabled-textbox", textBoxBounds.panRight(212 + 30))
        .render(FlatUiTheme.disabledTextBoxStyle.placeholderText("Disabled"));

    Rect dropdownLabelBounds = buttonsLabelBounds.dupe().panDown(345).to(212, 45);
    Widgets.label(dropdownLabelBounds).render(sectionLabelStyle.text("Dropdown"));

    Rect dropdownBounds = dropdownLabelBounds.dupe().panDown(60).to(212, 42);
    dropDownOpen =
        Widgets.textDropDown(dropdownBounds, dropDownOptions, selectedOptions)
            .render(
                FlatUiTheme.dropdownTriggerStyle.text("Default"),
                FlatUiTheme.dropdownOptionStyle,
                dropDownOpen)
            .open;

    Rect radioButtonLabelBounds = buttonsLabelBounds.dupe().panDown(485).to(212, 45);
    Widgets.label(radioButtonLabelBounds).render(sectionLabelStyle.text("Radio Buttons"));

    Rect radioButtonsBounds = radioButtonLabelBounds.dupe().panDown(60).to(212, 60);
    selectedRadioOption =
        Widgets.radioButtons(radioButtonsBounds, radioButtonsData, selectedRadioOption)
            .render(FlatUiTheme.defaultRadioButtonStyle)
            .selectedKey;

    Rect radioButtonBounds = radioButtonsBounds.dupe().onBottom(0).panDown(3).to(212, 30);

    Widgets.radioButton(null, radioButtonBounds)
        .disabled(true)
        .render(FlatUiTheme.defaultRadioButtonStyle.text("Disabled radio is off"));
    Widgets.radioButton(null, radioButtonBounds.panDown(33))
        .selected(true)
        .disabled(true)
        .render(FlatUiTheme.defaultRadioButtonStyle.text("Disabled radio is on"));

    Rect checkboxesLabelBounds = radioButtonLabelBounds.dupe().panRight(212 + 30);
    Widgets.label(checkboxesLabelBounds).render(sectionLabelStyle.text("Checkboxes"));

    Rect checkboxesBounds = checkboxesLabelBounds.dupe().onBottom(0).panDown(15).to(212, 30);

    if (Widgets.radioButton(null, checkboxesBounds)
        .selected(uncheckedChecked)
        .render(FlatUiTheme.defaultCheckboxStyle.text("Unchecked"))
        .clicked) {
      uncheckedChecked = !uncheckedChecked;
    }
    if (Widgets.radioButton(null, checkboxesBounds.panDown(33))
        .selected(checkedChecked)
        .render(FlatUiTheme.defaultCheckboxStyle.text("Checked"))
        .clicked) {
      checkedChecked = !checkedChecked;
    }
    Widgets.radioButton(null, checkboxesBounds.panDown(33))
        .selected(false)
        .disabled(true)
        .render(FlatUiTheme.defaultCheckboxStyle.text("Disabled unchecked"));
    Widgets.radioButton(null, checkboxesBounds.panDown(33))
        .selected(true)
        .disabled(true)
        .render(FlatUiTheme.defaultCheckboxStyle.text("Disabled checked"));

    Rect datePickerLabelBounds = checkboxesLabelBounds.dupe().panRight(212 + 30);
    Widgets.label(datePickerLabelBounds).render(sectionLabelStyle.text("Date Picker"));

    Rect dateTextBoxBounds = datePickerLabelBounds.dupe().onBottom(0).panDown(15).to(212, 42);
    WidgetStatus dateWidgetStatus =
        Widgets.dateTextBox("test-date-textbox", dateTextBoxBounds)
            .timeMs(timeMs)
            .render(
                FlatUiTheme.dateTextBoxStyle,
                FlatUiTheme.datePickerAreaStyle,
                FlatUiTheme.datePickerDayStyle);
    if (dateWidgetStatus.updated) {
      timeMs = dateWidgetStatus.timeMs;
    }

    //    Rect datepickerBounds = datePickerLabelBounds.dupe().onBottom(0).panDown(15).to(212, 200);
    //    WidgetStatus dateWidgetStatus = Widgets.datePicker(datepickerBounds)
    //        .timeMs(timeMs)
    //        .render(FlatUiTheme.datePickerAreaStyle, FlatUiTheme.datePickerDayStyle);
    //    if (dateWidgetStatus.updated) {
    //      timeMs = dateWidgetStatus.timeMs;
    //    }
  }
}
