package meow.meowfx.forms;

import meow.meowfx.SexySnippets.Icon;
import meow.meowfx.SexySnippets.IconTypes;

public class IconButton extends Button {

  private final Icon icon;

  public IconButton(IconTypes iconType) {
    super("");
    this.icon = new Icon(iconType);
    super.getNode().setGraphic(icon);
  }

  public Icon getIcon() {
    return icon;
  }

  public IconTypes getIconType() {
    return icon.getIconType();
  }

  public void setIcon(IconTypes iconType) {
    icon.setIcon(iconType);
  }
}