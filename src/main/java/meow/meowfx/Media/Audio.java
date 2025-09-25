package meow.meowfx.media;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Audio implements BaseMedia {

  private final MediaPlayer mediaPlayer;

  public Audio(String path) {
    Media media = new Media(new File(path).toURI().toString());
    this.mediaPlayer = new MediaPlayer(media);
  }

  @Override
  public MediaPlayer getMediaPlayer() {
    return mediaPlayer;
  }
}