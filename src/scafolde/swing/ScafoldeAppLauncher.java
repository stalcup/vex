package scafolde.swing;

import java.io.IOException;

import scafolde.ScafoldeClient;
import scafolde.ScafoldeServer;
import vex.Vex;
import vex.swing.SwingPlatform;

public class ScafoldeAppLauncher {

  public static void main(String[] args) throws IOException {
    ScafoldeServer scafoldeServer = new ScafoldeServer(80);
    scafoldeServer.start();

    Vex.platform = new SwingPlatform(false);
    Vex.platform.setUi(
        () -> ScafoldeClient.doUi(0, 0, Vex.platform.getWidth(), Vex.platform.getHeight()));
  }
}
