package vex;

import java.util.List;
import java.util.Map;
import java.util.Set;

import vex.events.MouseEvent.Type;
import vex.geom.Point;
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

  public static boolean focusNext = true;
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

    if (Platform.mouseLocationIsIn(x, y, width, height)
        || Strings.equals(getCurrentFocusId(), focusId)) {
      Vex.fillRect(x, y + offsetPixels, width, visiblePixels);
    }

    if (Platform.mouseEventIsIn(x, y, width, height, Type.DOWN)) {
      setCurrentFocusId(focusId);
      // Aka, start dragging.
    }

    if (Strings.equals(getCurrentFocusId(), focusId)
        && Vex.platform.getMouseEvent() != null
        && Vex.platform.getMouseEvent().type == Type.DRAG) {
      scrollPercent += Vex.platform.getMouseEvent().delta.y * 100f / hiddenPixels;

      scrollPercent = Math.max(0, scrollPercent);
      scrollPercent = Math.min(100, scrollPercent);
    }

    if (Strings.equals(getCurrentFocusId(), focusId) && Platform.mouseEventIs(Type.UP)) {
      setCurrentFocusId(null);
    }

    return scrollPercent;
  }

  public static Point getStringSize(String text) {
    return Vex.getSize(text);
  }

  public static void renderInnerBorder(int x, int y, int width, int height, int border) {
    Vex.setStroke(border);
    Vex.drawRect(x, y, width - border, height - border);
  }

  public static void renderRect(int x, int y, int width, int height, Color color) {
    Vex.setColor(color);
    Vex.fillRect(x, y, width, height);
  }

  public static void renderRect(Rect rect, Color color) {
    renderRect(rect.x, rect.y, rect.width, rect.height, color);
  }

  public static void renderStringCenteredBoth(int x, int y, int width, int height, String text) {
    Point stringSize = getStringSize(text);
    Vex.drawString(
        text,
        x + width / 2 - stringSize.x / 2,
        y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f),
        x,
        y,
        width,
        height);
  }

  public static void renderStringCenteredHorizontal(int x, int y, int width, String text) {
    Point stringSize = getStringSize(text);
    Vex.drawString(text, x + width / 2 - stringSize.x / 2, y);
  }

  public static void renderAlignedString(Rect rect, String text, Align horizontalAlignment) {
    renderAlignedString(rect.x, rect.y, rect.width, rect.height, text, horizontalAlignment);
  }

  public static void renderAlignedString(
      int x, int y, int width, int height, String text, Align horizontalAlignment) {
    Point stringSize = getStringSize(text);

    if (horizontalAlignment == Align.MIN) {
      Vex.drawString(
          text,
          x,
          y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f),
          x,
          y,
          width,
          height);
    } else if (horizontalAlignment == Align.MID) {
      Vex.drawString(
          text,
          x + width / 2 - stringSize.x / 2,
          y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f));
    } else if (horizontalAlignment == Align.MAX) {
      Vex.drawString(
          text,
          x + width - stringSize.x,
          y + height / 2 + stringSize.y / 2 - Math.round(stringSize.y / 6f),
          x,
          y,
          width,
          height);
    }
  }

  public static void renderAlignedImage(
      int x,
      int y,
      int width,
      int height,
      Base64Image image,
      Align horizontalAlignment,
      int imageShiftX,
      int imageShiftY) {
    if (horizontalAlignment == Align.MIN) {
      Vex.drawImage(x + imageShiftX, y + imageShiftY + (height - image.height) / 2, image);
    } else if (horizontalAlignment == Align.MID) {
      Vex.drawImage(
          x + imageShiftX + (width - image.width) / 2,
          y + imageShiftY + (height - image.height) / 2,
          image);
    } else if (horizontalAlignment == Align.MAX) {
      Vex.drawImage(
          x + imageShiftX + width - image.width,
          y + imageShiftY + (height - image.height) / 2,
          image);
    }
  }

  public static void renderStringLeft(int x, int y, int width, int height, String text) {
    renderAlignedString(x, y, width, height, text, Align.MIN);
  }

  public static void renderStringRight(int x, int y, int width, int height, String text) {
    renderAlignedString(x, y, width, height, text, Align.MAX);
  }

  public static void renderTitleBar(
      int x, int y, int width, int height, String title, Color backgroundColor, Color textColor) {
    Vex.setColor(backgroundColor);
    Vex.fillRect(x, y, width, height);

    Vex.setColor(textColor);
    renderStringCenteredBoth(x, y, width, height, title);
  }

  public static ButtonWidget label(Rect rect) {
    return button(rect);
  }

  public static ButtonWidget button(Rect rect) {
    return new ButtonWidget(rect);
  }

  public static TextBoxWidget textBox(String focusId, Rect rect) {
    return new TextBoxWidget(focusId, rect);
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
      int optionsCount) {
    if (selectionsPreview != null) {
      buttonStyle.text(selectionsPreview);
    }
    if (button(closedRect).disabled(disabled).focusId(focusId).render(buttonStyle).clicked) {
      open = true;
      Vex.platform.consumeMouseEvent();
    }

    int displayRowCount = Math.min(optionsCount, 5);
    Rect openRect =
        closedRect.dupe().scaleHeight(displayRowCount * 0.8).panDown(closedRect.height + 10);

    List<Rect> rowRects = null;

    if (open) {
      Vex.platform.beginLayer();
      rowRects = openRect.dupe().asRows(displayRowCount);
    }

    if (open && Platform.mouseEventIs(Type.DOWN) && !Platform.mouseEventIsIn(openRect, Type.DOWN)) {
      open = false;
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
      Vex.platform.consumeKeyEvent();
    }
  }

  public static String getCurrentFocusId() {
    return currentFocusId;
  }

  public static void setCurrentFocusId(String currentFocusId) {
    lastFocusId = Widgets.currentFocusId;
    Widgets.currentFocusId = currentFocusId;
    focusSetOnFrameId = Vex.platform.getFrameid();
  }

  public static String getLastFocusId() {
    return lastFocusId;
  }

  public static void clearFocusIfNotSetThisFrame() {
    if (focusSetOnFrameId != Vex.platform.getFrameid()) {
      setCurrentFocusId(null);
    }
  }

  public static void setLastFocusId(String lastFocusId) {
    Widgets.lastFocusId = lastFocusId;
  }

  public static boolean lostFocus(String focusId) {
    return focusId != null
        && focusId.equals(lastFocusId)
        && focusSetOnFrameId == Vex.platform.getFrameid() - 1;
  }

  public static void clickOutClearFocus(Rect bounds) {
    if (Platform.mouseEventIsIn(bounds, Type.DOWN)) {
      clearFocusIfNotSetThisFrame();
    }
  }

  public static void unfocusedTabFocusNext() {
    if (getCurrentFocusId() == null
        && Vex.platform.getKeyEvent() != null
        && "Tab".equals(Vex.platform.getKeyEvent().keyText)) {
      focusNext = true;
    }
  }
}
