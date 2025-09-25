package meow.meowfx.forms;

import meow.meowfx.internals.Element;

public class PasswordInput extends Element<Input> {

  private String value;
  private boolean isPasswordVisible;

  public PasswordInput() {
    super(new Input());
    this.value = "";
    isPasswordVisible = false;
    getNode().getTextProperty().addListener((observable, oldValue, newValue) -> {
      if (isPasswordVisible) {
        getNode().value(newValue);
      } else {
        getNode().value("•".repeat(newValue.length()));
      }
      value = newValue;
    });
  }

  public void showPassword() {
    if (isPasswordVisible) {
      return;
    }
    isPasswordVisible = true;
    getNode().value(value);
  }

  public void hidePassword() {
    if (!isPasswordVisible) {
      return;
    }
    isPasswordVisible = false;
    getNode().value("•".repeat(value.length()));
  }

  public String getPassword() {
    return value;
  }
}