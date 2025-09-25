package meow.meowfx.snippets;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import meow.meowfx.internals.Element;

public class NavBar extends Element<HBox> {

  public NavBar() {
    super(new HBox());
  }

  public void addMenuItems(Node... nodes) {
    getNode().getChildren().addAll(nodes);
    for (Node node : nodes) {
      node.maxHeight(Double.MAX_VALUE);
    }
  }

  public void padding(int t, int r, int b, int l) {
    getNode().setPadding(new Insets(t, r, b, l));
  }

  public void horizontalSpacing(double spacing) {
    getNode().setSpacing(spacing);
  }
}