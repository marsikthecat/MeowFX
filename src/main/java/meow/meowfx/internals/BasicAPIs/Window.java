package meow.meowfx.internals.BasicAPIs;

import javafx.scene.Scene;
import javafx.stage.Stage;
import meow.meowfx.structure.Body;
import java.util.HashMap;

public class Window {

  private static final HashMap<Integer, Stage> windowsLookup = new HashMap<>();
  private Window() {
    // No instantiation
  }

  public static void open(int id, String title, Body body, int width, int height) {
    Stage stage = new Stage();
    stage.setTitle(title);
    Scene scene = new Scene(body, width, height);
    stage.setScene(scene);
    windowsLookup.put(id, stage);
    stage.setOnCloseRequest(e -> windowsLookup.remove(id));
    stage.show();
  }

  public static void close(int id) {
    Stage stage = windowsLookup.get(id);
    if (stage != null) {
      stage.close();
      windowsLookup.remove(id);
    } else {
      Console.error("Window with ID " + id + " does not exist.");
    }
  }
}