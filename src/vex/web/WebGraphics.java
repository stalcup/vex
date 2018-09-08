package vex.web;

import elemental2.dom.CanvasRenderingContext2D;
import jsinterop.base.Js;
import vex.Color;
import vex.Graphics;
import vex.geom.Point;

public class WebGraphics implements Graphics {

  private CanvasRenderingContext2D context2d;
  private int fontPixelSize;

  public WebGraphics(CanvasRenderingContext2D context2d) {
    this.context2d = context2d;
  }

  @Override
  public void drawRect(int x, int y, int width, int height) {
    context2d.strokeRect(x, y, width, height);
  }

  @Override
  public void drawString(String string, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight) {
    context2d.fillText(string, x, y);
  }

  @Override
  public void fillRect(int x, int y, int width, int height) {
    context2d.fillRect(x, y, width, height);
  }

  @Override
  public Point getSize(String string) {
    return new Point((int) context2d.measureText(string).width, Math.round(fontPixelSize / 1.333f));
  }

  @Override
  public void setColor(Color color) {
    int red = color.r;
    int green = color.g;
    int blue = color.b;
    int alpha = color.a;
    if (alpha == 255) {
      context2d.fillStyle =
          Js.<CanvasRenderingContext2D.FillStyleUnionType>uncheckedCast(
              "rgb(" + red + ", " + green + ", " + blue + ")");
    } else {
      context2d.fillStyle =
          Js.<CanvasRenderingContext2D.FillStyleUnionType>uncheckedCast(
              "rgb(" + red + ", " + green + ", " + blue + ", " + (alpha / 255f) + ")");
    }
  }

  @Override
  public void setFont(String fontName, FontStyle style, int pixelSize) {
    this.fontPixelSize = pixelSize;
    context2d.font = style.name + " " + pixelSize + "px " + fontName;
  }

  @Override
  public void setStroke(int width) {
    context2d.strokeStyle =
        Js.<CanvasRenderingContext2D.StrokeStyleUnionType>uncheckedCast(
            context2d.fillStyle + " " + width + "px");
  }
}
