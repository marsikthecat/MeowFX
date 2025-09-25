package meow.meowfx.structure;

import java.util.ArrayList;
import java.util.List;

public class Head {
  private String title;
  private String description;
  private String author;
  private final List<String> keyWords;
  private String iconPath = "/defaultIcon.png";

  public Head() {
    title = "Unknown Dokument";
    description = "";
    author = "";
    keyWords = new ArrayList<>();
  }

  public String getDescription() {
    return description;
  }

  public String getAuthor() {
    return author;
  }

  public List<String> getKeyWords() {
    return keyWords;
  }

  public String getTitle() {
    return title;
  }

  public String getIconPath() {
    return iconPath;
  }

  public void title(String title) {
    this.title = title;
  }

  public void author(String author) {
    this.author = author;
  }

  public void description(String description) {
    this.description = description;
  }

  public void keyWords(String... keyWords) {
    this.keyWords.addAll(List.of(keyWords));
  }

  public void icon(String iconPath) {
    this.iconPath = iconPath;
  }
}