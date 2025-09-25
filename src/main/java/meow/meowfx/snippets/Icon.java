package meow.meowfx.snippets;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import meow.meowfx.internals.Element;

public class Icon extends Element<Label> {

  private IconTypes iconType;
  public Icon(IconTypes iconType) {
    super(new Label(iconType.getUnicode()));
    this.iconType = iconType;
  }

  public void setIcon(IconTypes iconType) {
    getNode().setText(iconType.getUnicode());
    this.iconType = iconType;
  }

  public IconTypes getIconType() {
    return iconType;
  }

  public void size(int s) {
    getNode().setFont(new Font(s));
  }

  public void color(Color color) {
    getNode().setTextFill(color);
  }
}