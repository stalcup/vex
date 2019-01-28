package vex.geom;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

public class Rect {

  public String name;
  public String chainName;

  public int height;
  public int width;
  public int x;
  public int y;

  public Rect(String name, int x, int y, int width, int height) {
    Preconditions.checkNotNull(name);

    this.name = name;
    chainName = name;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;

    System.out.println(chainName);
  }

  public Rect(String name, String chainName, int x, int y, int width, int height) {
    Preconditions.checkNotNull(name);

    this.name = name;
    this.chainName = chainName;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;

    System.out.println(chainName);
  }

  public Rect(String name, int x, int y, int width, int height, Rect parent) {
    Preconditions.checkNotNull(name);

    this.name = name;
    chainName = parent.chainName + "-" + name;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;

    System.out.println(chainName);
  }

  public List<Rect> asColumns(int columnCount, String name) {
    List<Rect> columns = new ArrayList<>();
    for (int i = 0; i < columnCount; i++) {
      int left = i * width / columnCount;
      int right = (i + 1) * width / columnCount;
      columns.add(new Rect(name + i, x + left, y, right - left, height));
    }
    return columns;
  }

  public List<Rect> asRows(int rowCount, String name) {
    List<Rect> rows = new ArrayList<>();
    for (int i = 0; i < rowCount; i++) {
      int top = i * height / rowCount;
      int bottom = (i + 1) * height / rowCount;
      rows.add(new Rect(name + i, x, y + top, width, bottom - top, this));
    }
    return rows;
  }

  public Rect at(int x, int y) {
    this.x = x;
    this.y = y;
    return this;
  }

  public Rect dupe(String name) {
    return new Rect(name, x, y, width, height, this);
  }

  public Rect growBottom(int deltaY) {
    height += deltaY;
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

  public Rect offBottom(int pixels) {
    height -= pixels;
    return this;
  }

  public Rect offLeft(int pixels) {
    x += pixels;
    width -= pixels;
    return this;
  }

  public Rect offRight(int pixels) {
    width -= pixels;
    return this;
  }

  public Rect offTop(int pixels) {
    y += pixels;
    height -= pixels;
    return this;
  }

  public Rect onBottom(int pixels) {
    y = y + height - pixels;
    height = pixels;
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

  public Rect onTop(int pixels) {
    height = pixels;
    return this;
  }

  public Rect panDown(int deltaY) {
    y += deltaY;
    return this;
  }

  public Rect panLeft(int deltaX) {
    x -= deltaX;
    return this;
  }

  public Rect panRight(int deltaX) {
    x += deltaX;
    return this;
  }

  public Rect panUp(int deltaY) {
    y -= deltaY;
    return this;
  }

  public Rect scaleHeight(double scale) {
    height *= scale;
    return this;
  }

  public Rect shrink(int pixels) {
    x += pixels;
    y += pixels;
    width -= pixels * 2;
    height -= pixels * 2;
    return this;
  }

  public Rect expand(int pixels) {
    x -= pixels;
    y -= pixels;
    width += pixels * 2;
    height += pixels * 2;
    return this;
  }

  public Rect to(int width, int height) {
    this.width = width;
    this.height = height;
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

  public Rect toHeight(int height) {
    this.height = height;
    return this;
  }

  @Override
  public String toString() {
    return "Rect [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
  }

  public Rect toWidth(int width) {
    this.width = width;
    return this;
  }

  public static Rect combine(String name, Rect rect1, Rect rect2) {
    int x = Math.min(rect1.x, rect2.x);
    int y = Math.min(rect1.y, rect2.y);

    int x2 = Math.max(rect1.x + rect1.width, rect2.x + rect2.width);
    int y2 = Math.max(rect1.y + rect1.height, rect2.y + rect2.height);

    Rect rect =
        new Rect(
            rect1.name + "+" + rect2.name,
            rect1.chainName + "+" + rect2.name + "-" + name,
            x,
            y,
            x2 - x,
            y2 - y);

    return rect;
  }
}
