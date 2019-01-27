package todomvc.swing;

import todomvc.TodoMvc;
import vex.Vex;
import vex.swing.SwingPlatform;

public class TodoMvcLauncher {

  public static void main(String[] args) {
    Vex.platform = new SwingPlatform(false);
    Vex.setUi(() -> TodoMvc.doUi(0, 0, Vex.getWidth(), Vex.getHeight()));
  }
}
