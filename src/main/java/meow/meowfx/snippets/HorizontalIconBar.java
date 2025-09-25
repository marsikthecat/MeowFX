package meow.meowfx.snippets;

import javafx.scene.layout.HBox;
import meow.meowfx.internals.Element;

public class HorizontalIconBar extends Element<HBox> {

  public HorizontalIconBar() {
    super(new HBox());
  }

  public void addIcons(Icon... icons) {
    getNode().getChildren().addAll(icons);
  }
}