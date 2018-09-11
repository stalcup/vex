package vex.web;

import elemental2.dom.CanvasRenderingContext2D;
import jsinterop.base.Js;
import vex.Color;
import vex.Graphics;
import vex.geom.Point;

public class WebGraphics implements Graphics {

  private CanvasRenderingContext2D context2d;
  private int fontPixelSize;
  private boolean fontStrikeThrough;
  private Color color;

  public WebGraphics(CanvasRenderingContext2D context2d) {
    this.context2d = context2d;
  }

  @Override
  public void drawRect(int x, int y, int width, int height) {
    context2d.strokeRect(x, y, width, height);
  }

  @Override
  public void drawString(String string, int x, int y) {
    context2d.fillText(string, x, y);
    if (fontStrikeThrough) {
      Point size = getSize(string);
      context2d.fillRect(
          x, y - (int) Math.round(size.y * .25), size.x, (int) Math.ceil(fontPixelSize / 9));
    }
  }

  @Override
  public void drawString(
      String string, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight) {
    context2d.fillText(string, x, y, clipX + clipWidth - x);
    if (fontStrikeThrough) {
      Point size = getSize(string);
      context2d.fillRect(
          x, y - (int) Math.round(size.y * .25), size.x, (int) Math.ceil(fontPixelSize / 9));
    }
  }

  @Override
  public void fillRect(int x, int y, int width, int height) {
    context2d.fillRect(x, y, width, height);
  }

  @Override
  public Point getSize(String string) {
    return new Point((int) context2d.measureText(string).width, Math.round(fontPixelSize / 0.8f));
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
    applyColor();
  }

  private void applyColor() {
    int red = this.color.r;
    int green = this.color.g;
    int blue = this.color.b;
    int alpha = this.color.a;
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
  public void setFont(String fontName, FontStyle style, int pixelSize, boolean strikeThrough) {
    this.fontPixelSize = pixelSize;
    this.fontStrikeThrough = strikeThrough;
    context2d.font = style.name + " " + pixelSize + "px " + fontName;
  }

  @Override
  public void setStroke(int width) {
    context2d.strokeStyle =
        Js.<CanvasRenderingContext2D.StrokeStyleUnionType>uncheckedCast(
            context2d.fillStyle + " " + width + "px");
  }

  @Override
  public void drawDropShadow(
      int x, int y, int width, int height, int offsetX, int offsetY, int blur) {
    context2d.shadowColor =
        "rgb(" + color.r + ", " + color.g + ", " + color.b + ", " + (color.a / 255f) + ")";
    context2d.shadowBlur = blur;
    context2d.shadowOffsetX = offsetX;
    context2d.shadowOffsetY = offsetY;

    context2d.fillStyle =
        Js.<CanvasRenderingContext2D.FillStyleUnionType>uncheckedCast("rgb(0, 0, 0, 1.0)");
    context2d.fillRect(x, y, width, height);

    applyColor();

    context2d.shadowColor = null;
    context2d.shadowBlur = 0;
    context2d.shadowOffsetX = 0;
    context2d.shadowOffsetY = 0;
  }
}
