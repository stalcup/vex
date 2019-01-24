package vex;

public enum FontStyle {
  BOLD("bold", 1),
  ITALIC("italic", 2),
  PLAIN("normal", 0);

  public final int code;
  public final String name;

  FontStyle(String name, int code) {
    this.name = name;
    this.code = code;
  }
}
