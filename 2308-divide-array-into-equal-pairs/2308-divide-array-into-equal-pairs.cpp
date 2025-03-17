/**
You are given an integer array nums consisting of 2 * n integers.
You need to divide nums into n pairs such that:
Each element belongs to exactly one pair.
The elements present in a pair are equal.
Return true if nums can be divided into n pairs, otherwise return false.

Example 1:
Input: nums = [3,2,3,2,2,2]
Output: true
Explanation: 
There are 6 elements in nums, so they should be divided into 6 / 2 = 3 pairs.
If nums is divided into the pairs (2, 2), (3, 3), and (2, 2), it will satisfy all the conditions.

Example 2:
Input: nums = [1,2,3,4]
Output: false
Explanation: 
There is no way to divide nums into 4 / 2 = 2 pairs such that the pairs satisfy every condition.
 
Constraints:
nums.length == 2 * n
1 <= n <= 500
1 <= nums[i] <= 500
**/



// Idea
/**
#include <vector>;
#include <iterator>;

class Solution {
public:
    bool divideArray(vector<int>& nums) { // note to change this to iterators
        int arrSize = nums.size();
        int numPairs = arrSize / 2;
        int pairs = numPairs;
        // we would have to check if there is an odd array, that is the default case

        // if two integers are equal, that is a pair
        // We would need to sort the array before we find pairs

        // sort array; selection sort
        for (i = 0; i < arrSize - 1; i++) {

        // Find index of smallest remaining element
        indexSmallest = i;
            for (j = i + 1; j < arrSize; ++j) {

            if (arrSize[j] < arrSize[indexSmallest]) {
                 indexSmallest = j;
            }
        }

        // Swap numbers[i] and numbers[indexSmallest]
        temp = arrSize[i];
        numbers[i] = numbers[indexSmallest];
        numbers[indexSmallest] = temp;
}


        // find pairs
        for (unsigned int i = 0; i < arrSize; i++) {
            if (arrSize[i] == arrSize[i + 1]) { // find a pair
               pairs++; // number of pairs must equal numPairs variable
            }
        }
        return true;       
    }
};
**/

// Solution

#include <vector>
#include <unordered_map>
#include <iostream>

class Solution {
public:
    bool divideArray(std::vector<int>& nums) {
        std::unordered_map<int, int> freqMap;
        
        // Count the frequency of each number
        for (int num : nums) {
            freqMap[num]++;
        }
        
        // Check if all elements have even frequency
        for (const auto& entry : freqMap) {
            if (entry.second % 2 != 0) {
                return false; // If any number has odd frequency, return false
            }
        }
        
        return true; // All elements have even frequency, so pairs are possible
    }
};

// What I learned

// map
// freqMap
// divideArray

