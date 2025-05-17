// Method 1 (built in sort)
//class Solution {
//    public void sortColors(int[] nums) {
//        Arrays.sort(nums); // kek
//    }
//}

// Method 2 (bubble sort)
class Solution {
    public void sortColors(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // Swap elements
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }
}

