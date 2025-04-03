class Solution {
public:
    long long maximumTripletValue(vector<int>& nums) {
        int n = nums.size();
        long long max_triplet_value = 0;

        int max_prefix = nums[0]; // Track the max nums[i] seen so far
        long long max_diff = LLONG_MIN; // Track max (nums[i] - nums[j])

        // Iterate over j (middle index)
        for (int j = 1; j < n - 1; j++) {
            max_diff = max(max_diff, (long long)(max_prefix - nums[j])); // Update max difference
            max_prefix = max(max_prefix, nums[j]); // Update max_prefix for next j
            
            // Compute triplet value if valid
            long long triplet_value = max_diff * nums[j + 1];
            max_triplet_value = max(max_triplet_value, triplet_value);
        }

        return max_triplet_value;
    }
};
