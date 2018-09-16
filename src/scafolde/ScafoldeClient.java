package scafolde;

import vex.Color;
import vex.FontStyle;
import vex.Platform;
import vex.Vex;
import vex.Widgets;
import vex.events.MouseEvent.Type;
import vex.widgets.TextBoxStyle;
import vex.widgets.TextBoxWidget;

public class ScafoldeClient {

  private static class Styles {
    static TextBoxStyle<?> editableLabelStyle =
        Widgets.textBoxStyle()
            .cornerRadius(10)
            .textColor(Color.BLACK)
            .hoverBackgroundColor(Color.HIGH_MIST)
            .font("Arial", FontStyle.BOLD, 24, false)
            .margin(3);
  }

  private static class PersistentState {
    static String databaseName = "Untitled Database";
  }

  private static class ClientState {
    static String userId = "stalcup";
    static String location = "databases";
  }

  private static String randomNumberFromServer = "";

  public static void doUi(int x, int y, int width, int height) {
    if (Platform.mouseEventIsIn(x, y, width, height, Type.DOWN)) {
      Widgets.currentFocusId = null;
    }

    Widgets.renderRect(x, y, width, height, Color.GRAY_95);

    if (Widgets.textBox("database-name-editable-label", 20, 10, 300, 30)
        .text(PersistentState.databaseName)
        .render(Styles.editableLabelStyle)
        .updated) {
      PersistentState.databaseName = TextBoxWidget.updatedText;
    }

    if (Widgets.button(200, 200, 400, 50)
        .render(
            Widgets.buttonStyle()
                .border(2, Color.BLACK)
                .cornerRadius(10)
                .font("Arial", FontStyle.BOLD, 18, false)
                .text("Get a Message from the server.", Color.BLACK)
                .backgroundColor(Color.WHITE))
        .clicked) {
      Vex.platform.httpGet(
          "http://localhost/functions/rand",
          r -> {
            randomNumberFromServer = r;
          });
    }
  }
}
