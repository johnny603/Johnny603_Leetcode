class Solution {
    public String triangleType(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];

        // First check if it forms a valid triangle using the triangle inequality
        if (a + b <= c || a + c <= b || b + c <= a) {
            return "none";
        }

        // If all sides are equal
        if (a == b && b == c) {
            return "equilateral";
        }

        // If two sides are equal
        if (a == b || b == c || a == c) {
            return "isosceles";
        }

        // If all sides are different
        return "scalene";
    }
}