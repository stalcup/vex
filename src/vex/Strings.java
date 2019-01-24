package vex;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import com.google.common.base.Preconditions;

public class Strings {

  public static String escapeChar(String input, String character) {
    return input.replace("\\", "\\y").replace(character, "\\x");
  }

  public static String unescapeChar(String input, String character) {
    return input.replace("\\x", character).replace("\\y", "\\");
  }

  public static String escapeSerial(String input) {
    Preconditions.checkNotNull(input);
    return escapeChar(input, ",");
  }

  public static String unescapeSerial(String input) {
    return unescapeChar(input, ",");
  }

  @SuppressWarnings("deprecation")
  public static String escapeDir(String input) {
    return URLEncoder.encode(Strings.escapeChar(input, "/"));
  }

  @SuppressWarnings("deprecation")
  public static String unescapeDir(String input) {
    return Strings.unescapeChar(URLDecoder.decode(input), "/");
  }

  public static String makePath(String... parts) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < parts.length; i++) {
      if (i > 0) {
        sb.append("/");
      }
      sb.append(escapeDir(parts[i]));
    }
    return sb.toString();
  }

  public static boolean equals(String a, String b) {
    return a == null ? b == null : a.equals(b);
  }

  public static Deque<String> splitPath(String path) {
    Deque<String> parts = new LinkedList<>(Arrays.asList(path.split("/", -1)));
    while (!parts.isEmpty() && parts.getFirst().isEmpty()) {
      parts.removeFirst();
    }
    return parts;
  }
}
