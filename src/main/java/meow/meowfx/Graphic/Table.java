package meow.meowfx.graphic;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import meow.meowfx.internals.BaseText;
import meow.meowfx.internals.Element;

public class Table extends Element<GridPane> {

  private final int rows;
  private final int columns;

  public Table(int zeilen, int spalten) {
    super(new GridPane());
    this.rows = zeilen;
    this.columns = spalten;

    for (int i = 0; i < spalten; i++) {
      ColumnConstraints cc = new ColumnConstraints();
      cc.setPercentWidth(100.0 / spalten);
      getNode().getColumnConstraints().add(cc);
    }

    for (int i = 0; i < zeilen; i++) {
      RowConstraints rc = new RowConstraints();
      rc.setPercentHeight(100.0 / zeilen);
      getNode().getRowConstraints().add(rc);
    }
    getNode().setGridLinesVisible(true);
  }

  public void put(Node node, int spalte, int zeile) {
    getNode().add(node, spalte, zeile);
  }

  public void fillRow(int rowNum, Node... nodes) {
    if (nodes.length > columns) {
      throw new IllegalArgumentException("Too much elements for number of columns");
    }
    for (int i = 0; i < nodes.length; i++) {
      if (nodes[i] instanceof BaseText) {
        ((BaseText) nodes[i]).padding(5, 10, 5, 10);
      }
      getNode().add(nodes[i], i, rowNum);
    }
  }

  public void fillColumn(int columNum, Node... nodes) {
    if (nodes.length > rows) {
      throw new IllegalArgumentException("Too much elements for number of rows");
    }
    for (int i = 0; i < nodes.length; i++) {
      if (nodes[i] instanceof BaseText) {
        ((BaseText) nodes[i]).padding(5, 10, 5, 10);
      }
      getNode().add(nodes[i], columNum, i);
    }
  }

  public void size(int width, int height) {
    getNode().setPrefSize(width, height);
  }
}