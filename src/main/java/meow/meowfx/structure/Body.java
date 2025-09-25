package meow.meowfx.structure;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import meow.meowfx.internals.Element;
import meow.meowfx.internals.Storage.LocalStorage;
import java.io.File;

public class Body extends Pane {

  private final int[] windowData;
  private boolean resizable = true;

  public Body() {
    windowData = new int[6];
  }


  public void windowWidth(int windowWidth) {
    windowData[0] = windowWidth;
  }

  public void windowHeight(int windowHeight) {
    windowData[1] = windowHeight;
  }

  public void maxWindowWidth(int maxWindowWidth) {
    windowData[2] = maxWindowWidth;
  }

  public void maxWindowHeight(int maxWindowHeight) {
    windowData[3] = maxWindowHeight;
  }

  public void minWindowWidth(int minWindowWidth) {
    windowData[4] = minWindowWidth;
  }

  public void minWindowHeight(int minWindowHeight) {
    windowData[5] = minWindowHeight;
  }

  public void resizable(boolean resizable) {
    this.resizable = resizable;
  }

  public boolean isResizable() {
    return resizable;
  }

  public int[] getWindowData() {
    return windowData;
  }

  public void addAll(Node... elements) {
    for (Node element : elements) {
      if (element instanceof Element<?>) {
        getChildren().add(element);
      }
    }
  }

  public void setResponsivePosition(Node node, double anchorX) {
    if (node instanceof Region region) {
      region.layoutXProperty().bind(
              widthProperty().multiply(anchorX).subtract(region.widthProperty().divide(2)));
    } else {
      node.layoutXProperty().bind(widthProperty().multiply(anchorX));
    }
  }

  public void setResponsivePosition(Node node, double anchorX, double anchorY) {
    if (node instanceof Region region) {
      region.layoutXProperty().bind(
              widthProperty().multiply(anchorX).subtract(region.widthProperty().divide(2)));
      region.layoutYProperty().bind(
              heightProperty().multiply(anchorY).subtract(region.heightProperty().divide(2)));
    } else {
      node.layoutXProperty().bind(widthProperty().multiply(anchorX));
      node.layoutYProperty().bind(heightProperty().multiply(anchorY));
    }
  }

  public void setResponsiveWidth(Node node, double widthPercent) {
    if (node instanceof Region region) {
      region.prefWidthProperty().bind(widthProperty().multiply(widthPercent));
    } else {
      node.prefWidth(widthProperty().get() * widthPercent);
    }
  }

  public void setResponsiveWidthAll(double widthPercent, Node... nodes) {
    for (Node node : nodes) {
      if (node instanceof Region region) {
        region.prefWidthProperty().bind(widthProperty().multiply(widthPercent));
      } else {
        node.prefWidth(widthProperty().get() * widthPercent);
      }
    }
  }

  public void setResponsiveHeight(Node node, double heightPercent) {
    if (node instanceof Region region) {
      region.prefHeightProperty().bind(heightProperty().multiply(heightPercent));
    } else {
      node.prefHeight(heightProperty().get() * heightPercent);
    }
  }

  public void setBelowWindowWidth(int width, Runnable runnable) {
    if (width < 0) {
      throw new IllegalArgumentException("Width must be positive");
    }
    widthProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.doubleValue() < width) {
        runnable.run();
      }
    });
  }

  public void setAboveWindowWidth(int width, Runnable runnable) {
    if (width < 0) {
      throw new IllegalArgumentException("Width must be positive");
    }
    widthProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.doubleValue() > width) {
        runnable.run();
      }
    });
  }

  protected void loadLocalStorage() {
    File file = new File("data.json");
    if (file.exists()) {
      LocalStorage.fromJson();
    }
  }

  protected void saveLocalStorage() {
    LocalStorage.toJson();
  }
}