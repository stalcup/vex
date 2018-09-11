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
      Widgets.setCurrentFocusId(focusId);
      Vex.platform.setTextCursorPosition(text.length());
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
    boolean focused = Widgets.getCurrentFocusId() == focusId;
    if (focused) {
      KeyEvent keyEvent = Vex.platform.getKeyEvent();
      if (keyEvent != null) {
        if (keyEvent.keyText.equals("Left")) {
          Vex.platform.setTextCursorPosition(Vex.platform.getTextCursorPosition() - 1);
        } else if (keyEvent.keyText.equals("Right")) {
          Vex.platform.setTextCursorPosition(Vex.platform.getTextCursorPosition() + 1);
        }
      }
      Vex.platform.setTextCursorPosition(
          Math.min(Vex.platform.getTextCursorPosition(), text.length()));
      Vex.platform.setTextCursorPosition(Math.max(Vex.platform.getTextCursorPosition(), 0));

      if (keyEvent != null) {
        Vex.platform.getKeyEvent();
        boolean delete = keyEvent.keyText.equals("Delete");
        boolean backspace = keyEvent.keyText.equals("Backspace");
        keyText = keyEvent.keyText;
        if ((keyEvent.printable && !"Enter".equals(keyEvent.keyText)) || delete || backspace) {
          String left =
              Vex.platform.getTextCursorPosition() > 0
                  ? text.substring(0, Vex.platform.getTextCursorPosition())
                  : "";
          String right =
              Vex.platform.getTextCursorPosition() < text.length()
                  ? text.substring(Vex.platform.getTextCursorPosition())
                  : "";

          if (keyEvent.printable) {
            text = left + keyEvent.key + right;
            Vex.platform.setTextCursorPosition(Vex.platform.getTextCursorPosition() + 1);
            updatedText = true;
          }
          if (delete && Vex.platform.getTextCursorPosition() < text.length()) {
            text = left + right.substring(1);
            updatedText = true;
          }
          if (backspace && Vex.platform.getTextCursorPosition() > 0) {
            text = left.substring(0, left.length() - 1) + right;
            Vex.platform.setTextCursorPosition(Vex.platform.getTextCursorPosition() - 1);
            updatedText = true;
          }
        }
      }

      Point stringSize =
          Widgets.getStringSize(text.substring(0, Vex.platform.getTextCursorPosition()));
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
    this.fontName = name;
    this.fontStyle = style;
    this.fontPointSize = pointSize;
    this.fontStrikeThrough = strikeThrough;
    if (fontStrikeThrough) {
      System.out.println("");
    }
    return this;
  }
}
