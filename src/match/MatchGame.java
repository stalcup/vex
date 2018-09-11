package match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import vex.Color;
import vex.Graphics.FontStyle;
import vex.Widgets;
import vex.geom.Point;

public class MatchGame {

  private static Color resetButtonColor = Color.GRAY_60;
  private static boolean confirmingReset = false;
  private static int clickCounter = 0;
  private static int gridSize = 7;
  private static int[][] values = new int[gridSize][gridSize];
  private static Set<Point> matchedButtonIndexes = new HashSet<>();
  private static Set<Point> selectedButtonIndexes = new HashSet<>();

  public static void doUi(int x, int y, int width, int height) {
    Widgets.renderRect(x, y, width, height, Color.GRAY_95);

    Widgets.setFont("Arial", FontStyle.BOLD, 20);

    int smallerDimension = Math.min(width, height);

    int gapSize = 10;
    int squareSize = (smallerDimension - (gapSize * (gridSize + 1))) / gridSize;

    for (int i = 0; i < gridSize; i++) {
      for (int k = 0; k < gridSize; k++) {
        String buttonLabel = "";
        Point buttonIndex = new Point(i, k);

        boolean isMatched = matchedButtonIndexes.contains(buttonIndex);
        boolean isResetButton = values[i][k] == 1 + gridSize * gridSize / 2;

        if (selectedButtonIndexes.contains(buttonIndex) || isMatched) {
          buttonLabel = values[i][k] + "";
        }
        if (isResetButton) {
          buttonLabel = "Reset";
        }

        Color buttonColor = isResetButton || isMatched ? resetButtonColor : Color.GRAY_60;
        Color mouseOverColor = isResetButton || isMatched ? resetButtonColor : Color.GRAY_80;

        if (Widgets.button(
                null,
                gapSize + i * (gapSize + squareSize),
                gapSize + k * (gapSize + squareSize),
                squareSize,
                squareSize)
            .text(buttonLabel)
            .textColor(Color.GRAY_10)
            .backgroundColor(buttonColor)
            .hoverColor(mouseOverColor)
            .render()
            .clicked) {

          if (confirmingReset) {
            continue;
          }

          randomizeResetButtonColor();

          if (isResetButton) {
            confirmingReset = true;
          } else {
            clickCounter++;
            if (selectedButtonIndexes.size() == 2) {
              selectedButtonIndexes.clear();
            }
            selectedButtonIndexes.add(buttonIndex);
            checkMatches();
          }
        }
      }
    }

    Widgets.setFont("Impact", FontStyle.PLAIN, squareSize / 2);
    Widgets.button(
            null,
            height < width ? smallerDimension : gapSize * 2 + squareSize,
            height < width ? gapSize * 2 + squareSize : smallerDimension,
            squareSize * 5 / 2,
            squareSize)
        .text("Clicks: " + clickCounter)
        .textColor(Color.BLACK)
        .backgroundColor(new Color(196, 196, 255))
        .hoverColor(null)
        .setSelectedColor(null)
        .render();

    if (confirmingReset) {
      Widgets.setFont("Arial", FontStyle.BOLD, 20);
      if (Widgets.button(
              null,
              height < width ? smallerDimension : gapSize,
              height < width ? gapSize : smallerDimension,
              squareSize,
              squareSize)
          .text("Yes")
          .textColor(Color.BLACK)
          .backgroundColor(resetButtonColor)
          .hoverColor(null)
          .setSelectedColor(null)
          .render()
          .clicked) {
        confirmingReset = false;
        selectedButtonIndexes.clear();
        matchedButtonIndexes.clear();
        clickCounter = 0;
      }
      if (Widgets.button(
              null,
              height < width ? smallerDimension + gapSize + squareSize : gapSize,
              height < width ? gapSize : smallerDimension + gapSize + squareSize,
              squareSize,
              squareSize)
          .text("No")
          .textColor(Color.BLACK)
          .backgroundColor(resetButtonColor)
          .hoverColor(null)
          .setSelectedColor(null)
          .render()
          .clicked) {
        confirmingReset = false;
      }
    }
  }

  private static void checkMatches() {
    if (selectedButtonIndexes.size() != 2) {
      return;
    }

    Iterator<Point> iterator = selectedButtonIndexes.iterator();
    Point buttonIndex1 = iterator.next();
    Point buttonIndex2 = iterator.next();

    if (values[buttonIndex1.x][buttonIndex1.y] == values[buttonIndex2.x][buttonIndex2.y]) {
      matchedButtonIndexes.addAll(selectedButtonIndexes);
      selectedButtonIndexes.clear();
    }
  }

  private static void randomizeResetButtonColor() {
    resetButtonColor =
        new Color(
            192 + (int) (Math.random() * 64),
            192 + (int) (Math.random() * 64),
            192 + (int) (Math.random() * 64));
  }

  private static void initializeRandomValuesGrid() {
    List<Integer> randomizedValues = new ArrayList<>();
    for (int i = 0; i < gridSize * gridSize; i++) {
      randomizedValues.add(1 + i / 2);
    }
    Collections.shuffle(randomizedValues);
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        values[i][j] = randomizedValues.remove(0);
      }
    }
  }

  static {
    initializeRandomValuesGrid();
    randomizeResetButtonColor();
  }
}
