package vex.widgets;

import vex.Color;
import vex.Graphics;
import vex.Platform;
import vex.Vex;
import vex.Widgets;
import vex.events.KeyEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;

public class TextBoxWidget extends AreaWidget {

  private static int NOT_SET = -1;

  private String focusId;
  private int margin = NOT_SET;
  private String placeholderText;
  private Color placeholderTextColor;
  private String text;
  private Color textColor;
  private Color underlineColor;

  public TextBoxWidget(String focusId, int x, int y, int width, int height) {
    super(x, y, width, height);
    this.focusId = focusId;
  }

  public TextBoxWidget margin(int margin) {
    this.margin = margin;
    return this;
  }

  public TextBoxWidget placeholderText(String placeholderText, Color placeholderTextColor) {
    this.placeholderText = placeholderText;
    this.placeholderTextColor = placeholderTextColor;
    return this;
  }

  public WidgetStatus render() {
    super.render();

    if (Platform.mouseEventIsIn(x, y, width, height, Type.DOWN)) {
      Widgets.currentFocusId = focusId;
      Widgets.textCursorPosition = text.length();
    }

    Graphics g = Vex.platform.getGraphics();
    if (underlineColor != null) {
      g.setColor(underlineColor);
      g.fillRect(x, y + height - 1, width, 1);
    }

    int effectiveMargin = margin == NOT_SET ? fontPointSize / 2 : margin;

    if ((text == null || text.isEmpty())
        && placeholderText != null
        && placeholderTextColor != null) {
      g.setColor(placeholderTextColor);
      Widgets.renderStringLeft(
          x + effectiveMargin, y, width - effectiveMargin, height, placeholderText);
    }

    boolean updatedText = false;
    String keyText = null;

    g.setColor(textColor);
    boolean focused = Widgets.currentFocusId == focusId;
    if (focused) {
      KeyEvent keyEvent = Vex.platform.getKeyEvent();
      if (keyEvent != null) {
        if (keyEvent.keyText.equals("Left")) {
          Widgets.textCursorPosition = Widgets.textCursorPosition - 1;
        } else if (keyEvent.keyText.equals("Right")) {
          Widgets.textCursorPosition = Widgets.textCursorPosition + 1;
        }
      }
      Widgets.textCursorPosition = Math.min(Widgets.textCursorPosition, text.length());
      Widgets.textCursorPosition = Math.max(Widgets.textCursorPosition, 0);

      if (keyEvent != null) {
        boolean delete = keyEvent.keyText.equals("Delete");
        boolean backspace = keyEvent.keyText.equals("Backspace");
        keyText = keyEvent.keyText;
        if (keyEvent.printable && !"Enter".equals(keyEvent.keyText) || delete || backspace) {
          String left =
              Widgets.textCursorPosition > 0 ? text.substring(0, Widgets.textCursorPosition) : "";
          String right =
              Widgets.textCursorPosition < text.length()
                  ? text.substring(Widgets.textCursorPosition)
                  : "";

          // Process these as mutually exclusive states, even though on some OS's the KeyEvent does
          // not make that clear.
          if (delete && Widgets.textCursorPosition < text.length()) {
            text = left + right.substring(1);
            updatedText = true;
          } else if (backspace && Widgets.textCursorPosition > 0) {
            text = left.substring(0, left.length() - 1) + right;
            Widgets.textCursorPosition = Widgets.textCursorPosition - 1;
            updatedText = true;
          } else if (keyEvent.printable) {
            text = left + keyEvent.key + right;
            Widgets.textCursorPosition = Widgets.textCursorPosition + 1;
            updatedText = true;
          }
        }
      }

      Point stringSize = Widgets.getStringSize(text.substring(0, Widgets.textCursorPosition));
      int textCursorX = stringSize.x;

      int textCursorPixelX = x + effectiveMargin + textCursorX;
      if (textCursorPixelX < x + width) {
        g.fillRect(textCursorPixelX, y + (height - stringSize.y) / 2, 2, stringSize.y);
      }
    }

    Widgets.renderStringLeft(x + effectiveMargin, y, width - effectiveMargin, height, text);

    return WidgetStatus.text(updatedText, text, keyText);
  }

  @Override
  public TextBoxWidget text(String text, Color textColor) {
    this.text = text;
    this.textColor = textColor;
    return this;
  }

  public TextBoxWidget underlineColor(Color underlineColor) {
    this.underlineColor = underlineColor;
    return this;
  }
}
