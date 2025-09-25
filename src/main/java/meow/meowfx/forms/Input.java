package meow.meowfx.forms;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import meow.meowfx.internals.Element;

public class Input extends Element<TextField> {

  public Input() {
    super(new TextField());
  }

  public void promptText(String promptText) {
    getNode().setPromptText(promptText);
  }

  public void disable() {
    getNode().setDisable(true);
  }
  public void enable() {
    getNode().setDisable(false);
  }

  public boolean isItDisabled() {
    return getNode().isDisabled();
  }

  public void onContentChanged(Runnable runnable) {
    getNode().textProperty().addListener((o, oldVal, newVal) -> runnable.run());
  }

  public StringProperty getTextProperty() {
    return getNode().textProperty();
  }

  public String getValue() {
    return getNode().getText();
  }

  public void value(String s) {
    getNode().setText(s);
  }

  public void clear() {
    getNode().clear();
  }

  public void removeOneCharacter() {
    String currentText = getNode().getText();
    if (!currentText.isEmpty()) {
      getNode().setText(currentText.substring(0, currentText.length() - 1));
    }
  }

  public boolean isEmpty() {
    return getNode().getText().isEmpty();
  }

  public int getTextLength() {
    return getNode().getText().length();
  }

  public boolean isNumeric() {
    return getNode().getText().matches("\\d+");
  }

  public boolean isAlphabetic() {
    return getNode().getText().matches("[a-zA-Z]+");
  }

  public boolean isAlphanumeric() {
    return getNode().getText().matches("[a-zA-Z0-9]+");
  }

  public boolean isEmail() {
    return getNode().getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
  }

  public boolean isUrl() {
    return getNode().getText().matches("^(https?|ftp)://[^\s/$.?#].[^\s]*$");
  }

  public boolean isPhoneNumber() {
    return getNode().getText().matches("^\\+?[0-9]{10,15}$");
  }

  public boolean isDate() {
    return getNode().getText().matches("^\\d{4}-\\d{2}-\\d{2}$");
  }

  public boolean isTime() {
    return getNode().getText().matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
  }

  public void addCharacter(String string) {
    getNode().setText(getNode().getText() + string);
  }
}