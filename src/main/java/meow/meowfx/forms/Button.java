package meow.meowfx.forms;

import javafx.scene.Cursor;
import meow.meowfx.internals.Element;

public class Button extends Element<javafx.scene.control.Button> {

  public Button(String txt) {
    super(new javafx.scene.control.Button(txt));
    cursor(Cursor.HAND);
  }

  public void text(String txt) {
    getNode().setText(txt);
  }

  public String getText() {
    return getNode().getText();
  }
}