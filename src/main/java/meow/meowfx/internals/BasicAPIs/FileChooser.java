package meow.meowfx.internals.BasicAPIs;

import javafx.stage.Stage;
import java.io.File;
import java.util.List;

public class FileChooser {
  private final javafx.stage.FileChooser fileChooser;

  public FileChooser() {
    fileChooser = new javafx.stage.FileChooser();
  }

  public File openFile(String title) {
    fileChooser.setTitle(title);
    return fileChooser.showOpenDialog(new Stage());
  }

  public List<File> openMultiplyFiles(String title) {
    fileChooser.setTitle(title);
    return fileChooser.showOpenMultipleDialog(new Stage());
  }

  public void suggestFileNameToSave(String fileName) {
    fileChooser.setInitialFileName(fileName);
  }

  public File saveFile(String title) {
    fileChooser.setTitle(title);
    return fileChooser.showSaveDialog(new Stage());
  }
}