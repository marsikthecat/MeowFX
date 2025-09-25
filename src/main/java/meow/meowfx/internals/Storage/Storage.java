package meow.meowfx.internals.Storage;

import java.util.HashMap;

public abstract class Storage {

  private static final HashMap<String, String> stringStringHashMap = new HashMap<>();

  Storage() {
    // No instantiation
  };

  public static void setItem(String key, String value) {
    stringStringHashMap.put(key, value);
  }

  public static String getItem(String key) {
    return stringStringHashMap.get(key);
  }

  public static void removeItem(String key) {
    stringStringHashMap.remove(key);
  }

  public static void clear() {
    stringStringHashMap.clear();
  }

  protected static HashMap<String, String> getMap() {
    return stringStringHashMap;
  }
}