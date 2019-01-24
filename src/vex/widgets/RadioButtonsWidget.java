package vex.widgets;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import vex.Rect;
import vex.Widgets;
import vex.styles.RadioButtonStyle;

public class RadioButtonsWidget extends Widget {

  protected boolean disabled;

  @Override
  public RadioButtonsWidget disabled(boolean disabled) {
    this.disabled = disabled;
    return this;
  }

  private Map<String, String> selectableKeysByDisplayValue;
  private String selectedKey;

  public RadioButtonsWidget(
      Rect bounds, Map<String, String> selectableKeysByDisplayValue, String selectedKey) {
    super(bounds);
    this.selectableKeysByDisplayValue = selectableKeysByDisplayValue;
    this.selectedKey = selectedKey;
  }

  public WidgetStatus render(RadioButtonStyle<?> style) {
    String newSelectedKey = selectedKey;

    if (selectableKeysByDisplayValue.isEmpty()) {
      return WidgetStatus.selected(newSelectedKey);
    }

    List<Rect> optionsBounds = bounds.asRows(selectableKeysByDisplayValue.size());
    Iterator<Rect> optionsBoundsIterator = optionsBounds.iterator();
    for (Entry<String, String> entry : selectableKeysByDisplayValue.entrySet()) {
      Rect optionBounds = optionsBoundsIterator.next();

      if (Widgets.radioButton(null, optionBounds)
          .selected(entry.getKey().equals(selectedKey))
          .disabled(disabled)
          .render(style.text(entry.getValue()))
          .clicked) {
        newSelectedKey = entry.getKey();
      }
    }

    return WidgetStatus.selected(newSelectedKey);
  }
}
