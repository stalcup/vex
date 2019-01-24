package vex;

public class Color {

  public static final Color BLACK = new Color(0, 0, 0);
  public static final Color GRAY = new Color(127, 127, 127);
  public static final Color GRAY_10 = new Color(0.10f, 0.10f, 0.10f);
  public static final Color GRAY_20 = new Color(0.20f, 0.20f, 0.20f);
  public static final Color GRAY_30 = new Color(0.30f, 0.30f, 0.30f);
  public static final Color GRAY_40 = new Color(0.40f, 0.40f, 0.40f);
  public static final Color GRAY_50 = new Color(0.50f, 0.50f, 0.50f);
  public static final Color GRAY_60 = new Color(0.60f, 0.60f, 0.60f);
  public static final Color GRAY_70 = new Color(0.70f, 0.70f, 0.70f);
  public static final Color GRAY_75 = new Color(0.75f, 0.75f, 0.75f);
  public static final Color GRAY_80 = new Color(0.80f, 0.80f, 0.80f);
  public static final Color GRAY_85 = new Color(0.85f, 0.85f, 0.85f);
  public static final Color GRAY_90 = new Color(0.90f, 0.90f, 0.90f);
  public static final Color GRAY_95 = new Color(0.95f, 0.95f, 0.95f);
  public static final Color WHITE = new Color(255, 255, 255);

  // We got all the colors.
  public static final Color RED = new Color(255, 0, 0);
  public static final Color ORANGE = new Color(255, 127, 0);
  public static final Color YELLOW = new Color(255, 255, 0);
  public static final Color YEGEEN = new Color(127, 255, 0);
  public static final Color GREEN = new Color(0, 255, 0);
  public static final Color CYAN = new Color(0, 255, 255);
  public static final Color BLUE = new Color(0, 0, 255);
  public static final Color PURPLE = new Color(127, 0, 255);
  public static final Color PINKLE = new Color(255, 0, 255);
  public static final Color PINK = new Color(255, 0, 127);

  public static final Color HIGH_HAZE = new Color(0, 0, 0, 63);
  public static final Color MEDIUM_HAZE = new Color(0, 0, 0, 31);
  public static final Color LOW_HAZE = new Color(0, 0, 0, 15);
  public static final Color VERY_LOW_HAZE = new Color(0, 0, 0, 7);

  public static final Color HIGH_MIST = new Color(255, 255, 255, 191);
  public static final Color MEDIUM_MIST = new Color(255, 255, 255, 127);
  public static final Color LOW_MIST = new Color(255, 255, 255, 63);
  public static final Color VERY_LOW_MIST = new Color(255, 255, 255, 31);

  public static final Color BLUE_80 = new Color(0.80f, 0.80f, 1.00f);
  public static final Color BLUE_95 = new Color(0.95f, 0.95f, 1.00f);

  public int r, g, b, a;

  public Color() {}

  public Color(float r, float g, float b) {
    this.r = (int) (r * 255);
    this.g = (int) (g * 255);
    this.b = (int) (b * 255);
    a = 255;
  }

  public Color(float r, float g, float b, float a) {
    this.r = (int) (r * 256);
    this.g = (int) (g * 256);
    this.b = (int) (b * 256);
    this.a = (int) (a * 256);
  }

  public Color(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
    a = 255;
  }

  public Color(int r, int g, int b, int a) {
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }

  public Color lighter(float lighter) {
    Color lighterColor = new Color();
    lighterColor.r = (int) (r * (1 - lighter) + 255 * lighter);
    lighterColor.g = (int) (g * (1 - lighter) + 255 * lighter);
    lighterColor.b = (int) (b * (1 - lighter) + 255 * lighter);
    lighterColor.a = a;
    return lighterColor;
  }

  public Color darker(float darker) {
    Color darkerColor = new Color();
    darkerColor.r = (int) (r * (1 - darker) + 0 * darker);
    darkerColor.g = (int) (g * (1 - darker) + 0 * darker);
    darkerColor.b = (int) (b * (1 - darker) + 0 * darker);
    darkerColor.a = a;
    return darkerColor;
  }

  public Color desaturate(float desaturation) {
    int grey = (r + g + b) / 3;
    Color desaturatedColor = new Color();
    desaturatedColor.r = (int) (r * (1 - desaturation) + grey * desaturation);
    desaturatedColor.g = (int) (g * (1 - desaturation) + grey * desaturation);
    desaturatedColor.b = (int) (b * (1 - desaturation) + grey * desaturation);
    desaturatedColor.a = a;
    return desaturatedColor;
  }

  @Override
  public String toString() {
    return "Color [r=" + r + ", g=" + g + ", b=" + b + ", a=" + a + "]";
  }
}
