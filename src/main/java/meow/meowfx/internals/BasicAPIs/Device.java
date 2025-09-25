package meow.meowfx.internals.BasicAPIs;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PowerSource;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Device {

  private static final SystemInfo si = new SystemInfo();
  private static final HardwareAbstractionLayer hal = si.getHardware();

  private Device() {
    // No instantiation, or you catch a fade.
  }

  public static String os() {
    return System.getProperty("os.name").toLowerCase();
  }

  public static String osVersion() {
    return System.getProperty("os.version").toLowerCase();
  }

  public static String userName() {
    return System.getProperty("user.name").toLowerCase();
  }

  public static String numberOfCores() {
    return String.valueOf(hal.getProcessor().getPhysicalProcessorCount());
  }

  public static long totalRamInGB() {
    return hal.getMemory().getTotal() / 1_000_000_000;
  }

  public static long availableRamInGB() {
    return hal.getMemory().getAvailable() / 1_000_000_000;
  }

  public static String getCpuTemperature() {
    return hal.getSensors().getCpuTemperature() + " °C";
  }

  public static String getCpuVoltage() {
    return hal.getSensors().getCpuVoltage() + " V";
  }

  public static String[] getPowerSourcesInfo() {
    String[] powerSources = new String[hal.getPowerSources().size()];
    List<PowerSource> powerSourceList = hal.getPowerSources();
    for (int i = 0; i < powerSourceList.size(); i++) {
      powerSources[i] = "Battery: " + powerSourceList.get(i).getName()
              + ", Remaining: " + (powerSourceList.get(i).getCurrentCapacity() * 100)
              + "%";
    }
    return powerSources;
  }

  public static String[] getDisksInfo() {
    String[] disksInfos = new String[hal.getDiskStores().size()];
    List<HWDiskStore> disks = hal.getDiskStores();
    for (int i = 0; i < disks.size(); i++) {
      disksInfos[i] = "Model: " + disks.get(i).getName()
              + ", Size: " + (disks.get(i).getSize() / 1_000_000_000 + " GB");
    }
    return disksInfos;
  }

  public static boolean isOnline() {
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL("https://www.google.com").openConnection();
      connection.setConnectTimeout(2000);
      connection.connect();
      return connection.getResponseCode() == 200;
    } catch (Exception e) {
      return false;
    }
  }
}