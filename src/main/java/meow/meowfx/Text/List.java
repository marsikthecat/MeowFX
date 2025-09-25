package meow.meowfx.text;

import javafx.scene.control.Label;
import meow.meowfx.internals.Element;

public class List extends Element<Label> {
  public List(boolean numeric, String... strings) {
    super(new Label());
    StringBuilder stringBuilder = new StringBuilder(strings.length);
    Label label = getNode();
    for (int i = 0; i < strings.length; i++) {
      if (numeric) {
        stringBuilder.append(i).append(". ").append(strings[i]).append("\n");
      } else {
        stringBuilder.append((char) ('a' + (i))).append(". ").append(strings[i]).append("\n");
      }
    }
    label.setText(stringBuilder.toString());
  }
  // TODO: More god damn features.
}