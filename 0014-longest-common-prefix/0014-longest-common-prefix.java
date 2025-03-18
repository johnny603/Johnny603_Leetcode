class Solution {
    public String longestCommonPrefix(String[] strs) {
        // If the array is empty, return an empty string
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Start with the first string as the prefix
        String result = strs[0];

        // Iterate through each string in the array
        for (int i = 1; i < strs.length; i++) {
            // Compare the current string with the result (prefix)
            int j = 0;
            // Find the common prefix between result and strs[i]
            while (j < result.length() && j < strs[i].length() && result.charAt(j) == strs[i].charAt(j)) {
                j++;
            }

            // Update result based on the common part found
            result = result.substring(0, j);

            // If at any point the result becomes empty, return immediately
            if (result.isEmpty()) {
                return "";
            }
        }

        // Return the longest common prefix
        return result;
    }
}
