package meow.meowfx.forms;

import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;
import meow.meowfx.internals.Element;

public class Spinner extends Element<javafx.scene.control.Spinner<Integer>> {

  public Spinner(int min, int max, int initialValue) {
    super(new javafx.scene.control.Spinner<>(min, max, initialValue));
    getNode().setEditable(true);
  }

  public void appendString(String string) {
    getNode().getValueFactory().setConverter(new StringConverter<>() {
        @Override
        public String toString(Integer value) {
          return value + " " + string;
        }

        @Override
        public Integer fromString(String string) {
          return Integer.parseInt(string.replace(string, ""));
        }
    });
  }

  public void onValueChanged(Runnable runnable) {
    getNode().valueProperty().addListener((o, oldVal, newVal) -> runnable.run());
  }

  public void range(int min, int max) {
    getNode().setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, getNode().getValue()));
  }

  public int getValue() {
    return getNode().getValue();
  }

  public void prompt(String promptText) {
    getNode().setPromptText(promptText);
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
}