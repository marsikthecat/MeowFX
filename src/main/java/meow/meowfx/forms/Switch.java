package meow.meowfx.forms;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import meow.meowfx.internals.Element;

public class Switch extends Element<javafx.scene.control.Slider> {

  private final BooleanProperty isOn;

  public Switch(boolean isSwitchedOn) {
    super(new javafx.scene.control.Slider());
    this.isOn = new SimpleBooleanProperty(isSwitchedOn);
    getNode().setShowTickMarks(false);
    getNode().setShowTickLabels(false);
    getNode().setOnMousePressed(event -> {
      getNode().setValue(isOn.get() ? getNode().getMin() : getNode().getMax());
      isOn.set(!isOn.get());
    });
    getNode().setPrefWidth(40);
  }
  public boolean getSwitchState() {
    return isOn.get();
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

  public void onStateChanged(Runnable runnable) {
    isOn.addListener((o, oldVal, newVal) -> runnable.run());
  }
}