class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int start = 0; start < n; start++) {
            if (nums[start] == 0) {
                // Try both directions: -1 (left) and +1 (right)
                if (isValid(nums.clone(), start, -1)) count++;
                if (isValid(nums.clone(), start, 1)) count++;
            }
        }

        return count;
    }

    private boolean isValid(int[] arr, int curr, int dir) {
        int n = arr.length;

        while (curr >= 0 && curr < n) {
            if (arr[curr] == 0) {
                curr += dir;
            } else {
                arr[curr]--;
                dir *= -1; // reverse direction
                curr += dir;
            }
        }

        // check if all zeros
        for (int val : arr) {
            if (val != 0) return false;
        }
        return true;
    }
}
