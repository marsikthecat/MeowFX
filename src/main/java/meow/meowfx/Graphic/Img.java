package meow.meowfx.graphic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import meow.meowfx.internals.Element;

public class Img extends Element<ImageView> {
  public Img(String path) {
    super(new ImageView());
    getNode().setImage(new Image(path));
    getNode().setPreserveRatio(true);
  }

  public void image(String path) {
    getNode().setImage(new Image(path));
  }

  public void rotate(int angle) {
    getNode().setRotate(angle);
  }

  public void width(int width) {
    getNode().setFitWidth(width);
  }

  public void height(int height) {
    getNode().setFitHeight(height);
  }
}