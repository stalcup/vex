package vex;

import java.util.Set;

public class Sets {

  public static <T> void toggle(Set<T> set, T value) {
    if (set.contains(value)) {
      set.remove(value);
    } else {
      set.add(value);
    }
  }
}
