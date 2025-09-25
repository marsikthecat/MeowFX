package meow.meowfx.snippets;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import meow.meowfx.forms.IconButton;
import meow.meowfx.internals.Element;

public class Menu extends Element<VBox> {

  private final IconButton menuButton;
  private boolean isVisible = false;
  private final TranslateTransition transition =
          new TranslateTransition(Duration.seconds(0.6), this);

  public Menu() {
    super(new VBox());
    menuButton = new IconButton(IconTypes.MENU);
    Platform.runLater(() -> setTranslateX(-getWidth()));
    menuButton.onclick(this::toggle);
  }

  public IconButton getMenuButton() {
    return menuButton;
  }

  public void addElements(Node... nodes) {
    getNode().getChildren().addAll(nodes);
  }
  public void fadeDuration(double seconds) {
    transition.setDuration(Duration.seconds(seconds));
  }
  private void toggle() {
    double targetX = isVisible ? -getWidth() : 0;
    transition.setToX(targetX);
    transition.play();
    menuButton.setIcon(isVisible ? IconTypes.MENU : IconTypes.CROSS);
    isVisible = !isVisible;
  }

  public void padding(double top, double right, double bottom, double left) {
    getNode().setPadding(new Insets(top, right, bottom, left));
  }

  public void elementSpacing(double spacing) {
    getNode().setSpacing(spacing);
  }
}