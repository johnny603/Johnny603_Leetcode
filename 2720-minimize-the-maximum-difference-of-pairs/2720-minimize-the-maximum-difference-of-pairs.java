class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        int result = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canFormPairs(nums, p, mid)) {
                result = mid;
                high = mid - 1; // try smaller max diff
            } else {
                low = mid + 1; // increase allowed max diff
            }
        }

        return result;
    }

    private boolean canFormPairs(int[] nums, int p, int maxDiff) {
        int count = 0;
        int i = 1;

        while (i < nums.length) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                count++;
                i += 2; // skip both indices, they're used
            } else {
                i++; // try next pair
            }

            if (count >= p) return true;
        }

        return count >= p;
    }
}
