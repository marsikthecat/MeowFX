package meow.meowfx.internals.BasicAPIs;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class ClipBoard {

  private static String clickBoardContent;
  private ClipBoard() {}

  public static void copyToClickBoard(String string) {
    ClipboardContent content = new ClipboardContent();
    content.putString(string);
    clickBoardContent = string;
    Clipboard.getSystemClipboard().setContent(content);
  }

  public static String getClickBoardContent() {
    return clickBoardContent;
  }
}