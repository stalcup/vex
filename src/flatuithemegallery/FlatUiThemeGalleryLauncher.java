package flatuithemegallery;

import vex.Rect;
import vex.Vex;
import vex.swing.SwingPlatform;

public class FlatUiThemeGalleryLauncher {

  public static void main(String[] args) {
    Vex.platform = new SwingPlatform(false);
    Vex.platform.setUi(
        () ->
            FlatUiThemeGallery.doUi(
                new Rect(0, 0, Vex.platform.getWidth(), Vex.platform.getHeight())));
  }
}
