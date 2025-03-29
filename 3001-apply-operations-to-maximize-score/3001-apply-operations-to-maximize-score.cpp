class Solution {
public:
    const int MOD = 1e9 + 7; // Define a constant MOD value for modular arithmetic

    int maximumScore(vector<int>& nums, int k) {
        int n = nums.size(); // Get the number of elements in nums
        vector<int> primeScores(n); // Array to store prime scores for each number

        // Calculate the prime score for each number in nums
        for (int index = 0; index < n; index++) {
            int num = nums[index]; // Get the current number

            // Check for prime factors from 2 to sqrt(num)
            for (int factor = 2; factor <= sqrt(num); factor++) {
                if (num % factor == 0) { // If factor is a divisor of num
                    primeScores[index]++; // Increment prime score

                    // Remove all occurrences of the prime factor from num
                    while (num % factor == 0) num /= factor;
                }
            }

            // If num is still greater than or equal to 2, it's a prime factor itself
            if (num >= 2) primeScores[index]++;
        }

        // Initialize next and previous dominant index arrays
        vector<int> nextDominant(n, n); // Stores the next dominant index for each element
        vector<int> prevDominant(n, -1); // Stores the previous dominant index for each element

        // Stack to store indices for maintaining a monotonic decreasing prime score
        stack<int> decreasingPrimeScoreStack;

        // Calculate the next and previous dominant indices for each number
        for (int index = 0; index < n; index++) {
            // Maintain a decreasing order of prime scores
            while (!decreasingPrimeScoreStack.empty() &&
                   primeScores[decreasingPrimeScoreStack.top()] < primeScores[index]) {
                int topIndex = decreasingPrimeScoreStack.top(); // Get top index
                decreasingPrimeScoreStack.pop(); // Remove the top element

                // Set the next dominant element for the popped index
                nextDominant[topIndex] = index;
            }

            // If the stack is not empty, set the previous dominant element for the current index
            if (!decreasingPrimeScoreStack.empty())
                prevDominant[index] = decreasingPrimeScoreStack.top();

            // Push the current index onto the stack
            decreasingPrimeScoreStack.push(index);
        }

        // Calculate the number of subarrays in which each element is dominant
        vector<long long> numOfSubarrays(n);
        for (int index = 0; index < n; index++) {
            numOfSubarrays[index] = (long long)(nextDominant[index] - index) * (index - prevDominant[index]);
        }

        // Priority queue to process elements in decreasing order of their value
        priority_queue<pair<int, int>> processingQueue;

        // Push each number and its index onto the priority queue
        for (int index = 0; index < n; index++)
            processingQueue.push({nums[index], index});

        long long score = 1; // Initialize score variable

        // Process elements while there are operations left
        while (k > 0) {
            // Get the element with the maximum value from the queue
            auto [num, index] = processingQueue.top();
            processingQueue.pop();

            // Calculate the number of operations to apply on the current element
            long long operations = min((long long)k, numOfSubarrays[index]);

            // Update the score by raising the element to the power of operations
            score = (score * power(num, operations)) % MOD;

            // Reduce the remaining operations count
            k -= operations;
        }

        return score; // Return the final computed score
    }

private:
    // Helper function to compute the power of a number modulo MOD
    long long power(long long base, long long exponent) {
        long long res = 1; // Initialize result as 1

        // Calculate exponentiation using binary exponentiation
        while (exponent > 0) {
            // If the exponent is odd, multiply the result by the base
            if (exponent % 2 == 1) {
                res = ((res * base) % MOD);
            }

            // Square the base and halve the exponent
            base = (base * base) % MOD;
            exponent /= 2;
        }

        return res; // Return the computed power
    }
};
