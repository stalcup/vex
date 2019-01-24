package vex;

import java.util.ArrayList;
import java.util.List;

public class Rect {

  public int x;
  public int y;
  public int width;
  public int height;

  public Rect(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public List<Rect> asRows(int rowCount) {
    List<Rect> rows = new ArrayList<>();
    for (int i = 0; i < rowCount; i++) {
      int top = i * height / rowCount;
      int bottom = (i + 1) * height / rowCount;
      rows.add(new Rect(x, y + top, width, bottom - top));
    }
    return rows;
  }

  public List<Rect> asRows(int rowCount, int gap) {
    Rect area = dupe();
    area.height += gap;

    List<Rect> rows = area.asRows(rowCount);

    for (Rect row : rows) {
      row.height -= gap;
    }

    return rows;
  }

  public List<Rect> asColumns(int columnCount) {
    List<Rect> columns = new ArrayList<>();
    for (int i = 0; i < columnCount; i++) {
      int left = i * width / columnCount;
      int right = (i + 1) * width / columnCount;
      columns.add(new Rect(x + left, y, right - left, height));
    }
    return columns;
  }

  public Rect dupe() {
    return new Rect(x, y, width, height);
  }

  public Rect shrink(int pixels) {
    x += pixels;
    y += pixels;
    width -= pixels * 2;
    height -= pixels * 2;
    return this;
  }

  public Rect onTop(int pixels) {
    height = pixels;
    return this;
  }

  public Rect offTop(int pixels) {
    y += pixels;
    height -= pixels;
    return this;
  }

  public Rect onLeft(int pixels) {
    width = pixels;
    return this;
  }

  public Rect onRight(int pixels) {
    x = x + width - pixels;
    width = pixels;
    return this;
  }

  public Rect onBottom(int pixels) {
    y = y + height - pixels;
    height = pixels;
    return this;
  }

  public Rect offRight(int pixels) {
    width -= pixels;
    return this;
  }

  public Rect offLeft(int pixels) {
    x += pixels;
    width -= pixels;
    return this;
  }

  public Rect scaleHeight(double scale) {
    height *= scale;
    return this;
  }

  public Rect at(int x, int y) {
    this.x = x;
    this.y = y;
    return this;
  }

  public Rect to(int width, int height) {
    this.width = width;
    this.height = height;
    return this;
  }

  public Rect onHorizontalCenter(int width) {
    x = x + (this.width - width) / 2;
    this.width = width;
    return this;
  }

  public Rect toHeight(int height) {
    this.height = height;
    return this;
  }

  public Rect toWidth(int width) {
    this.width = width;
    return this;
  }

  public Rect combine(Rect that) {
    int x = Math.min(this.x, that.x);
    int y = Math.min(this.y, that.y);

    int x2 = Math.max(this.x + width, that.x + that.width);
    int y2 = Math.max(this.y + height, that.y + that.height);

    this.x = x;
    this.y = y;
    width = x2 - x;
    height = y2 - y;

    return this;
  }

  public Rect toCenterHeight(int height) {
    int diff = (this.height - height) / 2;
    y += diff;
    this.height -= diff * 2;
    return this;
  }

  public Rect toCenterWidth(int width) {
    int diff = (this.width - width) / 2;
    x += diff;
    this.width -= diff * 2;
    return this;
  }

  public Rect panUp(int deltaY) {
    y -= deltaY;
    return this;
  }

  public Rect panDown(int deltaY) {
    y += deltaY;
    return this;
  }

  public Rect panRight(int deltaX) {
    x += deltaX;
    return this;
  }

  public Rect panLeft(int deltaX) {
    x -= deltaX;
    return this;
  }

  public Rect growLeft(int deltaX) {
    x -= deltaX;
    width += deltaX;
    return this;
  }

  public Rect growRight(int deltaX) {
    width += deltaX;
    return this;
  }

  public Rect growTop(int deltaY) {
    y -= deltaY;
    height += deltaY;
    return this;
  }

  public Rect growBottom(int deltaY) {
    height += deltaY;
    return this;
  }
}
