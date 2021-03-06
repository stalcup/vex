package vex.widgets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vex.Align;
import vex.Color;
import vex.Vex;
import vex.Widgets;
import vex.events.KeyEvent;
import vex.events.MouseEvent.Type;
import vex.geom.Point;
import vex.geom.Rect;
import vex.styles.Style;
import vex.styles.TextBoxStyle;

public class TextBoxWidget extends Widget {

  public static int textCursorPosition;
  public static int textCursorLine;
  public static String updatedText;

  private String text;

  public TextBoxWidget(Rect bounds) {
    super(bounds);
  }

  @Override
  protected Color computeBorderColor(Style<?> style) {
    TextBoxStyle<?> textboxStyle = (TextBoxStyle<?>) style;
    if (disabled && textboxStyle.disabledBorderColor != null) {
      return textboxStyle.disabledBorderColor;
    }

    return super.computeBorderColor(style);
  }

  @Override
  protected Color computeTextColor(Style<?> style) {
    TextBoxStyle<?> textboxStyle = (TextBoxStyle<?>) style;
    if (disabled && textboxStyle.disabledTextColor != null) {
      return textboxStyle.disabledTextColor;
    }

    return super.computeTextColor(style);
  }

  public WidgetStatus render(TextBoxStyle<?> style) {
    maybeFocusMeNext();
    focusMeOnClick();
    boolean focused = isFocused();

    super.render(style);

    renderImage(style);

    if (style.underlineColor != null) {
      Vex.setColor(style.underlineColor);
      Vex.fillRect(bounds.x, bounds.y + bounds.height - 1, bounds.width, 1);
    }

    int pointSizeMargin = style.fontPointSize * 9 / 10;
    int effectiveMargin = style.margin == Style.NOT_SET ? pointSizeMargin : style.margin;
    int offsetTop = pointSizeMargin / 2;
    Point stringSize = null;
    boolean addedALine = false;

    renderPlaceholderText(style, effectiveMargin);

    if (text == null) {
      return WidgetStatus.text(false, null, null, false);
    }

    boolean updatedText = false;
    String keyText = null;
    String displayText = null;

    Vex.setColor(style.textColor);

    List<String> lines = splitOnLinebreaks(text);

    if (focused) {
      TextBoxWidget.textCursorLine = Math.min(TextBoxWidget.textCursorLine, lines.size() - 1);
      TextBoxWidget.textCursorLine = Math.max(TextBoxWidget.textCursorLine, 0);

      TextBoxWidget.textCursorPosition =
          Math.min(
              TextBoxWidget.textCursorPosition, lines.get(TextBoxWidget.textCursorLine).length());
      TextBoxWidget.textCursorPosition = Math.max(TextBoxWidget.textCursorPosition, 0);
    }

    KeyEvent keyEvent = disabled ? null : Vex.getKeyEvent();

    if (focused && keyEvent != null) {
      keyText = keyEvent.keyText;

      for (int i = 0; i < lines.size(); i++) {
        String line = lines.get(i);

        if (TextBoxWidget.textCursorLine == i) {

          if (keyText.equals("Left")) {
            if (TextBoxWidget.textCursorPosition > 0) {
              TextBoxWidget.textCursorPosition--;
            } else if (TextBoxWidget.textCursorLine > 0) {
              TextBoxWidget.textCursorLine--;
              TextBoxWidget.textCursorPosition = lines.get(TextBoxWidget.textCursorLine).length();
            }
            break;
          } else if (keyText.equals("Right")) {
            if (TextBoxWidget.textCursorPosition < line.length()) {
              TextBoxWidget.textCursorPosition = TextBoxWidget.textCursorPosition + 1;
            } else if (TextBoxWidget.textCursorLine < lines.size() - 1) {
              TextBoxWidget.textCursorPosition = 0;
              TextBoxWidget.textCursorLine++;
            }
            break;
          } else if (keyText.equals("Up")) {
            if (TextBoxWidget.textCursorLine > 0) {
              TextBoxWidget.textCursorLine = TextBoxWidget.textCursorLine - 1;
            } else {
              TextBoxWidget.textCursorPosition = 0;
            }
            break;
          } else if (keyText.equals("Down")) {
            if (TextBoxWidget.textCursorLine < lines.size() - 1) {
              TextBoxWidget.textCursorLine++;
            } else {
              TextBoxWidget.textCursorPosition = line.length();
            }
            break;
          }

          TextBoxWidget.textCursorPosition =
              Math.min(TextBoxWidget.textCursorPosition, line.length());
          TextBoxWidget.textCursorPosition = Math.max(TextBoxWidget.textCursorPosition, 0);

          String textLeftOfCursor = line.substring(0, TextBoxWidget.textCursorPosition);

          boolean delete = keyText.equals("Delete");
          boolean backspace = keyText.equals("Backspace");
          boolean enter = "Enter".equals(keyText);
          boolean tab = keyText.equals("Tab");
          boolean acceptCharacter =
              keyEvent.printable && (multiline || !enter) && !tab || delete || backspace;

          if (tab) {
            Widgets.setFocusNext(tab);
          }

          if (acceptCharacter) {
            addedALine = enter;

            String left = TextBoxWidget.textCursorPosition > 0 ? textLeftOfCursor : "";
            String right =
                TextBoxWidget.textCursorPosition < line.length()
                    ? line.substring(TextBoxWidget.textCursorPosition)
                    : "";

            // Process these as mutually exclusive states, even though on some OS's the KeyEvent
            // does
            // not make that clear.
            if (delete && TextBoxWidget.textCursorPosition < line.length()) {
              line = left + right.substring(1);
              updatedText = true;
            } else if (backspace && TextBoxWidget.textCursorPosition > 0) {
              line = left.substring(0, left.length() - 1) + right;
              TextBoxWidget.textCursorPosition = TextBoxWidget.textCursorPosition - 1;
              updatedText = true;
            } else if (keyEvent.printable) {
              line = left + keyEvent.key + right;
              if (!enter) {
                TextBoxWidget.textCursorPosition = TextBoxWidget.textCursorPosition + 1;
              }
              updatedText = true;
            } else if (delete
                && TextBoxWidget.textCursorPosition == line.length()
                && i < lines.size() - 1) {
              line = left + lines.remove(i + 1);
              updatedText = true;
            } else if (backspace && TextBoxWidget.textCursorPosition == 0 && i > 0) {
              String previousLine = lines.remove(i - 1);
              line = previousLine + right;
              TextBoxWidget.textCursorLine--;
              TextBoxWidget.textCursorPosition = previousLine.length();
              updatedText = true;
              i--;
            }

            lines.set(i, line);
          }
        }
      }
    }

    text = String.join("\n", lines);

    if (addedALine) {
      TextBoxWidget.textCursorLine++;
      TextBoxWidget.textCursorPosition = 0;

      lines = splitOnLinebreaks(text);
    }

    if (focused) {
      TextBoxWidget.textCursorLine = Math.min(TextBoxWidget.textCursorLine, lines.size() - 1);
      TextBoxWidget.textCursorLine = Math.max(TextBoxWidget.textCursorLine, 0);

      TextBoxWidget.textCursorPosition =
          Math.min(
              TextBoxWidget.textCursorPosition, lines.get(TextBoxWidget.textCursorLine).length());
      TextBoxWidget.textCursorPosition = Math.max(TextBoxWidget.textCursorPosition, 0);
    }

    // -------------------------------- draw

    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);

      stringSize = Vex.getSize(line);
      if (i > 0) {
        offsetTop += stringSize.y * 1.1;
      }

      if (focused && TextBoxWidget.textCursorLine == i) {
        String textLeftOfCursor = line.substring(0, TextBoxWidget.textCursorPosition);
        stringSize = Vex.getSize(textLeftOfCursor);

        int textCursorX = stringSize.x;
        if (multiline) {
          int textCursorPixelX = bounds.x + effectiveMargin + textCursorX;
          if (textCursorPixelX < bounds.x + bounds.width) {
            Vex.fillRect(textCursorPixelX, bounds.y + offsetTop, 2, stringSize.y);
          }
        } else {
          int textCursorPixelX = bounds.x + effectiveMargin + textCursorX;
          if (textCursorPixelX < bounds.x + bounds.width) {
            Vex.fillRect(
                textCursorPixelX, bounds.y + (bounds.height - stringSize.y) / 2, 2, stringSize.y);
          }
        }
      }

      displayText = style.password ? makePasswordString(line.length()) : line;

      if (multiline) {
        Vex.drawString(
            displayText,
            bounds.x + effectiveMargin,
            bounds.y + offsetTop + stringSize.y,
            bounds.x,
            bounds.y,
            bounds.width,
            bounds.height);
      } else {
        Vex.drawAlignedString(
            bounds.x + effectiveMargin,
            bounds.y,
            bounds.width - effectiveMargin,
            bounds.height,
            displayText,
            Align.MIN);
      }
    }

    if (updatedText) {
      TextBoxWidget.updatedText = text;
    }

    return WidgetStatus.text(updatedText, text, keyText, Widgets.lostFocus(focusId));
  }

  private void renderPlaceholderText(TextBoxStyle<?> style, int effectiveMargin) {
    if ((text == null || text.isEmpty())
        && style.placeholderText != null
        && style.placeholderTextColor != null) {
      Vex.setColor(style.placeholderTextColor);
      Vex.drawAlignedString(
          bounds.x + effectiveMargin,
          bounds.y,
          bounds.width - effectiveMargin,
          bounds.height,
          style.placeholderText,
          Align.MIN);
    }
  }

  private void focusMeOnClick() {
    if (focusId != null
        && Vex.mouseEventIsIn(bounds.x, bounds.y, bounds.width, bounds.height, Type.DOWN)) {
      Widgets.setCurrentFocusId(focusId);
      if (text != null) {
        TextBoxWidget.textCursorPosition = text.length();
      }
    }
  }

  private void maybeFocusMeNext() {
    if (receivesTabFocus) {
      Widgets.maybeFocusMeNext(focusId);
    }
  }

  private List<String> splitOnLinebreaks(String text) {
    if (multiline) {
      return new ArrayList<>(Arrays.asList(text.split("\n", -1)));
    } else {
      List<String> list = new ArrayList<>();
      list.add(text);
      return list;
    }
  }

  public String makePasswordString(int length) {
    return new String(new char[length]).replace("\0", "*");
  }

  public TextBoxWidget text(String text) {
    this.text = text;
    return this;
  }

  protected boolean multiline;

  public TextBoxWidget multiline(boolean multiline) {
    this.multiline = multiline;
    return this;
  }

  private void renderImage(TextBoxStyle<?> style) {
    if (style.image != null) {
      Vex.drawAlignedImage(
          bounds,
          style.image,
          style.imageHorizontalAlignment,
          style.imageShiftX,
          style.imageShiftY);
    }
  }

  @Override
  public TextBoxWidget disabled(boolean disabled) {
    return (TextBoxWidget) super.disabled(disabled);
  }

  @Override
  public TextBoxWidget receivesTabFocus(boolean receivesTabFocus) {
    return (TextBoxWidget) super.receivesTabFocus(receivesTabFocus);
  }

  @Override
  public TextBoxWidget focusId(String focusId) {
    return (TextBoxWidget) super.focusId(focusId);
  }
}
