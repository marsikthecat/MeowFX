package meow.meowfx.text;

import javafx.scene.paint.Color;
import meow.meowfx.internals.BasicAPIs.Console;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class A extends P {

  public A(String string) {
    super(string);
    hover(Color.PURPLE);
  }

  public void href(String link) {
    setOnMouseClicked(e -> {
      try {
        Desktop.getDesktop().browse(new URI(link));
      } catch (IOException | URISyntaxException ex) {
        Console.error(ex.getMessage());
      }
    });
  }

  public void hover(Color color) {
    super.hover(color);
  }

  public void backgroundColorOnHover(String color) {
    super.hover("-fx-background-Color", color);
  }
}