package vex.styles;

import vex.Vex;
import vex.geom.Rect;

public abstract class StyleShape {

  public abstract void draw(Rect bounds);

  public abstract void fill(Rect bounds);

  public static StyleShape OVAL =
      new StyleShape() {
        @Override
        public void draw(Rect bounds) {
          Vex.drawOval(bounds);
        }

        @Override
        public void fill(Rect bounds) {
          Vex.fillOval(bounds);
        }
      };

  public static StyleShape RECT =
      new StyleShape() {
        @Override
        public void draw(Rect bounds) {
          Vex.drawRect(bounds);
        }

        @Override
        public void fill(Rect bounds) {
          Vex.fillRect(bounds);
        }
      };

  public static StyleShape roundRect(double cornerRadiusRatio) {
    return new StyleShape() {
      @Override
      public void draw(Rect bounds) {
        int cornerRadius = (int) (Math.min(bounds.width, bounds.height) * cornerRadiusRatio);
        Vex.drawRoundRect(bounds, cornerRadius);
      }

      @Override
      public void fill(Rect bounds) {
        int cornerRadius = (int) (Math.min(bounds.width, bounds.height) * cornerRadiusRatio);
        Vex.fillRoundRect(bounds, cornerRadius);
      }
    };
  }
}
