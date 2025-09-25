package meow.meowfx.internals.BasicAPIs;

public class Console {

  private Console() {}
  public static void debug(String message) {
    System.out.println("\u001B[34m" + message + "\u001B[0m");
  }
  public static void log(String message) {
    System.out.println(message);
  }
  public static void warn(String message) {
    System.out.println("\u001B[33m" + message + "\u001B[0m");
  }
  public static void error(String message) {
    System.err.println(message);
  }
}