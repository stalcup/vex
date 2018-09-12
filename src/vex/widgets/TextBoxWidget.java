package vex.widgets;

import vex.Color;
import vex.Graphics;
import vex.Graphics.FontStyle;
import vex.Platform;
import vex.Vex;
import vex.Widgets;
import vex.events.KeyEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;

public class TextBoxWidget {

  private Color backgroundColor;
  private int border;
  private Color borderColor;
  private String focusId;
  private int height;
  private int margin;
  private String placeholderText;
  private Color placeholderTextColor;
  private String text;
  private Color textColor;
  private Color underlineColor;
  private int width;
  private int x;
  private int y;
  private String fontName;
  private FontStyle fontStyle;
  private int fontPointSize;
  private boolean fontStrikeThrough;

  public TextBoxWidget(String focusId, int x, int y, int width, int height) {
    this.focusId = focusId;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public TextBoxWidget backgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }

  public TextBoxWidget border(int border, Color borderColor) {
    this.border = border;
    this.borderColor = borderColor;
    return this;
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
    if (Platform.mouseEventIsIn(x, y, width, height, Type.DOWN)) {
      Widgets.currentFocusId = focusId;
      Widgets.textCursorPosition = text.length();
    }

    Graphics g = Vex.platform.getGraphics();
    if (backgroundColor != null) {
      g.setColor(backgroundColor);
      g.fillRect(x, y, width, height);
    }

    if (borderColor != null) {
      g.setColor(borderColor);
      g.setStroke(border);
      g.drawRect(x - border, y - border, width + border * 2 - 1, height + border * 2 - 1);
    }

    if (underlineColor != null) {
      g.setColor(underlineColor);
      g.fillRect(x, y + height - 1, width, 1);
    }

    if (fontName != null) {
      g.setFont(fontName, fontStyle, fontPointSize, fontStrikeThrough);
    }

    if ((text == null || text.isEmpty())
        && placeholderText != null
        && placeholderTextColor != null) {
      g.setColor(placeholderTextColor);
      Widgets.renderStringLeft(x + margin, y, width - margin, height, placeholderText);
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

      int textCursorPixelX = x + margin + textCursorX;
      if (textCursorPixelX < x + width) {
        g.fillRect(textCursorPixelX, y + (height - stringSize.y) / 2, 2, stringSize.y);
      }
    }

    Widgets.renderStringLeft(x + margin, y, width - margin, height, text);

    return WidgetStatus.text(updatedText, text, keyText);
  }

  public TextBoxWidget text(String text, Color textColor) {
    this.text = text;
    this.textColor = textColor;
    return this;
  }

  public TextBoxWidget underlineColor(Color underlineColor) {
    this.underlineColor = underlineColor;
    return this;
  }

  public TextBoxWidget font(String name, FontStyle style, int pointSize, boolean strikeThrough) {
    fontName = name;
    fontStyle = style;
    fontPointSize = pointSize;
    fontStrikeThrough = strikeThrough;
    return this;
  }
}
