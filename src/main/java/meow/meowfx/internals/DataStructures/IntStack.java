package meow.meowfx.internals.DataStructures;

import meow.meowfx.internals.BasicAPIs.Console;

public class IntStack {

  private int[] stacki;
  private int top = -1;

  public IntStack(int size) {
    stacki = new int[size];
  }

  public int get(int index) {
    return stacki[index];
  }

  public void set(int index, int value) {
    stacki[index] = value;
  }

  public int size() {
    return top + 1;
  }

  public int pop() {
    if (top < 0) {
      throw new IllegalStateException("Stack is empty");
    }
    return stacki[top--];
  }

  public void push(int value) {
    if (top >= stacki.length - 1) {
      throw new IndexOutOfBoundsException("Stack overflow, my friend!");
    }
    stacki[++top] = value;
  }

  public int peek() {
    if (top < 0) {
      throw new IllegalStateException("Stack is empty");
    }
    return stacki[top];
  }

  public boolean isEmpty() {
    return top < 0;
  }

  public void destroy() {
    stacki = null;
  }

  public int sum() {
    int sum = 0;
    for (int i = 0; i < top; i++) {
      sum += stacki[i];
    }
    return sum;
  }

  public int[] sorted() {
    int[] sortedArray = new int[top + 1];
    System.arraycopy(stacki, 0, sortedArray, 0, top + 1);
    java.util.Arrays.sort(sortedArray);
    return sortedArray;
  }

  public int search(int intToSearch) {
    for (int i = 0; i <= top; i++) {
      if (stacki[i] == intToSearch) {
        return i;
      }
    }
    return -1;
  }

  public void print() {
    for (int i = 0; i < top; i++) {
      Console.log("Stack[" + i + "]" + " : " + stacki[i]);
    }
  }
}