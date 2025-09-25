package meow.meowfx.snippets;

import javafx.scene.layout.HBox;
import meow.meowfx.forms.Button;
import meow.meowfx.forms.Input;
import meow.meowfx.internals.Element;

public class SearchBar extends Element<HBox> {

  private final Input input;
  private final Button button;

  public SearchBar() {
    super(new HBox());
    this.input = new Input();
    this.button = new Button("\uD83D\uDD0D");
    getNode().getChildren().addAll(input, button);
  }

  public String getSearchText() {
    return input.getValue();
  }

  public boolean isEmpty() {
    return input.getValue().isEmpty();
  }

  public Button getButton() {
    return button;
  }

  public Input getInput() {
    return input;
  }

  public void clearInput() {
    input.clear();
  }
}