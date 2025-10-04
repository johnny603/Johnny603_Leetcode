// Use two pointers the find these values since you cannot sort
// The sides of the water tank can be formed with the max value and the max - 1 value of the array
// calculate the length between the side values
// area = (max - 1) length

// constraints
/*
n == height.length
2 <= n <= 105
0 <= height[i] <= 104
*/

class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            int width = right - left;
            int area = minHeight * width;
            maxArea = Math.max(maxArea, area);
            
            // Move the pointer pointing to the smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
}

