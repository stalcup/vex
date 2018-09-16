package scafolde;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class ScafoldeServer extends NanoHTTPD {

  public ScafoldeServer(int port) {
    super(port);
  }

  public void start() throws IOException {
    start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    System.out.println("ScafoldeServer started at http://localhost:" + getListeningPort() + "/ \n");
  }

  @Override
  public Response serve(IHTTPSession session) {
    if (session.getUri().equals("/functions/rand")) {
      return newFixedLengthResponse(Math.random() + "");
    }

    return newFixedLengthResponse("");
  }
}
