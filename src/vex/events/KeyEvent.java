package vex.events;

import java.util.HashMap;
import java.util.Map;

public class KeyEvent {
  private static Map<String, String> normalizedByKeyText = new HashMap<>();

  static {
    normalizedByKeyText.put("ArrowLeft", "Left");
    normalizedByKeyText.put("ArrowRight", "Right");
    normalizedByKeyText.put("ControlLeft", "Ctrl");
    normalizedByKeyText.put("ControlRight", "Ctrl");
    normalizedByKeyText.put("NumpadEnter", "Enter");
  }

  public static enum Type {
    UP,
    DOWN,
    TYPE
  }

  public String key;
  public String keyText;
  public Type type;
  public boolean printable;

  public KeyEvent(String key, String keyText, Type type, boolean printable) {
    this.key = key;
    this.keyText =
        normalizedByKeyText.containsKey(keyText) ? normalizedByKeyText.get(keyText) : keyText;
    this.type = type;
    this.printable = printable;
  }
}
