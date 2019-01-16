package vex;

public class Base64Image {
  public final String type;
  public final int width;
  public final int height;
  public final String data;

  public Base64Image(String type, int width, int height, String data) {
    this.type = type;
    this.width = width;
    this.height = height;
    this.data = data;
  }

  @Override
  public int hashCode() {
    return System.identityHashCode(this);
  }
}
