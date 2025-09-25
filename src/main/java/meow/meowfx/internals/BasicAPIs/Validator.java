package meow.meowfx.internals.BasicAPIs;

import java.util.HashSet;
import java.util.Set;
import static meow.meowfx.internals.BasicAPIs.Console.log;

public class Validator {

  private int minSize = 0;
  private int maxSize = Integer.MAX_VALUE;
  private final Set<Character> forbiddenSymbols = new HashSet<>();
  private static final Set<Character> SYMBOLS = Set.of(
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
            '-', '_', '=', '+', '[', ']', '{', '}', ';', ':',
            '\'', ',', '.', '<', '>', '?', '/', '|'
  );
  private boolean whiteSpaceForbidden = false;
  private int minLetters = 0;
  private int minDigits = 0;
  private int minSymbols = 0;
  private int minLowerCase = 0;
  private int minUpperCase = 0;

  public void requireLength(int minSize, int maxSize) {
    if (minSize > maxSize) {
      throw new IllegalArgumentException("Min-size cannot be bigger than maxsize, bro!");
    }
    if (minSize < 0) {
      throw new IllegalArgumentException("Sizes must be positive, bro!");
    }
    this.minSize = minSize;
    this.maxSize = maxSize;
  }

  public void forbiddenSymbols(char... chars) {
    for (char c : chars) {
      forbiddenSymbols.add(c);
    }
  }

  public void forbidWhiteSpace(boolean bool) {
    this.whiteSpaceForbidden = bool;
  }

  public void atLeastLetters(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Number must be positive or Zero");
    }
    this.minLetters = n;
  }

  public void atLeastDigits(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Number must be positive or Zero");
    }
    this.minDigits = n;
  }

  public void atLeastSymbols(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Number must be positive or Zero");
    }
    this.minSymbols = n;
  }

  public void atLeastLowerCase(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Number must be positive or Zero");
    }
    this.minLowerCase = n;
  }

  public void atLeastUpperCase(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Number must be positive or Zero");
    }
    this.minUpperCase = n;
  }

  public static boolean isValidEmail(String email) {
    return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
  }

  public static boolean isValidPhoneNumber(String phoneNumber) {
    return phoneNumber.matches("\\+?(\\d{1,3})?[\\s.-]?\\(?\\d{1,4}\\)?[\\s.-]?\\d{1,4}[\\s.-]?\\d{1,9}([\\s.-]?\\d{1,9})?");
  }

  public static boolean isValidURL(String url) {
    return url.matches("\\b[a-zA-Z][a-zA-Z0-9+\\-.]*://[^\\s/?#]+(:\\d+)?(/[^\\s?#]*)?(\\?[^\\s#]*)?(#\\S*)?");
  }

  public static boolean isValidIPAddress(String ip) {
    return ip.matches("\\b((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d))" +
            "{3})\\b|((([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4}|:))|(([0-9a-fA-F]{1,4}:){1,7}:)|(([0-9a-fA-F]{1,4}:)" +
            "{1,6}:[0-9a-fA-F]{1,4})|(([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2})|(([0-9a-fA-F]{1,4}:){1,4}" +
            "(:[0-9a-fA-F]{1,4}){1,3})|(([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4})|(([0-9a-fA-F]{1,4}:){1,2}" +
            "(:[0-9a-fA-F]{1,4}){1,5})|(([0-9a-fA-F]{1,4}:)(:[0-9a-fA-F]{1,4}){1,6})|(:((:[0-9a-fA-F]{1,4}){1,7}|:)))(%.+)?\\b");
  }

  public static boolean isValidMacAddress(String macAddress) {
    return macAddress.matches("\\b([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})\\b");
  }

  public static boolean isValidISO860Date(String date) {
    return date.matches("\\b\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])\\b");
  }


  public boolean validate(String input) {
    if (input.length() < minSize || input.length() > maxSize) {
      log("Length of String doesn't fit: " + input.length() +
              ". Required: " + "At least " + minSize + " at most " + maxSize);
      return false;
    }

    int numberOfDigits = 0;
    int numberOfLetters = 0;
    int numberOfSymbols = 0;
    int numberOfLowerCaseLetters = 0;
    int numberOfUpperCaseLetters = 0;

    for (char c : input.toCharArray()) {
      if (Character.isDigit(c)) {
        numberOfDigits++;
      }
      if (Character.isLetter(c)) {
        numberOfLetters++;
      }
      if (SYMBOLS.contains(c)) {
        numberOfSymbols++;
      }
      if (Character.isLowerCase(c)) {
        numberOfLowerCaseLetters++;
      }
      if (Character.isUpperCase(c)) {
        numberOfUpperCaseLetters++;
      }
      if (whiteSpaceForbidden && Character.isWhitespace(c)) {
        log("Whitespace forbidden but found: '" + c + "'");
        return false;
      }
      if (forbiddenSymbols.contains(c)) {
        log("Forbidden symbol detected: '" + c + "'");
        return false;
      }
    }

    if (numberOfDigits < minDigits) {
      log("At least " + minDigits + " Digits required!");
      return false;
    }
    if (numberOfLetters < minLetters) {
      log("At least " + minLetters + " Letters required!");
      return false;
    }
    if (numberOfSymbols < minSymbols) {
      log("At least " + minSymbols + " Symbols required!");
      return false;
    }
    if (numberOfLowerCaseLetters < minLowerCase) {
      log("At least " + minLowerCase + " Lowercase letters required!");
      return false;
    }
    if (numberOfUpperCaseLetters < minUpperCase) {
      log("At least " + minUpperCase + " Uppercase letters required!");
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Validator validator = new Validator();
    validator.forbiddenSymbols('<', '>');
    validator.atLeastDigits(3);
    validator.atLeastLetters(5);
    validator.atLeastSymbols(1);
    validator.requireLength(5, 20);
    validator.forbidWhiteSpace(true);
    log(String.valueOf(validator.validate("Password"))); // false (at least 3 digits, only 0)
    log(String.valueOf(validator.validate("t1234k567"))); // false (at least 5 letters, only 2)
    log(String.valueOf(validator.validate("t1234password567"))); // false (at least 1 symbol, only 0)
    log(String.valueOf(validator.validate("Pass"))); // false (length too small)
    log(String.valueOf(validator.validate("Pass word"))); // false (whitespace)
    log(String.valueOf(validator.validate("Pass>word"))); // false (forbidden symbol >)
    log(String.valueOf(validator.validate("Password123!"))); // true
    validator.atLeastLowerCase(5);
    validator.atLeastUpperCase(2);
    log(String.valueOf(validator.validate("abc45,5aa"))); // false (at least 2 uppercase, only 0)
    log(String.valueOf(validator.validate("ABC45,5dead"))); // false (at least 5 lowerCase, only 4)
    log(String.valueOf(validator.validate("ABC45!5banana"))); // true
  }
}