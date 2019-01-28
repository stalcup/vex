package match.web;

import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLCanvasElement;
import match.MatchGame;
import vex.Vex;
import vex.web.WebPlatform;

public class MatchGameEntryPoint implements EntryPoint {

  @Override
  public void onModuleLoad() {
    Vex.platform =
        new WebPlatform((HTMLCanvasElement) DomGlobal.document.getElementById("matchgame-canvas"));
    Vex.setUi(() -> MatchGame.doUi(0, 0, Vex.getWidth(), Vex.getHeight()));
  }
}
