package meow.meowfx.graphic;

import javafx.scene.effect.*;
import javafx.scene.media.MediaView;
import meow.meowfx.media.Video;

public class Filter {

  private final ColorAdjust filters = new ColorAdjust();
  private final MediaView mediaView;

  public Filter(Video video) {
    this.mediaView = video.getMediaView();
    mediaView.setEffect(filters);
  }

  public void greyScale() {
    filters.setSaturation(-1);
  }

  public void sepia() {
    SepiaTone sepiaTone = new SepiaTone(1);
    sepiaTone.setInput(filters);
    mediaView.setEffect(sepiaTone);
  }

  public void brightness(double brightness) {
    filters.setBrightness(checkAndNormalize("Brightness", brightness));
  }

  public void saturation(double saturation) {
    filters.setSaturation(checkAndNormalize("Saturation", saturation));
  }

  public void contrast(double contrast) {
    filters.setContrast(checkAndNormalize("Contrast", contrast));
  }

  public void hue(double hue) {
    filters.setHue(checkAndNormalize("Hue", hue));
  }

  public void glow(double glow) {
    if (glow < 0 || glow > 10) {
      throw new IllegalArgumentException("My friend: " + glow + " between 0 and 10. Look at the javaDoc, bro!");
    }
    Glow glowFilter = new Glow();
    glowFilter.setInput(filters);
    glowFilter.setLevel(glow * 0.1);
  }

  public void blur(double blur) {
    if (blur < 0 || blur > 10) {
      throw new IllegalArgumentException("My friend: " + blur + " between 0 and 10. Look at the javaDoc, bro!");
    }
    GaussianBlur gaussianBlur = new GaussianBlur();
    gaussianBlur.setInput(filters);
    gaussianBlur.setRadius(blur * 6.3);
  }

  public double checkAndNormalize(String keyWord, double val) {
    if (val < -10 || val > 10) {
      throw new IllegalArgumentException("My friend: " + keyWord + " between -10 and 10. Look at the javaDoc, bro!");
    }
    return val * 0.1;
  }
}