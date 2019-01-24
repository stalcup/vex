package vex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

public class Base64DirectoryImageResourcesBuilder {
  private Set<String> imageExtensions = new HashSet<>(Arrays.asList("png", "jpg", "jpeg", "gif"));
  private File directory;
  private String javaPackage;

  public Base64DirectoryImageResourcesBuilder(String srcDir, Class<?> classLiteral) {
    directory = new File(srcDir);
    javaPackage = classLiteral.getPackage().toString();
  }

  private static String getExtension(String fileName) {
    int i = fileName.lastIndexOf('.');
    if (i > 0) {
      return fileName.substring(i + 1);
    }
    return "";
  }

  public void build(String newFileName) throws IOException {
    StringBuilder sb = new StringBuilder();

    sb.append(javaPackage + ";\n");
    sb.append("\n");
    sb.append("import vex.Base64Image;\n");
    sb.append("\n");
    sb.append("public class " + newFileName + " {\n");
    sb.append("\n");

    for (File file : directory.listFiles()) {
      String fileName = file.getName();
      String extension = getExtension(fileName);
      if (imageExtensions.contains(extension)) {
        String name = fileName.substring(0, fileName.length() - extension.length() - 1);

        String data = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();

        sb.append("  public static Base64Image " + name + " =\n");
        sb.append("      new Base64Image(\n");
        sb.append("          \"" + extension + "\",\n");
        sb.append("          " + width + ",\n");
        sb.append("          " + height + ",\n");
        sb.append("          \"" + data + "\");\n");
      }
    }

    sb.append("}\n");

    String classContent = sb.toString();
    byte[] bytes = classContent.getBytes(StandardCharsets.UTF_8);

    Path imageResourcesClassPath = new File(directory, newFileName + ".java").toPath();
    Files.write(imageResourcesClassPath, bytes);
    System.out.println(
        "Recreated " + imageResourcesClassPath + " with size " + bytes.length + " bytes.");
  }
}
