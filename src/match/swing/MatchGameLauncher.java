package match.swing;

import match.MatchGame;
import vex.Vex;
import vex.swing.SwingPlatform;

public class MatchGameLauncher {

  public static void main(String[] args) {
    Vex.platform = new SwingPlatform(false);
    Vex.platform.setUi(
        () -> MatchGame.doUi(0, 0, Vex.platform.getWidth(), Vex.platform.getHeight()));
  }
}
