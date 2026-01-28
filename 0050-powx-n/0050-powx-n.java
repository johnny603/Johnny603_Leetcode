/*
- if n is even x^n = (x^n/2)^2
- if n is odd x^n = x * x^n-1
*/
class Solution {
    public double myPow(double x, int n) {
        long N = n; // prevent overflow
        // x^-n = 1/x^n
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return pow(x, N);
    }

    private double pow(double x, long n) {
        if (n == 0) return 1.0;

        double half = pow(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
