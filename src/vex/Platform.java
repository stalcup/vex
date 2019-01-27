package vex;

import jsinterop.annotations.JsType;
import vex.events.KeyEvent;
import vex.events.MouseEvent;
import vex.geom.Point;

@JsType
public interface Platform {

  @FunctionalInterface
  interface ResponseMessageHandler {
    void handleResponseMessage(String responseMessage);
  }

  void beginLayer();

  void consumeKeyEvent();

  void consumeMouseEvent();

  void doAfterFrame(Runnable callback);

  void endLayer();

  int getFrameid();

  int getHeight();

  KeyEvent getKeyEvent();

  String getLocation();

  MouseEvent getMouseEvent();

  Point getMouseLocation();

  int getWidth();

  void httpGet(String path, ResponseMessageHandler responseMessageHandler);

  void httpPost(String path, String body, ResponseMessageHandler responseMessageHandler);

  void println(String line);

  void setCursor(Cursor cursor);

  void setLocation(String location);

  void setTitle(String title);

  void setUi(Runnable ui);

  void repaint();
}
