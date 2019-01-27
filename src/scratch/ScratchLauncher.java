package scratch;

import vex.Vex;
import vex.swing.SwingPlatform;

public class ScratchLauncher {

  public static void main(String[] args) {
    Vex.platform = new SwingPlatform(false);
    Vex.setUi(() -> Scratch.doUi(0, 0, Vex.getWidth(), Vex.getHeight()));
  }
}
