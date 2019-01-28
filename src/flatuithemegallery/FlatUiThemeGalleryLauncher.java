package flatuithemegallery;

import vex.Vex;
import vex.geom.Rect;
import vex.swing.SwingPlatform;

public class FlatUiThemeGalleryLauncher {

  public static void main(String[] args) {
    Vex.platform = new SwingPlatform(false);
    Vex.setUi(
        () -> FlatUiThemeGallery.doUi(new Rect("root", 0, 0, Vex.getWidth(), Vex.getHeight())));
  }
}
