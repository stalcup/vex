package vex.widgets;

import java.util.List;
import java.util.Set;

import vex.Rect;
import vex.Sets;
import vex.Vex;
import vex.Widgets;
import vex.styles.ButtonStyle;

public class SelectWidget {

  private Rect closedBounds;
  private List<String> options;
  private Set<String> selections;

  public SelectWidget(Rect closedBounds, List<String> options, Set<String> selections) {
    this.closedBounds = closedBounds;
    this.options = options;
    this.selections = selections;
  }

  public WidgetStatus render(
      ButtonStyle<?> triggerStyle, ButtonStyle<?> optionStyle, boolean open) {
    String selectionsPreview =
        selections == null || selections.isEmpty() ? null : String.join(", ", selections);

    WidgetStatus status =
        Widgets.startRawDropDown(
            open, focusId, disabled, selectionsPreview, closedBounds, triggerStyle, options.size());

    if (status.open) {
      int displayCount = Math.min(options.size(), status.rowRects.size());
      for (int rowIndex = 0; rowIndex < displayCount; rowIndex++) {
        String option = options.get(rowIndex);
        if (Widgets.button(status.rowRects.get(rowIndex))
            .disabled(disabled)
            .selected(selections != null && selections.contains(option))
            .render(optionStyle.text(option))
            .clicked) {
          status.selected = option;
          Sets.toggle(selections, option);
        }
      }
      Vex.platform.endLayer();
    }
    return status;
  }

  private boolean disabled;

  public SelectWidget disabled(boolean disabled) {
    this.disabled = disabled;
    return this;
  }

  private String focusId;

  public SelectWidget focusId(String focusId) {
    this.focusId = focusId;
    return this;
  }
}
