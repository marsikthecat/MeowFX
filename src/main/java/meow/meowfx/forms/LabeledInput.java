package meow.meowfx.forms;

import javafx.scene.layout.HBox;
import meow.meowfx.text.P;
import meow.meowfx.internals.Element;

public class LabeledInput extends Element<HBox> {
  public LabeledInput(P p, Input input) {
    super(new HBox(p, input));
  }

  public void promptText(String promptText) {
    Input input = (Input) getNode().getChildren().get(0);
    input.promptText(promptText);
  }

  public Input getInput() {
    return (Input) getNode().getChildren().get(1);
  }

  public void label(String lableText) {
    P label = (P) getNode().getChildren().get(0);
    label.text(lableText);
  }

  public String getLabel() {
    P label = (P) getNode().getChildren().get(0);
    return label.getValue();
  }

  public String getValue() {
    Input input = (Input) getNode().getChildren().get(1);
    return input.getValue();
  }
}