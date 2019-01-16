package vex.styles;

import vex.Rect;
import vex.Vex;

public abstract class StyleShape {

  public abstract void draw(Rect bounds);

  public abstract void fill(Rect bounds);

  public static StyleShape OVAL =
      new StyleShape() {
        @Override
        public void draw(Rect bounds) {
          Vex.platform.getGraphics().drawOval(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        @Override
        public void fill(Rect bounds) {
          Vex.platform.getGraphics().fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
        }
      };

  public static StyleShape RECT =
      new StyleShape() {
        @Override
        public void draw(Rect bounds) {
          Vex.platform.getGraphics().drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        @Override
        public void fill(Rect bounds) {
          Vex.platform.getGraphics().fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
      };

  public static StyleShape roundRect(double cornerRadiusRatio) {
    return new StyleShape() {
      @Override
      public void draw(Rect bounds) {
        int cornerRadius = (int) (Math.min(bounds.width, bounds.height) * cornerRadiusRatio);
        Vex.platform
            .getGraphics()
            .drawRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, cornerRadius);
      }

      @Override
      public void fill(Rect bounds) {
        int cornerRadius = (int) (Math.min(bounds.width, bounds.height) * cornerRadiusRatio);
        Vex.platform
            .getGraphics()
            .fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, cornerRadius);
      }
    };
  }
}
