class Solution {
    public int removeElement(int[] nums, int val) {
        // Initialize a variable to track the position for elements that are not equal to val
        int k = 0;
        
        // Loop through each element in the input array
        for (int i = 0; i < nums.length; i++) {
            // If the current element is not equal to val, we keep it
            if (nums[i] != val) {
                // Place the current element at the position tracked by k
                nums[k] = nums[i];
                // Increment k to move to the next available position
                k++;
            }
        }
        
        // Return k, which is the count of elements that are not equal to val
        return k;
    }
}
