package meow.meowfx.media;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import meow.meowfx.internals.Element;

public class Video extends Element<MediaView> implements BaseMedia {

  private final MediaPlayer mediaPlayer;
  public Video(String path) {
    super(new MediaView());
    Media media = new Media(new File(path).toURI().toString());
    this.mediaPlayer = new MediaPlayer(media);
    getNode().setMediaPlayer(mediaPlayer);
    getNode().setPreserveRatio(true);
  }

  public void width(int width) {
    getNode().setFitWidth(width);
  }

  public void height(int height) {
    getNode().setFitHeight(height);
  }

  @Override
  public MediaPlayer getMediaPlayer() {
    return mediaPlayer;
  }
  public MediaView getMediaView() {
    return getNode();
  }
}