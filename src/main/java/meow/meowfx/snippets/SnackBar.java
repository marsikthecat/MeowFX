package meow.meowfx.snippets;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import meow.meowfx.text.P;
import meow.meowfx.internals.Element;

public class SnackBar extends Element<HBox> {

  private boolean visible = false;
  private double startPos = 800.0;
  private double endPos = 500.0;

  private final TranslateTransition movement =
            new TranslateTransition(Duration.seconds(0.6), this);

  private final FadeTransition fadeInout =
          new FadeTransition(Duration.seconds(0.6), this);

  private final PauseTransition pauseTransition =
          new PauseTransition(Duration.seconds(3));
  public SnackBar() {
    super(new HBox());
    Platform.runLater(() -> setTranslateY(startPos));
    backgroundColor("black");
  }

  public void startPosition(double y) {
    startPos = y;
    Platform.runLater(() -> setTranslateY(y));
  }

  public void endPosition(double y) {
    endPos = y;
  }

  public void animationDuration(double seconds) {
    movement.setDuration(Duration.seconds(seconds));
    fadeInout.setDuration(Duration.seconds(seconds));
  }

  public void popUpDuration(double seconds) {
    pauseTransition.setDuration(Duration.seconds(seconds));
  }

  public void action() {
    if (visible) {
      return;
    }
    fadeInout.setFromValue(0);
    fadeInout.setToValue(1);
    movement.setToY(endPos);
    movement.play();
    fadeInout.play();
    visible = true;
    pauseTransition.setOnFinished(event -> {
      fadeInout.setFromValue(1);
      fadeInout.setToValue(0);
      movement.setToY(startPos);
      fadeInout.play();
      movement.play();
      visible = false;
    });
    pauseTransition.play();
  }

  public void message(String message) {
    P p = new P(message);
    p.fontColor(Color.WHITE);
    p.bold();
    getNode().getChildren().add(p);
  }

  public void backgroundColor(String color) {
    super.backgroundColor(color);
  }

  public void textColor(Color color) {
    if (getNode().getChildren().isEmpty()) {
      throw new IllegalStateException("text cannot be colored, because there is no text yet.");
    }
    P p = (P) getNode().getChildren().get(0);
    p.fontColor(color);
  }

  public void padding(double top, double right, double bottom, double left) {
    getNode().setPadding(new Insets(top, right, bottom, left));
  }
}
