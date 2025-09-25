package meow.meowfx.graphic;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import meow.meowfx.internals.Element;

public class Hr extends Element<Separator> {
  public Hr() {
    super(new Separator(Orientation.HORIZONTAL));
    appendStyleForNode("background-color", "#eee");
    getNode().setPrefHeight(1);
  }

  public void color(String color) {
    removeStyleForNode("background-color");
    appendStyleForNode("background-color", color);
  }

  public void thickness(double thickness) {
    getNode().setPrefHeight(thickness);
  }

  public void width(double width) {
    getNode().setPrefWidth(width);
  }
}