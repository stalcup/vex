package vex.themes.flatui;

import java.io.IOException;
import java.net.URISyntaxException;

import vex.Base64DirectoryImageResourcesBuilder;

public class ImagesBuilderMain {
  public static void main(String[] args) throws URISyntaxException, IOException {
    String eclipseVariableContainerLoc = args[0];
    new Base64DirectoryImageResourcesBuilder(eclipseVariableContainerLoc, ImagesBuilderMain.class)
        .build();
  }
}
