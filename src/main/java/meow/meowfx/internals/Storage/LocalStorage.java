package meow.meowfx.internals.Storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import meow.meowfx.internals.BasicAPIs.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class LocalStorage extends Storage {

  private LocalStorage() {
    // No instantiation
  }

  public static void toJson() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try (FileWriter writer = new FileWriter("data.json")) {
      gson.toJson(getMap(), writer);
    } catch (IOException e) {
      Console.error(e.getMessage());
    }
  }

  public static void fromJson() {
    Gson gson = new Gson();
    Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
    try (FileReader reader = new FileReader("data.json")) {
      Map<String, Object> data = gson.fromJson(reader, mapType);
      for (Map.Entry<String, Object> entry : data.entrySet()) {
        setItem(entry.getKey(), (String) entry.getValue());
      }
    } catch (IOException e) {
      Console.error(e.getMessage());
    }
  }
}