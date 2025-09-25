package meow.meowfx.snippets;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import meow.meowfx.internals.Element;

public class Loader extends Element<Circle> {

 private final RotateTransition rotateTransition;

  public Loader(int size, Color color, int animationSpeed) {
    super(new Circle(size, Color.TRANSPARENT));
    getNode().setStroke(color);
    getNode().setStrokeWidth(4);
    getNode().setStrokeType(StrokeType.OUTSIDE);
    getNode().getStrokeDashArray().addAll(15.0, 10.0);
    rotateTransition = new RotateTransition(Duration.millis(animationSpeed), getNode());
    rotateTransition.setByAngle(360);
    rotateTransition.setCycleCount(Animation.INDEFINITE);
    rotateTransition.setInterpolator(Interpolator.LINEAR);
    rotateTransition.play();
  }

  public void stop() {
    rotateTransition.stop();
  }

  public void spin() {
    rotateTransition.pause();
  }
}