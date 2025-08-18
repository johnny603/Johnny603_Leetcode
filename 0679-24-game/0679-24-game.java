class Solution {
    public boolean judgePoint24(int[] cards) {
        // convert to double array for floating point division
        List<Double> nums = new ArrayList<>();
        for (int c : cards) {
            nums.add((double) c);
        }

        return backtrack(nums);
    }

    private boolean backtrack(List<Double> nums) {
        // base case: only one number left
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24.0) < 1e-6; // tolerance for float errors
        }

        // try every pair (i, j)
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) continue;

                // build next list without nums[i] and nums[j]
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) {
                        next.add(nums.get(k));
                    }
                }

                // try all possible results from nums[i] and nums[j]
                for (double res : compute(nums.get(i), nums.get(j))) {
                    next.add(res); // choose this operation result
                    if (backtrack(next)) {
                        return true;
                    }
                    next.remove(next.size() - 1); // backtrack
                }
            }
        }

        return false;
    }

    private List<Double> compute(double a, double b) {
        List<Double> results = new ArrayList<>();
        results.add(a + b);
        results.add(a - b);
        results.add(b - a);
        results.add(a * b);
        if (Math.abs(b) > 1e-6) results.add(a / b);
        if (Math.abs(a) > 1e-6) results.add(b / a);
        return results;
    }
}
