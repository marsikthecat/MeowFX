package meow.meowfx.internals.BasicAPIs;

public class Alert {

  public Alert(String title, String message, javafx.scene.control.Alert.AlertType alertType) {
    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText("");
    alert.setContentText(message);
    alert.showAndWait();
  }
}