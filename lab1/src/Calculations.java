public class Calculations {
    public static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static int sum(int a, int b) {
        int result = 0;
        for (int i = a; i <= b; i++) {
            result += i;
        }
        return result;
    }
}
