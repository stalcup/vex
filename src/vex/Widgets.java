package vex;

import java.util.List;
import java.util.Map;
import java.util.Set;

import vex.events.MouseEvent.Type;
import vex.geom.Rect;
import vex.styles.ButtonStyle;
import vex.widgets.ButtonWidget;
import vex.widgets.DatePickerWidget;
import vex.widgets.DateTextBoxWidget;
import vex.widgets.RadioButtonWidget;
import vex.widgets.RadioButtonsWidget;
import vex.widgets.SelectWidget;
import vex.widgets.TextBoxWidget;
import vex.widgets.Widget;
import vex.widgets.WidgetStatus;

public class Widgets {

  private static boolean focusNext = true;
  private static String lastFocusId;
  private static String currentFocusId;
  private static int focusSetOnFrameId;

  public static float doVerticalScrollBar(
      String focusId,
      int x,
      int y,
      int width,
      int height,
      int visibleRange,
      int totalRange,
      float scrollPercent,
      Color backgroundColor,
      Color barColor) {
    scrollPercent = Math.max(0, scrollPercent);
    scrollPercent = Math.min(100, scrollPercent);

    int visiblePixels = (int) (height * visibleRange / (float) totalRange);
    int hiddenPixels = height - visiblePixels;
    int offsetPixels = (int) (hiddenPixels * scrollPercent / 100f);

    if (backgroundColor != null) {
      Vex.setColor(backgroundColor);
      Vex.fillRect(x, y, width, height);
    }

    Vex.setColor(barColor);
    Vex.fillRect(x, y + offsetPixels, width, visiblePixels);

    if (Vex.mouseLocationIsIn(x, y, width, height)
        || Strings.equals(getCurrentFocusId(), focusId)) {
      Vex.fillRect(x, y + offsetPixels, width, visiblePixels);
    }

    if (Vex.mouseEventIsIn(x, y, width, height, Type.DOWN)) {
      setCurrentFocusId(focusId);
      // Aka, start dragging.
    }

    if (Strings.equals(getCurrentFocusId(), focusId)
        && Vex.getMouseEvent() != null
        && Vex.getMouseEvent().type == Type.DRAG) {
      scrollPercent += Vex.getMouseEvent().delta.y * 100f / hiddenPixels;

      scrollPercent = Math.max(0, scrollPercent);
      scrollPercent = Math.min(100, scrollPercent);
    }

    if (Strings.equals(getCurrentFocusId(), focusId) && Vex.mouseEventIs(Type.UP)) {
      setCurrentFocusId(null);
    }

    return scrollPercent;
  }

  public static ButtonWidget label(Rect rect) {
    return button(rect);
  }

  public static ButtonWidget button(Rect rect) {
    return new ButtonWidget(rect);
  }

  public static TextBoxWidget textBox(String focusId, Rect rect) {
    return new TextBoxWidget(rect).focusId(focusId);
  }

  public static RadioButtonWidget radioButton(String focusId, Rect radioButtonBounds) {
    return new RadioButtonWidget(focusId, radioButtonBounds);
  }

  public static RadioButtonsWidget radioButtons(
      Rect radioButtonsBounds,
      Map<String, String> selectableKeysByDisplayValue,
      String selectedKey) {
    return new RadioButtonsWidget(radioButtonsBounds, selectableKeysByDisplayValue, selectedKey);
  }

  public static Widget area(Rect rect) {
    return new Widget(rect);
  }

  public static WidgetStatus startRawDropDown(
      boolean open,
      String focusId,
      boolean disabled,
      String selectionsPreview,
      Rect closedRect,
      ButtonStyle<?> buttonStyle,
      int optionsCount,
      int maxDisplayRowCount) {
    if (selectionsPreview != null) {
      buttonStyle.text(selectionsPreview);
    }
    if (button(closedRect).disabled(disabled).focusId(focusId).render(buttonStyle).clicked) {
      open = true;
      Vex.consumeMouseEvent();
    }

    int displayRowCount = Math.min(optionsCount, maxDisplayRowCount);

    List<Rect> rowRects = null;

    if (open) {
      Vex.beginLayer();

      Rect dropDownRect =
          closedRect
              .dupe("dropDown")
              .scaleHeight(displayRowCount * 0.8)
              .panDown(closedRect.height + 10);
      rowRects = dropDownRect.asRows(displayRowCount, "row");

      if (Vex.mouseEventIs(Type.DOWN) && !Vex.mouseEventIsIn(dropDownRect, Type.DOWN)) {
        open = false;
      }
    }

    return WidgetStatus.open(open, rowRects);
  }

  public static SelectWidget textDropDown(
      Rect closedBounds, List<String> options, Set<String> selections) {
    return new SelectWidget(closedBounds, options, selections);
  }

  public static DatePickerWidget datePicker(Rect bounds) {
    return new DatePickerWidget(bounds);
  }

  public static DateTextBoxWidget dateTextBox(String focusId, Rect bounds) {
    return new DateTextBoxWidget(focusId, bounds);
  }

  public static void maybeFocusMeNext(String focusId) {
    if (focusNext) {
      focusNext = false;
      setCurrentFocusId(focusId);
      Vex.consumeKeyEvent();
    }
  }

  public static String getCurrentFocusId() {
    return currentFocusId;
  }

  public static void setCurrentFocusId(String currentFocusId) {
    lastFocusId = Widgets.currentFocusId;
    Widgets.currentFocusId = currentFocusId;
    focusSetOnFrameId = Vex.getFrameid();
  }

  public static String getLastFocusId() {
    return lastFocusId;
  }

  public static void clearFocusIfNotSetThisFrame() {
    if (focusSetOnFrameId != Vex.getFrameid()) {
      setCurrentFocusId(null);
    }
  }

  public static void setLastFocusId(String lastFocusId) {
    Widgets.lastFocusId = lastFocusId;
  }

  public static boolean lostFocus(String focusId) {
    return focusId != null
        && focusId.equals(lastFocusId)
        && focusSetOnFrameId == Vex.getFrameid() - 1;
  }

  public static void clickOutClearFocus(Rect bounds) {
    if (Vex.mouseEventIsIn(bounds, Type.DOWN)) {
      clearFocusIfNotSetThisFrame();
    }
  }

  public static void unfocusedTabFocusNext() {
    if (getCurrentFocusId() == null
        && Vex.getKeyEvent() != null
        && "Tab".equals(Vex.getKeyEvent().keyText)) {
      focusNext = true;
    }
  }

  public static boolean shouldFocusNext() {
    return focusNext;
  }

  public static void setFocusNext(boolean focusNext) {
    Widgets.focusNext = focusNext;
    Vex.repaint();
  }
}
