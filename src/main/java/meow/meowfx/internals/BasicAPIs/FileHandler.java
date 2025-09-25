package meow.meowfx.internals.BasicAPIs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

  private FileHandler() {
    // No instantiation or your toilet will be clogged.
  }

  public static void createNewFile(String pathWithFileName) {
    try {
      File file = new File(pathWithFileName);
      if (file.createNewFile()) {
        Console.log("File successfully created: " +  file.getName());
      } else {
        Console.log("File already exists: " + file.getName());
      }
    } catch (IOException e) {
      Console.error("An error occurred while creating file " + pathWithFileName + " : "
      + e.getMessage());
    }
  }

  public static void writeToFile(String pathWithFileName, String content) {
    try {
      FileWriter myWriter = new FileWriter(pathWithFileName);
      myWriter.write(content);
      myWriter.close();
      Console.log("Successfully wrote to file: " + pathWithFileName);
    } catch (IOException e) {
      Console.error("An error occurred while writing to file " + pathWithFileName + " : "
      + e.getMessage());
    }
  }

  public static List<String> readFile(String pathWithFileName) {
    List<String> lines = new ArrayList<>();
    try {
      File myObj = new File(pathWithFileName);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        lines.add(myReader.nextLine());
      }
      myReader.close();
      Console.log("Successfully read file: " + pathWithFileName);
    } catch (FileNotFoundException e) {
      Console.error("An error occurred while reading file " + pathWithFileName + " : "
                + e.getMessage());
    }
    return lines;
  }

  public static void deleteFile(String pathWithFileName) {
    File myObj = new File(pathWithFileName);
    if (myObj.delete()) {
      Console.log("Successfully deleted the file: " + myObj.getName());
    } else {
      Console.error("Not able to delete File: " + myObj.getName());
    }
  }
}