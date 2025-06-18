class Solution {
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        List<int[]> result = new ArrayList<>(); // array list of arrays

        for (int i = 0; i < nums.length; i += 3) { // n is a multiple of 3
            int[] group = new int[]{nums[i], nums[i + 1], nums[i + 2]}; // size 3 array
            int max = Math.max(group[0], Math.max(group[1], group[2]));
            int min = Math.min(group[0], Math.min(group[1], group[2]));

            if (max - min > k) {
                return new int[0][0]; // return empty array if condition not met
            }

            result.add(group); // append array
        }

        return result.toArray(new int[result.size()][]); // convert the array list to a 2d array
    }
}
