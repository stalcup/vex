package todomvc;

import java.util.ArrayList;
import java.util.List;

import vex.BaseStyles;
import vex.Color;
import vex.FontStyle;
import vex.Graphics;
import vex.Platform;
import vex.Rect;
import vex.Strings;
import vex.Vex;
import vex.Widgets;
import vex.widgets.WidgetStatus;

public class TodoMvc {

  private static class Style {
    static Color pageBackgroundColor = new Color(245, 245, 245);

    static String appFontName = "Arial";
    static Color appTitleColor = new Color(175, 47, 47, 38);
    static int appTitleFontSize = 100;
    static Color appBackgroundColor = Color.WHITE;

    static Color smallDropShadowColor = new Color(96, 96, 96, 255 * 3 / 10);
    static int smallDropShadowRadius = 6;
    static int smallDropShadowOffsetY = 1;

    static Color bigDropShadowColor = new Color(225, 225, 225, 255);
    static int bigDropShadowRadius = 25;
    static int bigDropShadowOffsetY = 5;

    static int todoHeight = 59;
    static int todoWidth = 550;
    static int todoFontSize = 24;
    static int newTodoHeight = 66;
    static Color newTodoPlaceholderColor = new Color(230, 230, 230);
    static Color doneTodoTextColor = Color.GRAY_70;
    static Color todoTextColor = new Color(77, 77, 77);
    static Color todoDividerColor = Color.LOW_HAZE;
    static Color deleteTodoButtonColor = new Color(192, 128, 128);
    static Color selectedFilterButtonTextColor = new Color(0.60f, 0.30f, 0.30f);

    static int filterAreaHeight = 40;
    static Color filterButtonTextColor = Color.GRAY_30;
    static Color filterButtonHoverBackgroundColor = Color.GRAY_95;

    static int doneButtonFontSize = 30;
  }

  private static class DataState {
    static List<TodoMvc.Todo> todos = new ArrayList<>();
  }

  private static class UiState {
    static String newTodoDescription = "";
    static String todosFilter = "all";
    static List<Todo> deletedTodos = new ArrayList<>();
  }

  static class Todo {
    String id = Math.random() + "";

    String description;

    boolean done;

    Todo(String description) {
      this.description = description;
    }
  }

  public static void doUi(int x, int y, int width, int height) {
    if (Widgets.getCurrentFocusId() == null) {
      Widgets.setCurrentFocusId("new-todo-textbox");
    }

    Widgets.renderRect(x, y, width, height, Style.pageBackgroundColor);

    Widgets.setColor(Style.appTitleColor);
    Widgets.setFont(Style.appFontName, FontStyle.PLAIN, Style.appTitleFontSize);
    Widgets.renderStringCenteredHorizontal(x, y + 103, width, "todos");

    int left = (width - Style.todoWidth) / 2;

    doDecorations(left);

    WidgetStatus newTodoStatus =
        Widgets.textBox(
                "new-todo-textbox", new Rect(left, 130, Style.todoWidth, Style.newTodoHeight))
            .text(UiState.newTodoDescription)
            .render(
                BaseStyles.textBox()
                    .margin(60)
                    .placeholderText("What needs to be done?", Style.newTodoPlaceholderColor)
                    .backgroundColor(Style.appBackgroundColor)
                    .font(Style.appFontName, FontStyle.PLAIN, Style.todoFontSize, false)
                    .textColor(Style.todoTextColor));
    if (newTodoStatus.updated) {
      UiState.newTodoDescription = newTodoStatus.text;
    } else if (!UiState.newTodoDescription.isEmpty() && "Enter".equals(newTodoStatus.keyText)) {
      DataState.todos.add(new Todo(UiState.newTodoDescription));
      UiState.newTodoDescription = "";
    }

    if (!DataState.todos.isEmpty()) {
      Widgets.setFont(Style.appFontName, FontStyle.PLAIN, Style.doneButtonFontSize);
      if (Widgets.button(new Rect(left + 6, 130 + 8, 45, 45))
          .render(BaseStyles.button().text("✔", Color.GRAY_80))
          .clicked) {
        boolean newState = !DataState.todos.get(0).done;
        for (Todo todo : DataState.todos) {
          todo.done = newState;
        }
      }
    }

    int top = 196;
    int notDoneCount = 0;
    for (Todo todo : DataState.todos) {
      notDoneCount += todo.done ? 0 : 1;

      if (UiState.todosFilter.equals("done") && todo.done) {
        continue;
      } else if (UiState.todosFilter.equals("not done") && !todo.done) {
        continue;
      }

      if (todo.done && Strings.equals(Widgets.getCurrentFocusId(), todo.id)) {
        Widgets.setCurrentFocusId(null);
      }

      WidgetStatus todoStatus =
          Widgets.textBox(todo.id, new Rect(left, top, Style.todoWidth, Style.todoHeight))
              .text(todo.description)
              .render(
                  BaseStyles.textBox()
                      .margin(60)
                      .backgroundColor(Style.appBackgroundColor)
                      .font(Style.appFontName, FontStyle.PLAIN, 24, todo.done)
                      .textColor(todo.done ? Style.doneTodoTextColor : Style.todoTextColor));
      if (todoStatus.updated) {
        todo.description = todoStatus.text;
      }
      Widgets.renderRect(left, top, Style.todoWidth, 1, Style.todoDividerColor);

      Widgets.setFont(Style.appFontName, FontStyle.PLAIN, 30);
      if (Widgets.button(new Rect(left + 6, top + 8, 45, 45))
          .render(BaseStyles.button().text(todo.done ? "✔" : "◯", Style.doneTodoTextColor))
          .clicked) {
        todo.done = !todo.done;
      }

      if (Platform.mouseLocationIsIn(left, top, Style.todoWidth, Style.todoHeight)) {
        if (Widgets.button(
                new Rect(
                    left + Style.todoWidth - Style.todoHeight + Style.todoHeight / 4,
                    top + Style.todoHeight / 4 + 1,
                    Style.todoHeight / 2,
                    Style.todoHeight / 2))
            .render(BaseStyles.button().text("✘", Style.deleteTodoButtonColor))
            .clicked) {
          UiState.deletedTodos.add(todo);
        }
      }

      top += Style.todoHeight;
    }

    if (!DataState.todos.isEmpty()) {

      Widgets.renderRect(
          left, top, Style.todoWidth, Style.filterAreaHeight, Style.appBackgroundColor);
      Widgets.renderRect(left, top, Style.todoWidth, 1, Style.todoDividerColor);

      Widgets.setColor(Style.filterButtonTextColor);
      Widgets.setFont(Style.appFontName, FontStyle.PLAIN, 14);
      Widgets.renderStringLeft(
          left + 13,
          top,
          Style.todoWidth,
          Style.filterAreaHeight,
          notDoneCount + " item" + (notDoneCount > 1 ? "s" : "") + " left");

      if (Widgets.button(new Rect(left + 200, top + 3, 30, Style.filterAreaHeight - 6))
          .render(
              BaseStyles.button()
                  .text(
                      "All",
                      UiState.todosFilter.equals("all")
                          ? Style.selectedFilterButtonTextColor
                          : Style.filterButtonTextColor)
                  .hoverBackgroundColor(Style.filterButtonHoverBackgroundColor))
          .clicked) {
        UiState.todosFilter = "all";
      }
      if (Widgets.button(new Rect(left + 240, top + 3, 50, Style.filterAreaHeight - 6))
          .render(
              BaseStyles.button()
                  .text(
                      "Active",
                      UiState.todosFilter.equals("done")
                          ? Style.selectedFilterButtonTextColor
                          : Style.filterButtonTextColor)
                  .hoverBackgroundColor(Style.filterButtonHoverBackgroundColor))
          .clicked) {
        UiState.todosFilter = "done";
      }
      if (Widgets.button(new Rect(left + 300, top + 3, 80, Style.filterAreaHeight - 6))
          .render(
              BaseStyles.button()
                  .text(
                      "Completed",
                      UiState.todosFilter.equals("not done")
                          ? Style.selectedFilterButtonTextColor
                          : Style.filterButtonTextColor)
                  .hoverBackgroundColor(Style.filterButtonHoverBackgroundColor))
          .clicked) {
        UiState.todosFilter = "not done";
      }
    }

    DataState.todos.removeAll(UiState.deletedTodos);
    UiState.deletedTodos.clear();
  }

  private static void doDecorations(int left) {
    int dropShadowHeight = 66;
    for (Todo todo : DataState.todos) {
      if (UiState.todosFilter.equals("done") && todo.done) {
        continue;
      } else if (UiState.todosFilter.equals("not done") && !todo.done) {
        continue;
      }
      dropShadowHeight += Style.todoHeight;
    }

    Widgets.setColor(Style.bigDropShadowColor);
    Graphics g = Vex.platform.getGraphics();
    g.drawDropShadow(
        left,
        130,
        Style.todoWidth,
        dropShadowHeight + (DataState.todos.isEmpty() ? 0 : Style.filterAreaHeight),
        0,
        Style.bigDropShadowOffsetY + (DataState.todos.isEmpty() ? 0 : 10),
        Style.bigDropShadowRadius);

    if (!DataState.todos.isEmpty()) {
      Widgets.setColor(Style.smallDropShadowColor);
      g.drawDropShadow(
          left + 6,
          130 + dropShadowHeight + 10,
          Style.todoWidth - 12,
          Style.filterAreaHeight,
          0,
          Style.smallDropShadowOffsetY,
          Style.smallDropShadowRadius);
      Widgets.renderRect(
          left + 6,
          130 + dropShadowHeight + 10,
          Style.todoWidth - 12,
          Style.filterAreaHeight,
          Style.appBackgroundColor);

      Widgets.setColor(Style.smallDropShadowColor);
      g.drawDropShadow(
          left + 3,
          130 + dropShadowHeight + 5,
          Style.todoWidth - 6,
          Style.filterAreaHeight,
          0,
          Style.smallDropShadowOffsetY,
          Style.smallDropShadowRadius);
      Widgets.renderRect(
          left + 3,
          130 + dropShadowHeight + 5,
          Style.todoWidth - 6,
          Style.filterAreaHeight,
          Style.appBackgroundColor);
    }

    Widgets.setColor(Style.smallDropShadowColor);
    g.drawDropShadow(
        left,
        130,
        Style.todoWidth,
        dropShadowHeight + (DataState.todos.isEmpty() ? 0 : Style.filterAreaHeight),
        0,
        Style.smallDropShadowOffsetY,
        Style.smallDropShadowRadius);
  }
}
