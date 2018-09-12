package vex;

public class Color {
  public static final Color BLACK = new Color(0, 0, 0);
  public static final Color GRAY = new Color(128, 128, 128);
  public static final Color GRAY_10 = new Color(0.10f, 0.10f, 0.10f);
  public static final Color GRAY_20 = new Color(0.20f, 0.20f, 0.20f);
  public static final Color GRAY_30 = new Color(0.30f, 0.30f, 0.30f);
  public static final Color GRAY_40 = new Color(0.40f, 0.40f, 0.40f);
  public static final Color GRAY_50 = new Color(0.50f, 0.50f, 0.50f);
  public static final Color GRAY_60 = new Color(0.60f, 0.60f, 0.60f);
  public static final Color GRAY_70 = new Color(0.70f, 0.70f, 0.70f);
  public static final Color GRAY_80 = new Color(0.80f, 0.80f, 0.80f);
  public static final Color GRAY_90 = new Color(0.90f, 0.90f, 0.90f);
  public static final Color GRAY_95 = new Color(0.95f, 0.95f, 0.95f);
  public static final Color WHITE = new Color(255, 255, 255);
  public static final Color YELLOW = new Color(255, 255, 0);
  public static final Color HIGH_HAZE = new Color(0, 0, 0, 64);
  public static final Color MEDIUM_HAZE = new Color(0, 0, 0, 32);
  public static final Color LOW_HAZE = new Color(0, 0, 0, 16);

  public final int r, g, b, a;

  public Color(float r, float g, float b) {
    this.r = (int) (r * 256);
    this.g = (int) (g * 256);
    this.b = (int) (b * 256);
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
}
