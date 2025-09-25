package meow.meowfx.snippets;

import javafx.scene.control.TitledPane;
import meow.meowfx.internals.Element;

public class Accordion extends Element<javafx.scene.control.Accordion> {

  public Accordion(String headLine, Element<?> element) {
    super(new javafx.scene.control.Accordion());
    TitledPane pane = new TitledPane(headLine, element);
    getNode().getPanes().add(pane);
  }

  public boolean isExpanded() {
    return getNode().getExpandedPane().isExpanded();
  }

  public void setExpanded(boolean expanded) {
    getNode().getExpandedPane().setExpanded(expanded);
  }
}