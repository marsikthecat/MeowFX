package meow.meowfx.internals;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;

public abstract class Element<T extends Node> extends Region {

  private final T node;
  private final Map<String, String> stylesMapForNode = new HashMap<>();
  private final Map<String, String> stylesMapForRegion = new HashMap<>();

  public Element(T node) {
    this.node = node;
    getChildren().add(node);
  }

  public void width(int width) {
    setPrefWidth(width);
  }

  public int width() {
    return (int) getPrefWidth();
  }

  public void height(int height) {
    setPrefHeight(height);
  }


  public int height() {
    return (int) getPrefHeight();
  }

  public void margin(int margin) {
    VBox.setMargin(this, new Insets(margin));
  }

  public void margin(int top, int right, int bottom, int left) {
    VBox.setMargin(this, new Insets(top, right, bottom, left));
  }

  public void margin(int vertical, int horizontal) {
    VBox.setMargin(this, new Insets(vertical, horizontal, vertical, horizontal));
  }

  public void border(String width, String color, String roundness) {
    appendStyleForRegion("-fx-border-width", width);
    appendStyleForRegion("-fx-border-color", color);
    appendStyleForRegion("-fx-border-radius", roundness);
    updateRegionStyle();
  }

  public void backgroundColor(String color) {
    appendStyleForRegion("-fx-background-color", color);
    updateRegionStyle();
  }

  public void onclick(Runnable runnable) {
    node.setOnMouseClicked(e -> runnable.run());
  }

  public void hover(String key, String value) {
    node.setOnMouseEntered(e -> appendStyleForRegion(key, value));
    node.setOnMouseExited(e -> removeStyleForRegion(key));
  }

  protected T getNode() {
    return node;
  }

  public void pos(int x, int y) {
    setLayoutX(x);
    setLayoutY(y);
  }

  public void setX(int x) {
    setLayoutX(x);
  }

  public void setY(int y) {
    setLayoutY(y);
  }

  public void attachYTopOutsideTo(Node othernode) {
    if (othernode instanceof Region) {
      layoutYProperty().bind(othernode.layoutYProperty().subtract(heightProperty()));
    }
  }

  public void attachYTopInsideTo(Node othernode) {
    if (othernode instanceof Region region) {
      layoutYProperty().bind(region.layoutYProperty());
    }
  }

  public void attachYCenter(Node othernode) {
    if (othernode instanceof Region region) {
      layoutYProperty().bind(region.layoutYProperty().add(region.heightProperty().divide(2))
                  .subtract(heightProperty().divide(2)));
    }
  }

  public void attachYBottomInsideTo(Node othernode) {
    if (othernode instanceof Region region) {
      layoutYProperty().bind(region.layoutYProperty().add(
              region.heightProperty().subtract(heightProperty())));
    }
  }

  public void attachYBottomOutsideTo(Node othernode) {
    if (othernode instanceof Region region) {
    layoutYProperty().bind(region.layoutYProperty().add(region.heightProperty()));
    }
  }

  public void attachXLeftInsideTo(Node othernode) {
    if (othernode instanceof Region region) {
      layoutXProperty().bind(region.layoutXProperty());
    }
  }

  public void attachXLeftOutsideTo(Node othernode) {
    if (othernode instanceof Region region) {
      layoutXProperty().bind(region.layoutXProperty().subtract(widthProperty()));
    }
  }

  public void attachXCenterTo(Node othernode) {
    if (othernode instanceof Region region) {
      layoutXProperty().bind(region.layoutXProperty().add(widthProperty().divide(2))
              .subtract(widthProperty().divide(2)));
    }
  }

  public void attachXRightInsideTo(Node othernode) {
    if (othernode instanceof Region region) {
      layoutXProperty().bind(region.layoutXProperty().add(widthProperty().subtract(widthProperty())));
    }
  }

  public void attachXRightOutsideTo(Node othernode) {
    if (othernode instanceof Region region) {
      layoutXProperty().bind(region.layoutXProperty().add(widthProperty()));
    }
  }




  public double getX() {
    return getLayoutX();
  }

  public double getY() {
    return getLayoutY();
  }

  public void shadow(double radius, double offsetX, double offsetY, Color color) {
    DropShadow shadow = new DropShadow();
    shadow.setRadius(radius);
    shadow.setOffsetX(offsetX);
    shadow.setOffsetY(offsetY);
    shadow.setColor(color);
    this.setEffect(shadow);
  }

  public void shadow() {
    DropShadow shadow = new DropShadow();
    shadow.setRadius(8);
    shadow.setOffsetX(3);
    shadow.setOffsetY(3);
    shadow.setColor(Color.rgb(0, 0, 0, 0.5));
    this.setEffect(shadow);
  }

  public void appendStyleForNode(String key, String value) {
    stylesMapForNode.put(key, value);
    updateNodeStyle();
  }

  public void removeStyleForNode(String key) {
    stylesMapForNode.remove(key);
    updateNodeStyle();
  }

  public void appendStyleForRegion(String key, String value) {
    stylesMapForRegion.put(key, value);
    updateRegionStyle();
  }

  public void removeStyleForRegion(String key) {
    stylesMapForRegion.remove(key);
    updateRegionStyle();
  }

  private void updateNodeStyle() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> e : stylesMapForNode.entrySet()) {
      sb.append(e.getKey()).append(": ").append(e.getValue()).append("; ");
    }
    node.setStyle(sb.toString().trim());
  }

  private void updateRegionStyle() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> e : stylesMapForRegion.entrySet()) {
      sb.append(e.getKey()).append(": ").append(e.getValue()).append("; ");
    }
    this.setStyle(sb.toString().trim());
  }

  public void cursor(Cursor cursor) {
    setCursor(cursor);
  }

  public void onContextMenu(Runnable runnable) {
    setOnContextMenuRequested(e -> runnable.run());
  }

  public void invisible() {
    setVisible(false);
  }

  public void visible() {
    setVisible(true);
  }

  public boolean getVisible() {
    return isVisible();
  }

  public void opacity(double opacity) {
    setOpacity(opacity);
  }
}