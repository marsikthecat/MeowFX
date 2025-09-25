package meow.meowfx.media;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public interface BaseMedia {

  MediaPlayer getMediaPlayer();
  default void play() {
    getMediaPlayer().play();
  }
  default boolean isPlaying() {
    return getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING;
  }
  default void pause() {
    getMediaPlayer().pause();
  }
  default boolean isPaused() {
    return getMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED;
  }
  default void autoPlay(boolean autoPlay) {
        getMediaPlayer().setAutoPlay(autoPlay);
    }
  default double currentTime() {
        return getMediaPlayer().getCurrentTime().toSeconds();
    }
  default void volume(double volume) {
    if (volume < 0 || volume > 1) {
      throw new IllegalArgumentException("Volume must be between 0 and 1");
    }
    getMediaPlayer().setVolume(volume);
  }
  default void mute() {
        getMediaPlayer().setMute(true);
    }
  default void unmute() {
        getMediaPlayer().setMute(false);
    }
  default boolean isMuted() {
    return getMediaPlayer().isMute();
  }
  default double duration() {
        return getMediaPlayer().getTotalDuration().toSeconds();
    }
  default void time(double time) {
    getMediaPlayer().seek(Duration.seconds(time));
  }
  default void restart() {
    getMediaPlayer().seek(Duration.ZERO);
    getMediaPlayer().play();
  }
  default void goTo(double time) {
        getMediaPlayer().seek(Duration.seconds(time));
    }
  default void forward(double seconds) {
    double newTime = currentTime() + seconds;
    goTo(Math.min(newTime, getMediaPlayer().getTotalDuration().toSeconds()));
  }
  default void loop() {
    getMediaPlayer().setOnEndOfMedia(this::restart);
  }
  default void rewind(double seconds) {
    double newTime = currentTime() - seconds;
    goTo(Math.max(newTime, 0));
  }
  default void speed(double speed) {
    if (speed <= 0) {
      throw new IllegalArgumentException("Speed must be greater than 0");
    }
    getMediaPlayer().setRate(speed);
  }
  default double getSpeed() {
    return getMediaPlayer().getRate();
  }
  default void doubleSpeed() {
    getMediaPlayer().setRate(2);
  }
  default void halfSpeed() {
    getMediaPlayer().setRate(0.5);
  }
  default void onEnded(Runnable runnable) {
    getMediaPlayer().setOnEndOfMedia(runnable);
  }
}