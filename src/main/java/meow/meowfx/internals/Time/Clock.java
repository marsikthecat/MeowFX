package meow.meowfx.internals.Time;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

public class Clock {

  private final Timeline timeline;
  private KeyFrame keyFrame;
  private Runnable action;

  public Clock() {
    timeline = new Timeline();
  }

  public void doEverySeconds(int seconds, Runnable runnable) {
    action = runnable;
    keyFrame = new KeyFrame(Duration.seconds(seconds), e -> action.run());
    timeline.getKeyFrames().setAll(keyFrame);
    timeline.setCycleCount(Timeline.INDEFINITE);
  }

  public void doEveryMillis(int millis, Runnable runnable) {
    action = runnable;
    keyFrame = new KeyFrame(Duration.millis(millis), e -> action.run());
    timeline.getKeyFrames().setAll(keyFrame);
    timeline.setCycleCount(Timeline.INDEFINITE);
  }

  public void doEveryMinutes(int minutes, Runnable runnable) {
    action = runnable;
    timeline.getKeyFrames().setAll(new KeyFrame(Duration.minutes(minutes), e -> action.run()));
    timeline.setCycleCount(Timeline.INDEFINITE);
  }

  public void newRunnable(Runnable runnable) {
    if (action == null) {
      throw new IllegalStateException("You must set a runnable first before replacing it");
    }
    Platform.runLater(() -> {
      timeline.stop();
      action = runnable;
      KeyFrame newKeyFrame = new KeyFrame(keyFrame.getTime(), e -> action.run());
      timeline.getKeyFrames().setAll(newKeyFrame);
      timeline.setCycleCount(Timeline.INDEFINITE);
    });
  }

  public void newSeconds(int seconds) {
    Platform.runLater(() -> {
      timeline.stop();
      KeyFrame newKeyFrame = new KeyFrame(Duration.seconds(seconds), e -> action.run());
      timeline.getKeyFrames().setAll(newKeyFrame);
      timeline.setCycleCount(Timeline.INDEFINITE);
    });
  }

  public void newMillis(int millis) {
    Platform.runLater(() -> {
      timeline.stop();
      KeyFrame newKeyFrame = new KeyFrame(Duration.millis(millis), e -> action.run());
      timeline.getKeyFrames().setAll(newKeyFrame);
      timeline.setCycleCount(Timeline.INDEFINITE);
    });
  }

  public void newMinutes(int minutes) {
    Platform.runLater(() -> {
      timeline.stop();
      KeyFrame newKeyFrame = new KeyFrame(Duration.minutes(minutes), e -> action.run());
      timeline.getKeyFrames().setAll(newKeyFrame);
      timeline.setCycleCount(Timeline.INDEFINITE);
    });
  }

  public void start() {
    timeline.play();
  }

  public void pause() {
    timeline.pause();
  }

  public void stop() {
    timeline.stop();
  }
}