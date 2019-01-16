package scratch;

import vex.Color;
import vex.Rect;
import vex.Widgets;
import vex.themes.flatui.FlatUiTheme;

public class Scratch {

  private static class UiState {
    static String newCountryText = "";
    static boolean isSelectingCountries;
    static String bigText = "";
  }

  public static void doUi(int x, int y, int width, int height) {
    Rect appRect = new Rect(x, y, width, height);

    Widgets.renderRect(appRect, Color.WHITE);

    Rect queryBounds = appRect.dupe().offLeft(20).offTop(20).onLeft(300).onTop(30);
    UiState.newCountryText =
        Widgets.textBox(
                "query-textbox", FlatUiTheme.textBoxRect.dupe().at(queryBounds.x, queryBounds.y))
            .text(UiState.newCountryText)
            .render(FlatUiTheme.defaultTextBoxStyle)
            .text;

    Rect selectorBounds = queryBounds.dupe().panDown(50);
    UiState.isSelectingCountries =
        Widgets.textDropDown(selectorBounds, Constants.countries, DataState.selectedCountries)
            .render(
                ScratchTheme.dropButtonTriggerStyle,
                ScratchTheme.dropButtonTriggerStyle,
                UiState.isSelectingCountries)
            .open;

    Rect buttonBounds = selectorBounds.dupe().panDown(50);
    if (!UiState.newCountryText.isEmpty()
        && Widgets.button(buttonBounds)
            .render(FlatUiTheme.defaultButtonStyle.text("+ Add Country"))
            .clicked) {
      Constants.countries.add(0, UiState.newCountryText);
      DataState.selectedCountries.add(UiState.newCountryText);
      UiState.newCountryText = "";
    }

    Rect textAreaBounds = selectorBounds.dupe().panDown(100);
    UiState.bigText =
        Widgets.textBox(
                "textarea", FlatUiTheme.textAreaRect.dupe().at(textAreaBounds.x, textAreaBounds.y))
            .multiline(true)
            .text(UiState.bigText)
            .render(FlatUiTheme.defaultTextBoxStyle)
            .text;
  }
}
