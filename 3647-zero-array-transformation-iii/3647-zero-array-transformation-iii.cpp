#include <algorithm>
#include <queue>
#include <vector>

using namespace std;

class Solution {
public:
    int maxRemoval(vector<int>& nums, vector<vector<int>>& queries) {
        sort(queries.begin(), queries.end()); // Sort queries by start index

        int chosen_queries = 0;
        priority_queue<int>
            candidates; // Max-heap (by negating values in Python)
        priority_queue<int, vector<int>, greater<int>> chosen; // Min-heap
        int index = 0;
        int query_index = 0;

        while (index < nums.size()) {
            // Add all queries whose start index is <= current index
            while (query_index < queries.size() &&
                   index >= queries[query_index][0]) {
                // Use max-heap by pushing end values as positive
                candidates.push(queries[query_index][1]);
                query_index++;
            }

            int num = nums[index];

            // Select up to 'num' valid queries that end after current index
            while (!candidates.empty() && chosen.size() < num) {
                int end = candidates.top();
                candidates.pop();
                if (end >= index) {
                    chosen.push(end); // Add to min-heap
                    chosen_queries++;
                }
            }

            // If we cannot find enough valid queries, return -1
            if (nums[index] > chosen.size()) {
                return -1;
            }
            nums[index] = 0; // Mark as processed

            // Remove expired chosen queries
            while (!chosen.empty() && chosen.top() <= index) {
                chosen.pop();
            }

            index++;
        }

        // Return how many queries were not used
        return queries.size() - chosen_queries;
    }
};
