package vex.widgets;

import vex.Color;
import vex.Graphics;
import vex.Platform;
import vex.Vex;
import vex.events.MouseEvent.Type;

public class ButtonWidget extends AreaWidget {
  private Color selectedColor;
  private String focusId;

  public ButtonWidget(String focusId, int x, int y, int width, int height) {
    super(x, y, width, height);
    this.focusId = focusId;
  }

  public ButtonWidget setSelectedColor(Color selectedColor) {
    this.selectedColor = selectedColor;
    return this;
  }

  @Override
  public WidgetStatus render() {
    Graphics g = Vex.platform.getGraphics();

    renderBackground(g);
    renderHoverColor(g);
    if (selectedColor != null) {
      g.setColor(selectedColor);
      fillRectOrRoundRect(g);
    }
    renderFont(g);
    renderText(g);
    renderBorder(g);

    return WidgetStatus.click(Platform.mouseEventIsIn(x, y, width, height, Type.DOWN));
  }
}
