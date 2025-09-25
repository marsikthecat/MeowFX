package meow.meowfx.text;

import javafx.scene.paint.Color;
import meow.meowfx.internals.BaseText;

public class H5 extends BaseText {
  public H5(String string) {
    super(string);
    font(15, "Arial", Color.BLACK);
  }
}