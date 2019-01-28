package scratch;

import vex.Color;
import vex.Vex;
import vex.Widgets;
import vex.geom.Rect;
import vex.themes.flatui.FlatUiTheme;

public class Scratch {

  private static class UiState {
    static String newCountryText = "";
    static boolean isSelectingCountries;
    static String bigText = "";
  }

  public static void doUi(int x, int y, int width, int height) {
    Rect appRect = new Rect("root", x, y, width, height);

    Vex.fillRect(appRect, Color.WHITE);

    Rect queryInputBounds = appRect.dupe("queryInput").offLeft(20).offTop(20).onLeft(300).onTop(30);
    UiState.newCountryText =
        Widgets.textBox(
                "query-textbox",
                FlatUiTheme.textBoxRect
                    .dupe("queryTextBox")
                    .at(queryInputBounds.x, queryInputBounds.y))
            .text(UiState.newCountryText)
            .render(FlatUiTheme.defaultTextBoxStyle)
            .text;

    Rect selectorBounds = queryInputBounds.dupe("selector").panDown(50);
    UiState.isSelectingCountries =
        Widgets.textDropDown(selectorBounds, Constants.countries, DataState.selectedCountries)
            .render(
                ScratchTheme.dropButtonTriggerStyle,
                ScratchTheme.dropButtonTriggerStyle,
                UiState.isSelectingCountries)
            .open;

    Rect buttonBounds = selectorBounds.dupe("button").panDown(50);
    if (!UiState.newCountryText.isEmpty()
        && Widgets.button(buttonBounds)
            .render(FlatUiTheme.defaultButtonStyle.text("+ Add Country"))
            .clicked) {
      Constants.countries.add(0, UiState.newCountryText);
      DataState.selectedCountries.add(UiState.newCountryText);
      UiState.newCountryText = "";
    }

    Rect textAreaBounds = selectorBounds.dupe("textAreaPosition").panDown(100);
    UiState.bigText =
        Widgets.textBox(
                "textarea",
                FlatUiTheme.textAreaRect
                    .dupe("textAreaSize")
                    .at(textAreaBounds.x, textAreaBounds.y))
            .multiline(true)
            .text(UiState.bigText)
            .render(FlatUiTheme.defaultTextBoxStyle)
            .text;
  }
}
