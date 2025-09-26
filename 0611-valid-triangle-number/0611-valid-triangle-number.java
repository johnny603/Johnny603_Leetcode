// What makes a triangle?
// added length of any 2 side it will be greater than 3rd side length
// nums[i] + nums[j] > nums[k]
// Plan: Sort array so the triangle definition holds, then use two pointers to check each triplet

class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;

        // fix the largest side at index k
        for (int k = n - 1; k >= 2; k--) {
            int l = 0;
            int r = k - 1;

            while (l < r) {
                if (nums[l] + nums[r] > nums[k]) {
                    // all indices between l and r-1 also work with r
                    count += (r - l);
                    r--;
                } else {
                    l++;
                }
            }
        }

        return count;
    }
}