package todomvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import vex.Color;
import vex.Graphics;
import vex.Graphics.FontStyle;
import vex.Platform;
import vex.Vex;
import vex.Widgets;
import vex.widgets.WidgetStatus;

public class TodoMvc {

  private static class Todo {
    String id;
    String value;
    boolean done;
    public boolean deleted;

    Todo(String value) {
      this.id = Math.random() + "";
      this.value = value;
    }
  }

  private static Color smallDropShadowColor = new Color(96, 96, 96, 255 * 3 / 10);
  private static int smallDropShadowRadius = 6;
  private static int smallDropShadowOffsetY = 1;

  private static Color bigDropShadowColor = new Color(225, 225, 225, 255);
  private static int bigDropShadowRadius = 25;
  private static int bigDropShadowOffsetY = 5;

  private static String entryValue = "";
  private static List<Todo> todos = new ArrayList<>();
  private static String showTodosFilter = "all";

  public static void doUi(int x, int y, int width, int height) {
    if (Widgets.currentFocusId == null) {
      Widgets.currentFocusId = "entry";
    }

    Widgets.renderRect(x, y, width, height, new Color(245, 245, 245));

    Widgets.setColor(new Color(175, 47, 47, 38));
    Widgets.setFont("SanSerif", FontStyle.PLAIN, 100);
    Widgets.renderStringCenteredHorizontal(x, y + 103, width, "todos");

    int left = (width - 550) / 2;
    int todoHeight = 59;

    int filterHeight = 40;

    int dropShadowHeight = 66;
    for (Todo todo : todos) {
      if (showTodosFilter.equals("done") && todo.done) {
        continue;
      } else if (showTodosFilter.equals("not done") && !todo.done) {
        continue;
      }

      dropShadowHeight += todoHeight;
    }

    Graphics g = Vex.platform.getGraphics();

    Widgets.setColor(bigDropShadowColor);
    g.drawDropShadow(
        left,
        130,
        550,
        dropShadowHeight + (todos.isEmpty() ? 0 : filterHeight),
        0,
        bigDropShadowOffsetY + (todos.isEmpty() ? 0 : 10),
        bigDropShadowRadius);

    if (!todos.isEmpty()) {
      Widgets.setColor(smallDropShadowColor);
      g.drawDropShadow(
          left + 6,
          130 + dropShadowHeight + 10,
          550 - 12,
          filterHeight,
          0,
          smallDropShadowOffsetY,
          smallDropShadowRadius);
      Widgets.renderRect(
          left + 6, 130 + dropShadowHeight + 10, 550 - 12, filterHeight, Color.WHITE);

      Widgets.setColor(smallDropShadowColor);
      g.drawDropShadow(
          left + 3,
          130 + dropShadowHeight + 5,
          550 - 6,
          filterHeight,
          0,
          smallDropShadowOffsetY,
          smallDropShadowRadius);
      Widgets.renderRect(left + 3, 130 + dropShadowHeight + 5, 550 - 6, filterHeight, Color.WHITE);
    }

    Widgets.setColor(smallDropShadowColor);
    g.drawDropShadow(
        left,
        130,
        550,
        dropShadowHeight + (todos.isEmpty() ? 0 : filterHeight),
        0,
        smallDropShadowOffsetY,
        smallDropShadowRadius);

    int entryHeight = 66;
    WidgetStatus entryStatus =
        Widgets.textBox("entry", left, 130, 550, entryHeight)
            .backgroundColor(Color.WHITE)
            //            .border(1, Color.LOW_HAZE)
            .font("SanSerif", FontStyle.PLAIN, 24, false)
            .margin(60)
            .placeholderText("What needs to be done?", new Color(230, 230, 230))
            .text(entryValue, new Color(77, 77, 77))
            .render();

    if (!todos.isEmpty()) {
      Widgets.setFont("SanSerif", FontStyle.PLAIN, 30);
      if (Widgets.button(null, left + 6, 130 + 8, 45, 45)
          .text("✔")
          .textColor(Color.GRAY_80)
          .render()
          .clicked) {
        boolean newState = !todos.get(0).done;
        for (Todo todo : todos) {
          todo.done = newState;
        }
      }
    }

    if (entryStatus.updated) {
      entryValue = entryStatus.text;
    } else if (!entryValue.isEmpty() && "Enter".equals(entryStatus.keyText)) {
      todos.add(new Todo(entryValue));
      entryValue = "";
    }

    int top = 196;
    int notDoneCount = 0;
    for (Todo todo : todos) {
      notDoneCount += todo.done ? 0 : 1;

      if (showTodosFilter.equals("done") && todo.done) {
        continue;
      } else if (showTodosFilter.equals("not done") && !todo.done) {
        continue;
      }

      if (todo.done && Widgets.currentFocusId == todo.id) {
        Widgets.setCurrentFocusId(null);
      }

      WidgetStatus todoStatus =
          Widgets.textBox(todo.id, left, top, 550, todoHeight)
              .backgroundColor(Color.WHITE)
              .font("SanSerif", FontStyle.PLAIN, 24, todo.done)
              .margin(60)
              .text(todo.value, todo.done ? Color.GRAY_70 : new Color(77, 77, 77))
              .render();
      if (todoStatus.updated) {
        todo.value = todoStatus.text;
      }
      Widgets.renderRect(left, top, 550, 1, Color.LOW_HAZE);

      Widgets.setFont("SanSerif", FontStyle.PLAIN, 30);
      if (Widgets.button(null, left + 6, top + 8, 45, 45)
          .text(todo.done ? "✔" : "◯")
          .textColor(todo.done ? Color.GRAY_70 : Color.GRAY_90)
          .backgroundColor(null)
          .hoverColor(null)
          .setSelectedColor(null)
          .render()
          .clicked) {
        todo.done = !todo.done;
      }

      if (Platform.mouseLocationIsIn(left, top, 550, todoHeight)) {
        if (Widgets.button(
                null,
                left + 550 - todoHeight + todoHeight / 4,
                top + todoHeight / 4 + 1,
                todoHeight / 2,
                todoHeight / 2)
            .text("✘")
            .textColor(new Color(192, 128, 128))
            .render()
            .clicked) {
          todo.deleted = true;
        }
      }

      top += todoHeight;
    }

    if (!todos.isEmpty()) {
      Color selectedFilterTextColor = new Color(0.60f, 0.30f, 0.30f);
      Color filterTextColor = Color.GRAY_30;

      Widgets.renderRect(left, top, 550, filterHeight, Color.WHITE);
      Widgets.setColor(Color.LOW_HAZE);
      Widgets.renderRect(left, top, 550, 1, Color.LOW_HAZE);

      Widgets.setColor(filterTextColor);
      Widgets.setFont("SanSerif", FontStyle.PLAIN, 14);
      Widgets.renderStringLeft(
          left + 13,
          top,
          550,
          filterHeight,
          notDoneCount + " item" + (notDoneCount > 1 ? "s" : "") + " left");

      Color filterButtonHoverColor = Color.GRAY_95;
      if (Widgets.button(null, left + 200, top + 3, 30, filterHeight - 6)
          .text("All")
          .textColor(showTodosFilter.equals("all") ? selectedFilterTextColor : filterTextColor)
          .backgroundColor(null)
          .hoverColor(filterButtonHoverColor)
          .setSelectedColor(null)
          .render()
          .clicked) {
        showTodosFilter = "all";
      }
      if (Widgets.button(null, left + 240, top + 3, 50, filterHeight - 6)
          .text("Active")
          .textColor(showTodosFilter.equals("done") ? selectedFilterTextColor : filterTextColor)
          .backgroundColor(null)
          .hoverColor(filterButtonHoverColor)
          .setSelectedColor(null)
          .render()
          .clicked) {
        showTodosFilter = "done";
      }
      if (Widgets.button(null, left + 300, top + 3, 80, filterHeight - 6)
          .text("Completed")
          .textColor(showTodosFilter.equals("not done") ? selectedFilterTextColor : filterTextColor)
          .backgroundColor(null)
          .hoverColor(filterButtonHoverColor)
          .setSelectedColor(null)
          .render()
          .clicked) {
        showTodosFilter = "not done";
      }
    }

    todos = todos.stream().filter(t -> !t.deleted).collect(Collectors.toList());
  }
}
