package vex.widgets;

import vex.Color;
import vex.Graphics;
import vex.Platform;
import vex.Vex;
import vex.Widgets;
import vex.events.MouseEvent.Type;

public class ButtonWidget {
  private int x;
  private int y;
  private int width;
  private int height;
  private String title;
  private Color textColor;
  private Color backgroundColor;
  private Color mouseOverColor;
  private Color selectedColor;
  private String focusId;

  public ButtonWidget(String focusId, int x, int y, int width, int height) {
    this.focusId = focusId;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public ButtonWidget text(String title) {
    this.title = title;
    return this;
  }

  public ButtonWidget textColor(Color textColor) {
    this.textColor = textColor;
    return this;
  }

  public ButtonWidget backgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }

  public ButtonWidget hoverColor(Color mouseOverColor) {
    this.mouseOverColor = mouseOverColor;
    return this;
  }

  public ButtonWidget setSelectedColor(Color selectedColor) {
    this.selectedColor = selectedColor;
    return this;
  }

  public WidgetStatus render() {
    Graphics g = Vex.platform.getGraphics();
    if (backgroundColor != null) {
      g.setColor(backgroundColor);
      g.fillRect(x, y, width, height);
    }

    if (mouseOverColor != null && Platform.mouseLocationIsIn(x, y, width, height)) {
      g.setColor(mouseOverColor);
      g.fillRect(x, y, width, height);
    }

    if (selectedColor != null) {
      g.setColor(selectedColor);
      g.fillRect(x, y, width, height);
    }

    if (title != null) {
      g.setColor(textColor);
      Widgets.renderStringCenteredBoth(x, y, width, height, title);
    }

    return WidgetStatus.click(Platform.mouseEventIsIn(x, y, width, height, Type.DOWN));
  }
}
