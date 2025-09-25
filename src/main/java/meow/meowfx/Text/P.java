package meow.meowfx.text;

import javafx.scene.paint.Color;
import meow.meowfx.internals.BaseText;

public class P extends BaseText {
  public P(String string) {
    super(string);
    font(13,"Arial", Color.BLACK);
  }
}