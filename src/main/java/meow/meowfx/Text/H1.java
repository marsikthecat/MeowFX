package meow.meowfx.text;

import javafx.scene.paint.Color;
import meow.meowfx.internals.BaseText;

public class H1 extends BaseText {
  public H1(String string) {
    super(string);
    font(30, "Arial", Color.BLACK);
  }
}