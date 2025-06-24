// 1: List
/*
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> sol = new ArrayList<>();
        
        // Loop through all indices in nums
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == key) {
                // If nums[j] is the key, we consider all indices i such that
                // abs(i - j) <= k → i is in range [j - k, j + k]
                int start = Math.max(0, j - k);
                int end = Math.min(nums.length - 1, j + k);

                // Add each valid index to the solution
                for (int i = start; i <= end; i++) {
                    if (!sol.contains(i)) { // avoid duplicates
                        sol.add(i);
                    }
                }
            }
        }

        return sol;
    }
}
*/

// 2: Set

class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        Set<Integer> resultSet = new TreeSet<>(); // TreeSet keeps sorted order
        
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == key) {
                int start = Math.max(0, j - k);
                int end = Math.min(nums.length - 1, j + k);
                
                for (int i = start; i <= end; i++) {
                    resultSet.add(i); // O(log n) with TreeSet
                }
            }
        }

        return new ArrayList<>(resultSet);
    }
}



