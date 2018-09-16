package scratch;

import vex.Color;
import vex.FontStyle;
import vex.Widgets;
import vex.widgets.ButtonStyle;
import vex.widgets.Style;
import vex.widgets.TextBoxStyle;

public class Scratch {

  private static class DataState {
    static String queryValue = "";
  }

  private static class Styles {
    static Style<?> windowAreaStyle =
        Widgets.style()
            .backgroundColor(Color.GRAY_95)
            .border(1, Color.MEDIUM_HAZE)
            .cornerRadius(20);

    static TextBoxStyle<?> queryTextBoxStyle =
        Widgets.textBoxStyle()
            .textColor(Color.BLACK)
            .backgroundColor(Color.WHITE)
            .border(1, Color.MEDIUM_HAZE)
            .cornerRadius(10)
            .font("Times New Roman", FontStyle.ITALIC, 14, false);

    static ButtonStyle<?> buttonStyle =
        Widgets.buttonStyle()
            .backgroundColor(Color.WHITE)
            .border(1, Color.MEDIUM_HAZE)
            .cornerRadius(10)
            .textColor(Color.BLACK)
            .font("Arial", FontStyle.BOLD, 14, false)
            .hoverBackgroundColor(Color.YELLOW);
  }

  public static void doUi(int x, int y, int width, int height) {
    Widgets.renderRect(x, y, width, height, Color.WHITE);

    Widgets.area(20, 20, 340, 70).render(Styles.windowAreaStyle);

    DataState.queryValue =
        Widgets.textBox("query-textbox", 40, 40, 200, 30)
            .text(DataState.queryValue)
            .render(Styles.queryTextBoxStyle)
            .text;

    Widgets.button(260, 40, 80, 30).render(Styles.buttonStyle.text("Search"));
    Widgets.button(40, 110, 80, 30).render(Styles.buttonStyle.text("Refresh"));
  }
}
