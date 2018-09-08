package todomvc;

import vex.Color;
import vex.Graphics.FontStyle;
import vex.Widgets;

public class TodoMvc {

  private static String entryValue = "";

  public static void doUi(int x, int y, int width, int height) {
    Widgets.renderRect(x, y, width, height, new Color(245, 245, 245));

    Widgets.setColor(new Color(175, 47, 47, 38));
    Widgets.setFont("Arial", FontStyle.PLAIN, 100);
    Widgets.renderStringCentered(x, y, width, 130, "todo");

    Widgets.setFont("Arial", FontStyle.ITALIC, 18);
    entryValue =
        Widgets.textBox("entry", (width - 550) / 2, 130, 550, 65)
            .backgroundColor(Color.WHITE)
            .border(3, Color.HAZE)
            .placeholderText("What needs to be done?", new Color(230, 230, 230))
            .text(entryValue, new Color(77, 77, 77))
            .margin(45)
            .render()
            .text;
  }
}
