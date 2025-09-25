package meow.meowfx.forms;

import meow.meowfx.internals.Element;

public class RadioButton extends Element<RadioButton> {
  public RadioButton(String text) {
    super(new RadioButton(text));
  }

  public boolean isSelected() {
    return getNode().isSelected();
  }

  public void setText(String text) {
    getNode().setText(text);
  }

  public String getText() {
    return getNode().getText();
  }

  public void onDisableChanged(Runnable runnable) {
    getNode().disabledProperty().addListener((o, oldVal, newVal) -> runnable.run());
  }
}