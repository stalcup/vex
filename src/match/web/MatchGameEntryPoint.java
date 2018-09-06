package match.web;

import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.Document;
import elemental2.dom.HTMLCanvasElement;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import match.MatchGame;
import vex.Vex;
import vex.web.WebPlatform;

public class MatchGameEntryPoint implements EntryPoint {

  @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "window")
  private static class Window {
    @JsProperty public static Document document;
  }

  @Override
  public void onModuleLoad() {
    Vex.platform =
        new WebPlatform((HTMLCanvasElement) Window.document.getElementById("matchgame-canvas"));
    Vex.platform.setUi(
        () -> MatchGame.doUi(0, 0, Vex.platform.getWidth(), Vex.platform.getHeight()));
  }
}
