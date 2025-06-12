class Solution {
  int maxAdjacentDistance(List<int> nums) {
    int max = 0;
    int n = nums.length;

    for (int i = 0; i < n; i++) {
      int next = (i + 1) % n; // circular array
      int sol = (nums[i] - nums[next]).abs();
      max = max > sol ? max : sol;
    }

    return max;
  }
}
