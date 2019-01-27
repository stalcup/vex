package todomvc.web;

import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLCanvasElement;
import todomvc.TodoMvc;
import vex.Vex;
import vex.web.WebPlatform;

public class TodoMvcEntryPoint implements EntryPoint {

  @Override
  public void onModuleLoad() {
    Vex.platform =
        new WebPlatform((HTMLCanvasElement) DomGlobal.document.getElementById("todomvc-canvas"));
    Vex.setUi(() -> TodoMvc.doUi(0, 0, Vex.getWidth(), Vex.getHeight()));
  }
}
