package meow.meowfx.SexySnippets;

import javafx.scene.layout.VBox;
import meow.meowfx.internals.Element;

public class VerticalIconBar extends Element<VBox> {

  public VerticalIconBar() {
    super(new VBox());
  }

  public void addIcons(Icon... icons) {
    getNode().getChildren().addAll(icons);
  }
}