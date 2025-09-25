package meow.meowfx.structure;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class MeowHTML extends Stage {
  public MeowHTML(Head head, Body body) {
    body.loadLocalStorage();
    int[] windowData = body.getWindowData();
    Scene scene = new Scene(body, windowData[0], windowData[1]);
    setScene(scene);
    setResizable(body.isResizable());
    if (body.isResizable()) {
      setMaxWidth(windowData[2]);
      setMaxHeight(windowData[3]);
      setMinWidth(windowData[4]);
      setMinHeight(windowData[5]);
    }
    setTitle(head.getTitle());
    getIcons().add(new Image(Objects.requireNonNull(
            getClass().getResourceAsStream(head.getIconPath()))));
    setOnCloseRequest(e -> body.saveLocalStorage());
    show();
  }
}