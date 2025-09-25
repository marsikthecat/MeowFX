package meow.meowfx.forms;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import meow.meowfx.internals.Element;

public class Select extends Element<ComboBox<String>> {

  private ObservableList<String> observableList;
  public Select() {
    super(new ComboBox<>());
  }

  public void items(String... strings) {
    observableList = FXCollections.observableList(List.of(strings));
    getNode().setItems(observableList);
  }

  public void defaultItem(String defaultItem) {
    for (String item : observableList) {
      if (item.equals(defaultItem)) {
        getNode().setValue(item);
        break;
      }
    }
  }

  public void allowOnlyDefinedItems() {
    getNode().setEditable(false);
  }

  public void allowCustomInput() {
    getNode().setEditable(true);
  }

  public String getSelectedItem() {
    return getNode().getSelectionModel().getSelectedItem();
  }

  public List<String> getItems() {
    return getNode().getItems();
  }

  public void showDropDownList() {
    getNode().show();
  }

  public void hideDropDownList() {
    getNode().hide();
  }
}