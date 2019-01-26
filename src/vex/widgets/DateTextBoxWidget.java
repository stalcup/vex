package vex.widgets;

import java.util.Calendar;
import java.util.GregorianCalendar;

import vex.Platform;
import vex.Strings;
import vex.Vex;
import vex.Widgets;
import vex.events.MouseEvent.Type;
import vex.geom.Rect;
import vex.styles.ButtonStyle;
import vex.styles.DatePickerAreaStyle;
import vex.styles.TextBoxStyle;

public class DateTextBoxWidget {

  private static boolean datePickerOpen;
  private static String unvalidatedDateString;

  private TextBoxWidget textBoxWidget;
  private DatePickerWidget datePickerWidget;

  private long timeMs;
  private Calendar calendar = GregorianCalendar.getInstance();
  private boolean updated;

  public DateTextBoxWidget(String focusId, Rect bounds) {
    textBoxWidget = new TextBoxWidget(bounds).focusId(focusId);
  }

  public WidgetStatus render(
      TextBoxStyle<?> style,
      DatePickerAreaStyle<?> datePickerAreaStyle,
      ButtonStyle<?> datePickerDayStyle) {
    updated = false;

    boolean focused =
        textBoxWidget.focusId != null
            && Strings.equals(Widgets.getCurrentFocusId(), textBoxWidget.focusId);
    String validatedDateString = toDateString(timeMs);
    if (!focused) {
      unvalidatedDateString = validatedDateString;
    }

    WidgetStatus textBoxWidgetStatus = textBoxWidget.text(unvalidatedDateString).render(style);
    if (textBoxWidgetStatus.updated) {
      unvalidatedDateString = TextBoxWidget.updatedText;
      updateIfValidString(unvalidatedDateString);
    }

    if (focused) {
      datePickerOpen = true;
    } else if (Widgets.getCurrentFocusId() != null) {
      datePickerOpen = false;
    }

    if (datePickerOpen) {
      Vex.platform.beginLayer();

      Rect datePickerWidgetBounds =
          textBoxWidget.bounds.dupe().panDown(textBoxWidget.bounds.height + 10).to(0, 0);
      datePickerWidget = new DatePickerWidget(datePickerWidgetBounds).timeMs(timeMs);
      WidgetStatus datePickerWidgetStatus =
          datePickerWidget.render(datePickerAreaStyle, datePickerDayStyle);

      if (Platform.mouseEventIs(Type.DOWN)) {
        if (!Platform.mouseEventIsIn(datePickerWidgetBounds, Type.DOWN)) {
          datePickerOpen = false;
          Widgets.setCurrentFocusId(null);
          datePickerWidgetStatus.lostFocus = true;
        }
      }

      Vex.platform.endLayer();

      if (updated) {
        datePickerWidgetStatus.updated = updated;
      }

      return datePickerWidgetStatus;
    }

    return WidgetStatus.time(updated, timeMs, textBoxWidgetStatus.lostFocus);
  }

  private boolean updateIfValidString(String unvalidatedDateString) {
    if (unvalidatedDateString == null) {
      return false;
    }

    String[] split = unvalidatedDateString.split("/", -1);
    if (split.length != 3) {
      return false;
    }

    try {
      int month = Integer.parseInt(split[0]);
      int date = Integer.parseInt(split[1]);
      int year = Integer.parseInt(split[2]);

      if (month < 1 || month > 12) {
        return false;
      }
      if (date < 1 || date > 31) {
        return false;
      }
      if (year < 1970 || year > 2038) {
        return false;
      }

      calendar.set(year, month - 1, date);
      long newTimeMs = calendar.getTimeInMillis();
      updated = timeMs != newTimeMs;
      if (updated) {
        System.currentTimeMillis();
      }
      timeMs = newTimeMs;
      DateTextBoxWidget.unvalidatedDateString = toDateString(timeMs);
    } catch (NumberFormatException e) {
      return false;
    }

    return false;
  }

  private String toDateString(long timeMs) {
    int currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    int monthOfYear = calendar.get(Calendar.MONTH) + 1;
    int year = calendar.get(Calendar.YEAR);

    return monthOfYear + "/" + currentDayOfMonth + "/" + year;
  }

  public DateTextBoxWidget timeMs(long timeMs) {
    this.timeMs = timeMs;
    calendar.setTimeInMillis(timeMs);
    return this;
  }

  public DateTextBoxWidget disabled(boolean disabled) {
    textBoxWidget.disabled(disabled);
    return this;
  }
}
