class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        // iterate through vector
        vector<int>::iterator i;
        for (i = nums.begin(); i != nums.end(); i++) {
            if (*i >= target) {
                return distance(nums.begin(), i); // iterator to index
            }
        }
        return nums.size();
    }
};
