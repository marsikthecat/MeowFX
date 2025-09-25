package meow.meowfx.internals.Math;

public class Math {

  public static double sinus(double a) {
    return java.lang.Math.sin(java.lang.Math.toRadians(a));
  }

  public static double cosinus(double a) {
    return java.lang.Math.cos(java.lang.Math.toRadians(a));
  }

  public static double tangens(double a) {
    return java.lang.Math.tan(java.lang.Math.toRadians(a));
  }

  public static double ln(double a) {
    return java.lang.Math.log(a);
  }

  public static double log10(double a) {
    return java.lang.Math.log10(a);
  }

  public static double gcd(int a, int b) {
    return a == 0 ? b : gcd(b % a, a);
  }

  public static double scd(int a, int b) {
    return (a * b) / gcd(a, b);
  }

  public static double factorial(int a) {
    return a <= 1 ? 1 : a * factorial(a - 1);
  }

  public static double fibonacci(int a) {
    return a <= 1 ? a : fibonacci(a - 1) + fibonacci(a - 2);
  }

  public static double calculateCapital(double capital, double interestRate, int years) {
    return capital * java.lang.Math.pow(1 + interestRate / 100, years);
  }

  public static double increasingSum(int start, int end) {
    return (double) (end * (end + 1)) / 2 - (double) ((start - 1) * start) / 2;
  }

  public static double slope(double y2, double y1, double x2, double x1) {
    if (x2 - x1 == 0) {
      throw new ArithmeticException("Division by zero: x2 and x1 cannot be equal.");
    }
    return (y2 - y1) / (x2 - x1);
  }

  public static int max(int... nums) {
    if (nums.length == 0) {
      throw new IllegalArgumentException("Cannot find maximum out of nothing.");
    }
    int max = nums[0];
    for (int anInt : nums) {
      if (anInt > max) {
        max = anInt;
      }
    }
    return max;
  }

  public static int min(int... nums) {
    if (nums.length == 0) {
      throw new IllegalArgumentException("Cannot find minimum out of nothing.");
    }
    int min = nums[0];
    for (int anInt : nums) {
      if (anInt < min) {
        min = anInt;
      }
    }
    return min;
  }

  public static int sum(int... nums) {
    if (nums.length == 0) {
      throw new IllegalArgumentException("Cannot find sum out of nothing.");
    }
    int sum = nums[0];
    if (nums.length == 1) {
      return sum;
    }
    for (int anInt : nums) {
      sum += anInt;
    }
    return sum;
  }

  public static int avg(int... nums) {
    if (nums.length == 0) {
      throw new IllegalArgumentException("Cannot find average out of nothing.");
    }
    return sum(nums) / nums.length;
  }

  public static int random(int start, int end) {
    if (start >= end) {
      throw new IllegalArgumentException("Invalid range, my friend");
    }
    return (int) (java.lang.Math.random() * (end - start + 1)) + start;
  }
}