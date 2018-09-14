package scratch;

import vex.Color;
import vex.FontStyle;
import vex.Vex;
import vex.Widgets;
import vex.geom.Point;
import vex.widgets.AreaWidget;

public class Scratch {

  private static String queryValue = "";

  public static void doUi(int x, int y, int width, int height) {
    Point mouseLocation = Vex.platform.getMouseLocation();

    int mx = mouseLocation.x;
    int my = mouseLocation.y;
    mx = 0;
    my = 0;

    Widgets.renderRect(
        x, y, width, height, new Color(246 + mx / 100 % 10, 246 + (my / 100) % 10, 240));

    Widgets.area(mx + 20, my + 20, 340, 70)
        .backgroundColor(Color.GRAY_95)
        .border(1, Color.MEDIUM_HAZE)
        .cornerRadius(20)
        .render();

    queryValue =
        Widgets.textBox("query-textbox", mx + 40, my + 40, 200, 30)
            .backgroundColor(Color.WHITE)
            .border(1, Color.MEDIUM_HAZE)
            .cornerRadius(10)
            .text(queryValue, Color.BLACK)
            .font("Times New Roman", FontStyle.ITALIC, 14, false)
            .render()
            .text;

    AreaWidget button =
        Widgets.button("search-button", mx + 260, my + 40, 80, 30)
            .backgroundColor(Color.WHITE)
            .border(1, Color.MEDIUM_HAZE)
            .cornerRadius(10)
            .text("Search", Color.BLACK)
            .font("Arial", FontStyle.BOLD, 14, false)
            .hoverColor(Color.YELLOW);
    button.render();
  }
}
