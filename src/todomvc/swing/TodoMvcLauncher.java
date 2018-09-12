package todomvc.swing;

import todomvc.TodoMvc;
import vex.Vex;
import vex.swing.SwingPlatform;

public class TodoMvcLauncher {

  public static void main(String[] args) {
    Vex.platform = new SwingPlatform(false);
    Vex.platform.setUi(() -> TodoMvc.doUi(0, 0, Vex.platform.getWidth(), Vex.platform.getHeight()));
  }
}
