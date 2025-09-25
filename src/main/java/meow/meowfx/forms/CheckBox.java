package meow.meowfx.forms;

import meow.meowfx.internals.Element;

public class CheckBox extends Element<CheckBox> {

  public CheckBox(String text) {
    super(new CheckBox(text));
  }

  public boolean isChecked() {
    return getNode().isChecked();
  }

  public void disable() {
    getNode().setDisabled(true);
  }

  public void enable() {
    getNode().setDisabled(false);
  }

  public boolean isEnabled() {
    return !getNode().isDisabled();
  }
}