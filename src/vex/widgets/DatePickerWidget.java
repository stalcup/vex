package vex.widgets;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.common.base.Preconditions;

import vex.Align;
import vex.FontStyle;
import vex.Vex;
import vex.Widgets;
import vex.events.MouseEvent.Type;
import vex.geom.Rect;
import vex.styles.ButtonStyle;
import vex.styles.DatePickerAreaStyle;
import vex.styles.Style;

public class DatePickerWidget extends Widget {

  public DatePickerWidget(Rect bounds) {
    super(bounds);
  }

  private long timeMs;
  private Calendar calendar = GregorianCalendar.getInstance();
  int currentDayOfMonth;
  int currentDayOfWeek;
  int dayOfWeekFirstDayOfMonth;
  long daysInMonth;
  int monthOfYear;

  public DatePickerWidget timeMs(long timeMs) {
    this.timeMs = timeMs;

    calendar.setTimeInMillis(timeMs);
    currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH) - 1;
    currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    dayOfWeekFirstDayOfMonth = (currentDayOfWeek - currentDayOfMonth + 49) % 7;
    daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    monthOfYear = calendar.get(Calendar.MONTH);

    return this;
  }

  private boolean updated;
  private Rect headerBounds;
  private Rect leftArrowBounds;
  private Rect rightArrowBounds;
  private Rect innerBackgroundBounds;
  private Rect datesAreaBounds;
  private ButtonStyle<?> datePickerDayStyle;

  public WidgetStatus render(
      DatePickerAreaStyle<?> datePickerAreaStyle, ButtonStyle<?> datePickerDayStyle) {

    this.datePickerDayStyle = datePickerDayStyle;

    bounds.width =
        datePickerAreaStyle.outerMargin * 2
            + datePickerAreaStyle.innerMargin * 2
            + datePickerAreaStyle.dayWidth * 7;
    bounds.height =
        datePickerAreaStyle.outerMargin * 2
            + datePickerAreaStyle.innerMargin * 2
            + datePickerAreaStyle.dayHeight * 7
            + datePickerAreaStyle.headerHeight;

    headerBounds =
        bounds
            .dupe("headerArea")
            .onTop(datePickerAreaStyle.headerHeight + datePickerAreaStyle.outerMargin);

    leftArrowBounds =
        headerBounds
            .dupe("leftArrowButton")
            .offLeft(datePickerAreaStyle.outerMargin)
            .offTop(datePickerAreaStyle.outerMargin / 2)
            .onLeft(datePickerAreaStyle.headerHeight + datePickerAreaStyle.outerMargin / 2);

    rightArrowBounds =
        headerBounds
            .dupe("rightArrowButton")
            .offRight(datePickerAreaStyle.outerMargin)
            .offTop(datePickerAreaStyle.outerMargin / 2)
            .onRight(datePickerAreaStyle.headerHeight + datePickerAreaStyle.outerMargin / 2);

    innerBackgroundBounds =
        bounds
            .dupe("innerBackgroundArea")
            .shrink(datePickerAreaStyle.outerMargin)
            .offTop(datePickerAreaStyle.headerHeight);
    datesAreaBounds =
        innerBackgroundBounds.dupe("datesArea").shrink(datePickerAreaStyle.innerMargin);

    super.render(datePickerAreaStyle);

    return WidgetStatus.time(updated, timeMs, false);
  }

  @Override
  protected void renderBackground(Style<?> style) {
    super.renderBackground(style);

    DatePickerAreaStyle<?> datePickerAreaStyle = (DatePickerAreaStyle<?>) style;
    Vex.setColor(datePickerAreaStyle.innerBackgroundColor);
    Vex.fillRect(
        innerBackgroundBounds.x,
        innerBackgroundBounds.y,
        innerBackgroundBounds.width,
        innerBackgroundBounds.height);
  }

  private static DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();

  @Override
  protected void renderText(Style<?> style) {
    DatePickerAreaStyle<?> datePickerAreaStyle = (DatePickerAreaStyle<?>) style;

    Vex.drawAlignedImage(
        leftArrowBounds.x,
        leftArrowBounds.y,
        leftArrowBounds.width,
        leftArrowBounds.height,
        datePickerAreaStyle.leftImage,
        Align.MID,
        0,
        0);
    if (Vex.mouseEventIsIn(
        leftArrowBounds.x,
        leftArrowBounds.y,
        leftArrowBounds.width,
        leftArrowBounds.height,
        Type.DOWN)) {
      updated = true;
      calendar.add(Calendar.MONTH, -1);
      timeMs = calendar.getTimeInMillis();
    }

    Vex.drawAlignedImage(
        rightArrowBounds.x,
        rightArrowBounds.y,
        rightArrowBounds.width,
        rightArrowBounds.height,
        datePickerAreaStyle.rightImage,
        Align.MID,
        0,
        0);
    if (Vex.mouseEventIsIn(
        rightArrowBounds.x,
        rightArrowBounds.y,
        rightArrowBounds.width,
        rightArrowBounds.height,
        Type.DOWN)) {
      updated = true;
      calendar.add(Calendar.MONTH, 1);
      timeMs = calendar.getTimeInMillis();
    }

    Preconditions.checkState(style.textColor != null);
    Vex.setColor(computeTextColor(style));

    style.text = dateFormatSymbols.getMonths()[monthOfYear] + " " + calendar.get(Calendar.YEAR);

    Vex.drawAlignedString(
        headerBounds.x + style.paddingLeft,
        headerBounds.y,
        headerBounds.width - style.paddingLeft,
        headerBounds.height,
        style.text,
        style.textAlignX);

    ButtonStyle<?> dayNameStyle = datePickerDayStyle.dupe();
    dayNameStyle.fontStyle(FontStyle.BOLD);
    dayNameStyle.hoverBackgroundColor(null);

    // Day name headers.
    for (int i = 0; i < 7; i++) {
      String day = dateFormatSymbols.getShortWeekdays()[i + 1];
      Rect dayBounds =
          new Rect(
              "dayOfWeek",
              datesAreaBounds.x + i * datePickerAreaStyle.dayWidth,
              datesAreaBounds.y,
              datePickerAreaStyle.dayWidth,
              datePickerAreaStyle.dayHeight,
              datesAreaBounds);

      dayNameStyle.text(day);
      Widgets.button(dayBounds).render(dayNameStyle);
    }

    for (int dayOfMonth = 0; dayOfMonth < daysInMonth; dayOfMonth++) {
      int dayOfWeek = (dayOfWeekFirstDayOfMonth + dayOfMonth) % 7;
      int weekOfMonth = (dayOfWeekFirstDayOfMonth + dayOfMonth) / 7;

      Rect dayBounds =
          new Rect(
                  "dateButton",
                  datesAreaBounds.x + dayOfWeek * datePickerAreaStyle.dayWidth,
                  datesAreaBounds.y + (weekOfMonth + 1) * datePickerAreaStyle.dayHeight,
                  datePickerAreaStyle.dayWidth,
                  datePickerAreaStyle.dayHeight,
                  datesAreaBounds)
              .shrink(1);

      datePickerDayStyle.text(dayOfMonth + 1 + "");
      if (Widgets.button(dayBounds)
          .selected(dayOfMonth == currentDayOfMonth)
          .render(datePickerDayStyle)
          .clicked) {
        updated = true;
        long daysDifferent = dayOfMonth - currentDayOfMonth;
        timeMs += daysDifferent * 24L * 60L * 60L * 1000L;
      }
    }
  }
}
