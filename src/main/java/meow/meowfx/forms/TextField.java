package meow.meowfx.forms;

import javafx.scene.control.TextArea;
import meow.meowfx.internals.Element;

public class TextField extends Element<TextArea> {
  public TextField() {
    super(new TextArea());
    getNode().setWrapText(true);
  }

  public int cursorPosition() {
    return getNode().getCaretPosition();
  }

  public void moveCursorPosition(int pos) {
    getNode().positionCaret(pos);
  }

  public void incrementCursorPosition() {
    getNode().positionCaret(cursorPosition() + 1);
  }

  public void decrementCursorPosition() {
    getNode().positionCaret(cursorPosition() - 1);
  }

  public void selectEveryThing() {
    getNode().selectAll();
  }

  public void onCursorPositionChanged(Runnable runnable) {
    getNode().caretPositionProperty().addListener((o, oldVal, newVal) -> runnable.run());
  }

  public void promptText(String promptText) {
    getNode().setPromptText(promptText);
  }

  public String getValue() {
    return getNode().getText();
  }

  public void insert(String string) {
    getNode().setText(getNode().getText() + string);
  }

  public void onInsert(Runnable runnable) {
    getNode().textProperty().addListener((observable, oldValue, newValue) -> runnable.run());
  }

  public void onFocus(Runnable runnable) {
    getNode().focusedProperty().addListener((o, oldVal, newVal) -> {
      if (newVal) {
        runnable.run();
      }
    });
  }

  public void onFocusLost(Runnable runnable) {
    getNode().focusedProperty().addListener((o, oldVal, newVal) -> {
      if (!newVal) {
        runnable.run();
      }
    });
  }

  public void disable() {
    getNode().setDisable(true);
  }

  public void enable() {
    getNode().setDisable(false);
  }

  public boolean isEnabled() {
    return !getNode().isDisabled();
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
}