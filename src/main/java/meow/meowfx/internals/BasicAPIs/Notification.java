package meow.meowfx.internals.BasicAPIs;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Toolkit;

public class Notification {

  private Notification() {
    // No instantiation or you catch some beef
  }

  public static void showInfo(String title, String message, String iconPath) {
    try {
      TrayIcon trayIcon = setTrayIcon(iconPath);
      trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
    } catch (AWTException e) {
      Console.error("An Error occurred while setting up Notification: " + e.getMessage());
    }
  }

  public static void showWarning(String title, String message, String iconPath) {
    try {
      TrayIcon trayIcon = setTrayIcon(iconPath);
      trayIcon.displayMessage(title, message, TrayIcon.MessageType.WARNING);
    } catch (AWTException e) {
      Console.error("An Error occurred while setting up Notification: " + e.getMessage());
    }
  }

  public static void showError(String title, String message, String iconPath) {
    try {
      TrayIcon trayIcon = setTrayIcon(iconPath);
      trayIcon.displayMessage(title, message, TrayIcon.MessageType.ERROR);
    } catch (AWTException e) {
       Console.error("An Error occurred while setting up Notification: " + e.getMessage());
    }
  }

  public static void show(String title, String message, String iconPath) {
    try {
      TrayIcon trayIcon = setTrayIcon(iconPath);
      trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
    } catch (AWTException e) {
      Console.error("An Error occurred while setting up Notification: " + e.getMessage());
    }
  }

  private static TrayIcon setTrayIcon(String iconPath) throws AWTException {
    if (!SystemTray.isSupported()) {
      throw new UnsupportedOperationException("System notification are not supported on your OS");
    }
      SystemTray tray = SystemTray.getSystemTray();
      Image image = Toolkit.getDefaultToolkit().createImage(iconPath);
      TrayIcon trayIcon = new TrayIcon(image, "Java Notification");
      trayIcon.setImageAutoSize(true);
      trayIcon.setToolTip("Tooltip-text");
      tray.add(trayIcon);
      return trayIcon;
  }
}