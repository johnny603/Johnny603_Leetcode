import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] sortArrayByParity(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.sort(nums);  // optional: sorts the input array

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                result.add(nums[i]);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) {
                result.add(nums[i]);
            }
        }

        // Convert ArrayList<Integer> to int[]
        int[] output = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }

        return output;
    }
}


// sort nums array
// for int i in array
// if odd add after even
// if even add to new arraylist first
// convert arraylist to array