package meow.meowfx.internals.DataStructures;

public class StringTanga {

  private char[] data;
  private int size;
  public StringTanga(String string) {
    if (string == null) {
      throw new IllegalArgumentException("String cannot be null, my friend!");
    }
    this.data = string.toCharArray();
    this.size = data.length;
  }

  private StringTanga(char[] data) {
    if (data == null) {
      throw new IllegalArgumentException("Character array cannot be null, my friend!");
    }
    this.data = data.clone();
  }

  public void setCharAt(int index, char c) {
    if (index < 0 || index >= data.length) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index + " my friend!");
    }
    data[index] = c;
  }

  public char getCharAt(int index) {
    if (index < 0 || index >= data.length) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index + " my friend!");
    }
    return data[index];
  }

  public int length() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void print() {
    System.out.print(java.util.Arrays.toString(data));
  }

  public int indexOf(char c) {
    int index = -1;
    for (int i = 0; i < data.length; i++) {
      if (data[i] == c) {
        index = i;
        break;
      }
    }
    return index;
  }

  public int[] allIndexOf(char c) {
    int counter = 0;
    for (char datum : data) {
      if (datum == c) {
        counter++;
      }
    }
    int[] indices = new int[counter];
    for (int i = 0, j = 0; i < data.length; i++) {
      if (data[i] == c) {
        indices[j] = i;
        j++;
      }
    }
    return indices;
  }

  public int firstIndexOf(char c) {
    int index = -1;
    for (int i = 0; i < data.length; i++) {
      if (data[i] == c) {
        index = i;
        break;
      }
    }
    return index;
  }

  public int lastIndexOf(char c) {
    int index = -1;
    for (int i = data.length - 1; i >= 0; i--) {
      if (data[i] == c) {
        index = i;
        break;
      }
    }
    return index;
  }

  public boolean contains(char c) {
    boolean found = false;
    for (char chr : data) {
      if (chr == c) {
        found = true;
        break;
      }
    }
    return found;
  }

  public int count(char c) {
    int found = 0;
    for (char chr : data) {
      if (chr == c) {
        found++;
      }
    }
    return found;
  }

  public void replaceAll(char oldChar, char newChar) {
    for (int i = 0; i < data.length; i++) {
      if (data[i] == oldChar) {
        data[i] = newChar;
      }
    }
  }

  public void reverse() {
    char[] reversed = new char[data.length];
    for (int i = data.length; i > 0; i--) {
      reversed[data.length - i] = data[i - 1];
    }
    data = reversed;
  }

  public void append(char c) {
    if (size == data.length) {
      char[] newData = new char[data.length + data.length + 1];
      System.arraycopy(data, 0, newData, 0, size);
      data = newData;
    }
    data[size++] = c;
  }

  public StringTanga subString(int start, int end) {
    if (start < 0 || end >= data.length || start > end) {
      throw new IndexOutOfBoundsException("start and end are not valid, my friend!");
    }
    char[] newData = new char[end - start];
    System.arraycopy(data, start, newData, 0, end - start);
    return new StringTanga(newData);
  }

  public void toUpperCase() {
    for (int i = 0; i < data.length; i++) {
      if (data[i] >= 'a' && data[i] <= 'z') {
        data[i] = (char) (data[i] - 32);
      }
    }
  }

  public void toLowerCase() {
    for (int i = 0; i < data.length; i++) {
      if (data[i] >= 'A' && data[i] <= 'Z') {
        data[i] = (char) (data[i] + 32);
      }
    }
  }

  public boolean startsWidth(char[] c) {
    if (c == null || c.length > data.length) {
      throw new IllegalArgumentException("Character array is invalid");
    }
    for (int i = 0; i < c.length; i++) {
      if (data[i] != c[i]) {
        return false;
      }
    }
    return true;
  }

  public boolean endsWidth(char[] c) {
    if (c == null || c.length > data.length) {
      throw new IllegalArgumentException("Character array is invalid");
    }
    for (int i = 0; i < c.length; i++) {
      if (data[data.length - c.length - i] != c[i]) {
        return false;
      }
    }
    return true;
  }

  public boolean isPalindrome() {
    for (int i = 0; i < data.length / 2; i++) {
      if (data[i] != data[data.length - 1 - i]) {
        return false;
      }
    }
    return true;
  }

  public int[] alphabetIndex() {
    int[] indexes = new int[data.length];
    for (int i = 0; i < data.length; i++) {
      char c = data[i];
      if (!Character.isLetter(c)) {
        throw new IllegalArgumentException("Stringtanga has not-alphabetic characters, my friend!");
      }
      indexes[i] = Character.toUpperCase(c) - 'A';
    }
    return indexes;
  }

  public boolean hasOnlyDigits() {
    for (char c : data) {
      if (Character.isLetter(c)) {
        return false;
      }
    }
    return true;
  }

  public boolean hasDigits() {
    for (char c : data) {
      if (Character.isDigit(c)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasOnlyLetters() {
    for (char c : data) {
      if (Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  public boolean hasLetters() {
    for (char c : data) {
      if (Character.isLetter(c)) {
        return true;
      }
    }
    return false;
  }
}