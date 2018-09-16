package match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import vex.Color;
import vex.FontStyle;
import vex.Widgets;
import vex.geom.Point;
import vex.widgets.ButtonStyle;
import vex.widgets.Style;

public class MatchGame {

  private static class Styles {
    static ButtonStyle<?> gridButtonStyle =
        Widgets.buttonStyle().border(2, Color.BLACK).cornerRadius(10).textColor(Color.GRAY_10);

    static Style<?> infoLabelStyle =
        Widgets.style()
            .textColor(Color.BLACK)
            .backgroundColor(new Color(196, 196, 255))
            .border(2, Color.BLACK)
            .cornerRadius(10);

    static ButtonStyle<?> confirmationButtonsStyle =
        Widgets.buttonStyle()
            .textColor(Color.BLACK)
            .border(2, Color.BLACK)
            .hoverColor(Color.MEDIUM_MIST)
            .cornerRadius(10);

    static Color resetButtonColor = Color.GRAY_60;

    static int gridCellGap = 10;
  }

  static class DataState {
    static int gridSize = 7;
    static int clickCounter = 0;
    static int[][] values = new int[gridSize][gridSize];
    static Set<Point> matchedButtonIndexes = new HashSet<>();
  }

  private static class UiState {
    static boolean confirmingReset = false;
    static Set<Point> selectedButtonIndexes = new HashSet<>();
    static int smallerDimension;
    static int squareSize;
  }

  public static void doUi(int x, int y, int width, int height) {
    UiState.smallerDimension = Math.min(width, height);
    UiState.squareSize =
        (UiState.smallerDimension - Styles.gridCellGap * (DataState.gridSize + 1))
            / DataState.gridSize;

    Widgets.renderRect(x, y, width, height, Color.GRAY_95);
    Widgets.setFont("Arial", FontStyle.BOLD, 20);

    for (int cellX = 0; cellX < DataState.gridSize; cellX++) {
      for (int cellY = 0; cellY < DataState.gridSize; cellY++) {
        Point buttonIndex = new Point(cellX, cellY);
        if (DataState.matchedButtonIndexes.contains(buttonIndex)) {
          continue;
        }

        boolean isResetButton =
            DataState.values[cellX][cellY] == 1 + DataState.gridSize * DataState.gridSize / 2;
        boolean isSelected = UiState.selectedButtonIndexes.contains(buttonIndex);

        String buttonLabel = "";
        if (isResetButton) {
          buttonLabel = "Reset";
        } else if (isSelected) {
          buttonLabel = DataState.values[cellX][cellY] + "";
        }

        if (Widgets.button(
                Styles.gridCellGap + cellX * (Styles.gridCellGap + UiState.squareSize),
                Styles.gridCellGap + cellY * (Styles.gridCellGap + UiState.squareSize),
                UiState.squareSize,
                UiState.squareSize)
            .render(
                Styles.gridButtonStyle
                    .backgroundColor(
                        isResetButton
                            ? Styles.resetButtonColor
                            : (isSelected ? Color.WHITE : Color.GRAY_60))
                    .hoverColor(UiState.confirmingReset ? null : Color.MEDIUM_MIST)
                    .borderWidth(isSelected ? 4 : 2)
                    .text(buttonLabel))
            .clicked) {
          if (UiState.confirmingReset) {
            continue;
          }
          handleGridButtonClicked(buttonIndex, isResetButton);
        }
      }
    }

    if (UiState.confirmingReset) {
      Widgets.renderRect(0, 0, UiState.smallerDimension, UiState.smallerDimension, Color.HIGH_MIST);
    }

    Widgets.setFont("Impact", FontStyle.PLAIN, UiState.squareSize / 2);

    Widgets.area(
            height < width ? UiState.smallerDimension : Styles.gridCellGap * 2 + UiState.squareSize,
            height < width ? Styles.gridCellGap * 2 + UiState.squareSize : UiState.smallerDimension,
            UiState.squareSize * 5 / 2,
            UiState.squareSize)
        .render(Styles.infoLabelStyle.text("Clicks: " + DataState.clickCounter));

    Widgets.area(
            height < width
                ? UiState.smallerDimension
                : Styles.gridCellGap * 3 + UiState.squareSize * 2,
            height < width
                ? Styles.gridCellGap * 3 + UiState.squareSize * 2
                : UiState.smallerDimension,
            UiState.squareSize * 5 / 2,
            UiState.squareSize)
        .render(
            Styles.infoLabelStyle.text(
                +DataState.matchedButtonIndexes.size() / 2
                    + " / "
                    + ((DataState.gridSize * DataState.gridSize) / 2)));

    if (UiState.confirmingReset) {
      Widgets.setFont("Arial", FontStyle.BOLD, 20);
      if (Widgets.button(
              UiState.smallerDimension / 2,
              UiState.smallerDimension / 2,
              UiState.squareSize,
              UiState.squareSize)
          .render(
              Styles.confirmationButtonsStyle.text("Yes").backgroundColor(Styles.resetButtonColor))
          .clicked) {
        UiState.confirmingReset = false;
        UiState.selectedButtonIndexes.clear();
        DataState.matchedButtonIndexes.clear();
        DataState.clickCounter = 0;
      }
      if (Widgets.button(
              UiState.smallerDimension / 2 + Styles.gridCellGap + UiState.squareSize,
              UiState.smallerDimension / 2,
              UiState.squareSize,
              UiState.squareSize)
          .render(
              Styles.confirmationButtonsStyle.text("No").backgroundColor(Styles.resetButtonColor))
          .clicked) {
        UiState.confirmingReset = false;
      }
    }
  }

  private static void handleGridButtonClicked(Point buttonIndex, boolean isResetButton) {
    randomizeResetButtonColor();

    if (isResetButton) {
      UiState.confirmingReset = true;
    } else {
      DataState.clickCounter++;
      if (UiState.selectedButtonIndexes.size() == 2) {
        UiState.selectedButtonIndexes.clear();
      }
      if (!DataState.matchedButtonIndexes.contains(buttonIndex)) {
        UiState.selectedButtonIndexes.add(buttonIndex);
        checkMatches();
      }
    }
  }

  private static void checkMatches() {
    if (UiState.selectedButtonIndexes.size() != 2) {
      return;
    }

    Iterator<Point> iterator = UiState.selectedButtonIndexes.iterator();
    Point buttonIndex1 = iterator.next();
    Point buttonIndex2 = iterator.next();

    if (DataState.values[buttonIndex1.x][buttonIndex1.y]
        == DataState.values[buttonIndex2.x][buttonIndex2.y]) {
      DataState.matchedButtonIndexes.addAll(UiState.selectedButtonIndexes);
      UiState.selectedButtonIndexes.clear();
    }
  }

  private static void randomizeResetButtonColor() {
    Styles.resetButtonColor =
        new Color(
            192 + (int) (Math.random() * 64),
            192 + (int) (Math.random() * 64),
            192 + (int) (Math.random() * 64));
  }

  private static void initializeRandomValuesGrid() {
    List<Integer> randomizedValues = new ArrayList<>();
    for (int i = 0; i < DataState.gridSize * DataState.gridSize; i++) {
      randomizedValues.add(1 + i / 2);
    }
    Collections.shuffle(randomizedValues);
    for (int i = 0; i < DataState.gridSize; i++) {
      for (int j = 0; j < DataState.gridSize; j++) {
        DataState.values[i][j] = randomizedValues.remove(0);
      }
    }
  }

  static {
    initializeRandomValuesGrid();
    randomizeResetButtonColor();
  }
}
