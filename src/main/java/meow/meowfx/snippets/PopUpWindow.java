package meow.meowfx.snippets;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import meow.meowfx.forms.Button;
import meow.meowfx.text.H3;
import meow.meowfx.text.P;
import meow.meowfx.internals.Element;

public class PopUpWindow extends Element<VBox> {

  private final H3 headLine;
  private final P text;
  private final boolean withBtn;
  private final Button yesBtn;
  private final Button noBtn;

  public PopUpWindow(String title, String content, String yesOption, String noOption) {
    super(new VBox());
    withBtn = !yesOption.isEmpty() && !noOption.isEmpty();
    Icon icon = new Icon(IconTypes.CROSS);
    icon.size(16);
    icon.cursor(Cursor.HAND);
    icon.color(Color.WHITE);
    icon.onclick(this::invisible);
    invisible();
    HBox titleBox = new HBox();
    titleBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    titleBox.setPadding(new Insets(10, 20, 10, 20));
    headLine = new H3(title);
    Region spacerLeft = new Region();
    Region spacerRight = new Region();
    HBox.setHgrow(spacerLeft, Priority.ALWAYS);
    HBox.setHgrow(spacerRight, Priority.ALWAYS);
    titleBox.getChildren().addAll(spacerLeft, headLine, spacerRight, icon);

    HBox contentBox = new HBox();
    contentBox.setPadding(new Insets(10, 20, 10, 20));
    contentBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    text = new P(content);
    contentBox.getChildren().add(text);

    if (withBtn) {
      HBox footer = new HBox();
      yesBtn = new Button(yesOption);
      noBtn = new Button(noOption);
      footer.getChildren().addAll(yesBtn, noBtn);
      footer.setPadding(new Insets(10, 20, 10, 20));
      footer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
      getNode().getChildren().addAll(titleBox, contentBox, footer);
    } else {
      yesBtn = null;
      noBtn = null;
      getNode().getChildren().addAll(titleBox, contentBox);
    }
  }

  public void show() {
    visible();
  }

  public void hide() {
    invisible();
  }

  public void message(String message) {
    text.text(message);
  }

  public void headLine(String headLineTxt) {
    headLine.text(headLineTxt);
  }

  public void onClickedYes(Runnable runnable) {
    if (!withBtn) {
      throw new UnsupportedOperationException("There are no buttons configured in this Modal!");
    }
    yesBtn.onclick(runnable);
  }

  public void onClickedNo(Runnable runnable) {
    if (!withBtn) {
      throw new UnsupportedOperationException("There are no buttons configured in this Modal!");
    }
    noBtn.onclick(runnable);
  }


  public void headLineColor(Color color) {
    headLine.fontColor(color);
  }

  public void headerBackgroundColor(Color color) {
    HBox headerBox = (HBox) getNode().getChildren().get(0);
    headerBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public void textColor(Color color) {
    text.fontColor(color);
  }

  public void textBackgroundColor(Color color) {
    HBox textBox = (HBox) getNode().getChildren().get(1);
    textBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public void footerBackgroundColor(Color color) {
    if (!withBtn) {
      throw new UnsupportedOperationException("There are no buttons configured in this Modal! Therefore no Footer to color");
    }
    HBox footer = (HBox) getNode().getChildren().get(2);
    footer.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public void padding(double top, double right, double bottom, double left) {
   getNode().setPadding(new Insets(top, right, bottom, left));
  }

  //TODO: More!!!
}