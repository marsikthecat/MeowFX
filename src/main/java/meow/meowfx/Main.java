package meow.meowfx;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import meow.meowfx.forms.*;
import meow.meowfx.graphic.Img;
import meow.meowfx.graphic.Table;
import meow.meowfx.media.Video;
import meow.meowfx.snippets.*;
import meow.meowfx.text.*;
import meow.meowfx.internals.BasicAPIs.Alert;
import meow.meowfx.internals.BasicAPIs.ClipBoard;
import meow.meowfx.internals.BasicAPIs.Console;
import meow.meowfx.internals.Http.HttpManager;
import meow.meowfx.structure.Body;
import meow.meowfx.structure.Head;
import meow.meowfx.structure.MeowHTML;

public class Main extends Application {

  /**
   * forms: 513 lines.
   * graphic: 187 lines.
   * internals: 501 + 483 + 333 + 123 + 109 + 75 + 248 + 142 + 242 = 2256 lines.
   * media: 139 lines.
   * snippets: 577 lines.
   * structure: 230 lines.
   * text: 121 lines.
   * Total: 4023 lines.

   * Below you can see a demo, bro.
   */
  @Override
  public void start(Stage stage) {

    H1 headLine = new H1("Hey Welcome");

    A link1 = new A("google");
    link1.fontColor(Color.DARKBLUE);
    link1.backgroundColorOnHover("lightgray");
    link1.href("https://www.google.com");
    link1.padding(5, 10, 5, 10);
    A link2 = new A("youtube");
    link2.fontColor(Color.DARKBLUE);
    link2.backgroundColorOnHover("lightgray");
    link2.padding(5, 10, 5, 10);
    A link3 = new A("chatGpt");
    link3.fontColor(Color.DARKBLUE);
    link3.backgroundColorOnHover("lightgray");
    link3.padding(5, 10, 5, 10);

    NavBar navBar = new NavBar();
    navBar.addMenuItems(link1, link2, link3);
    navBar.backgroundColor("grey");
    navBar.pos(0, 50);
    navBar.padding(5, 10,5, 10);

    Menu menu = new Menu();
    menu.addElements(new H3("Hey, how are you"), new H3("Option2"), new H3("Option3"));
    menu.width(150);
    menu.backgroundColor("lightgrey");
    menu.elementSpacing(10);
    menu.padding(5, 0, 5, 10);

    IconButton iconButton = menu.getMenuButton();
    iconButton.pos(10, 10);

    Img img = new Img("C:\\Marsik\\marsik.info\\Bilder\\obenmiau.png");
    img.width(100);
    img.shadow();
    img.pos(0, 150);

    Table table = new Table(5,4);
    table.backgroundColor("pink");
    table.fillRow(0,new H5(""), new H5("Monday"), new H5("TuesDay"), new H5("Wednesday"));
    table.fillRow(1,new H5("8:00"), new P("catching Mouses"), new P("catching Mouses"), new P("eating"));
    table.fillRow(2,new H5("10:00"), new P("working"), new P("working"), new P("studying"));
    table.fillRow(3,new H5("15:00"), new P("working"), new P("going home"), new P("eating"));
    table.fillRow(4,new H5("20:00"), new P("cleaning ass"), new P("sleeping"), new P("catching Mouses"));
    table.pos(10, 100);

    Video video = new Video("C:\\Marsik\\marsik.info\\Bilder\\20190218_180121.mp4");
    video.autoPlay(false);
    video.width(200);
    video.setY(260);
    video.shadow(12,8,8, Color.AQUAMARINE);
    video.cursor(Cursor.OPEN_HAND);
    video.loop();

    SnackBar snackBar = new SnackBar();
    snackBar.message("Video is playing");
    snackBar.animationDuration(0.5);
    snackBar.popUpDuration(3);
    snackBar.startPosition(470);
    snackBar.endPosition(380);
    snackBar.backgroundColor("black");
    snackBar.textColor(Color.WHITE);
    snackBar.padding(10, 20, 10, 20);

    PopUpWindow popUpWindow = new PopUpWindow("Information", "You have just clicked the snackbar \n" +
            "Which button do you want to click?", "Yes", "No");
    popUpWindow.onClickedYes(() -> {
      Console.log("Yes clicked");
      popUpWindow.hide();
    });
    popUpWindow.onClickedNo(() -> {
      Console.log("No clicked");
      popUpWindow.hide();
    });
    popUpWindow.headerBackgroundColor(Color.RED);
    popUpWindow.headLineColor(Color.WHITE);
    popUpWindow.textColor(Color.BLACK);
    popUpWindow.textBackgroundColor(Color.WHITE);
    popUpWindow.shadow();

    snackBar.onclick(popUpWindow::show);

    IconButton button = new IconButton(IconTypes.PLAY);
    button.onclick(() -> {
      if (video.isPlaying()) {
         video.pause();
         button.setIcon(IconTypes.PLAY);
      } else {
        snackBar.action();
        video.play();
        button.setIcon(IconTypes.PAUSE);
      }
    });
    button.attachXLeftOutsideTo(video);
    button.attachYCenter(video);

    Input input = new Input();
    input.promptText("type something in");
    input.onclick(() -> new Alert("Hey", input.getValue(), javafx.scene.control.Alert.AlertType.CONFIRMATION));

    LabeledInput labeledInput = new LabeledInput(new P("Your thoughts: "), input);
    labeledInput.pos(10, 410);

    SearchBar searchBar = new SearchBar();
    searchBar.onclick(() -> ClipBoard.copyToClickBoard(searchBar.getSearchText()));

    Body body = new Body();
    body.addAll(headLine, navBar, img, table, video, button, labeledInput, searchBar, menu, iconButton, snackBar
    ,popUpWindow);

    body.setResponsivePosition(headLine, 0.5);
    body.setResponsiveWidth(navBar, 1);
    body.setResponsivePosition(table, 0.5);
    body.setResponsivePosition(video, 0.5);
    body.setResponsiveHeight(menu, 1);
    body.setResponsivePosition(snackBar, 0.5);
    body.setResponsivePosition(popUpWindow, 0.5, 0.5);

    body.setBelowWindowWidth(400, () -> video.width(200));
    body.setAboveWindowWidth(400, () -> video.width(250));
    body.setBelowWindowWidth(500, img::invisible);
    body.setAboveWindowWidth(500, img::visible);
    body.setAboveWindowWidth(450, () -> searchBar.pos(230, 410));
    body.setBelowWindowWidth(450, () -> searchBar.pos(10, 440));

    body.windowWidth(400);
    body.windowHeight(300);
    body.resizable(true);
    body.maxWindowWidth(700);
    body.maxWindowHeight(500);
    body.minWindowWidth(350);
    body.minWindowHeight(200);

    String s = HttpManager.sendRpcRequest("http://www.raboof.com/projects/jayrock/demo.ashx",
            """
                    {
                       "jsonrpc": "2.0",
                       "method": "echo",
                       "params": ["Hallo Welt"],
                        "id": 1
                    }
                    """);
    Console.log(s);
    Head head = new Head();
    head.title("Hey");
    new MeowHTML(head, body).show();
  }
}