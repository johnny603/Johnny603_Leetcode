class Solution {
    public String largestNumber(int[] nums) {
        // Convert int[] to String[] so we can compare by concatenation
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        // Sort with custom comparator
        // Compare combined strings: which order gives a larger result?
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        // Step 3: If the largest number is "0", then all are zeros
        if (arr[0].equals("0")) {
            return "0";
        }

        // Build the largest number by concatenating
        StringBuilder sb = new StringBuilder();
        for (String num : arr) {
            sb.append(num);
        }

        return sb.toString();
    }
}
