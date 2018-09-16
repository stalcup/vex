package vex.widgets;

import vex.Graphics;
import vex.Platform;
import vex.Vex;
import vex.events.MouseEvent.Type;

public class ButtonWidget extends Widget {
  public ButtonWidget(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public WidgetStatus render(ButtonStyle<?> style) {
    Graphics g = Vex.platform.getGraphics();

    renderBackground(g, style);
    renderHoverColor(g, style);
    if (style.selectedColor != null) {
      g.setColor(style.selectedColor);
      fillRectOrRoundRect(g, style);
    }
    renderFont(g, style);
    renderText(g, style);
    renderBorder(g, style);

    return WidgetStatus.click(Platform.mouseEventIsIn(x, y, width, height, Type.DOWN));
  }
}
