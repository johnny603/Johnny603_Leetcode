class Solution {
    public int minOperations(int[] nums) {
        int minOperations = nums[0];
        int n = nums.length;
        int ones = 0;
        int minLen = n + 1;

        for (int i = 1; i < n; i++) {
            minOperations = gcd(minOperations, nums[i]);
        }

        if (minOperations != 1)
            return -1;

        for (int num : nums)
            if (num == 1) {
                ones++;
            }

        if (ones > 0) {
            return n - ones;
        }

        for (int i = 0; i < n; i++) {
            int g = nums[i];
            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }

        return (minLen - 1) + (n - 1);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }

    }

}