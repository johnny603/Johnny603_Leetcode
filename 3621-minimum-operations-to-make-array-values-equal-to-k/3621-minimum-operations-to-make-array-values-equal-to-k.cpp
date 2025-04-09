class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        // Step 1: if any num < k, impossible
        for (int num : nums) {
            if (num < k) return -1;
        }

        unordered_map<int, int> freq;
        for (int num : nums) {
            freq[num]++;
        }

        // Step 2: unique values > k
        set<int> greaterVals;
        for (auto& [num, count] : freq) {
            if (num > k) greaterVals.insert(num);
        }

        int ops = 0;
        while (!greaterVals.empty()) {
            int maxVal = *greaterVals.rbegin(); // largest value
            // check if all elements > h are equal (only maxVal should exist above h)
            // So, if there's only 1 unique value left in greaterVals, it’s valid to reduce
            ops++;
            greaterVals.erase(maxVal); // reduce this value
        }

        return ops;
    }
};
