/*
Use a set cur to track all OR results of subarrays ending at the current index.

Use another set res to track all distinct OR results globally.

Iterate through the array:

For each element, update cur by OR-ing it with each value from the previous cur, and add the current element as a standalone subarray.

Merge cur into res.
*/
public class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> res = new HashSet<>();
        Set<Integer> cur = new HashSet<>();

        for (int num : arr) {
            Set<Integer> next = new HashSet<>();
            next.add(num);
            for (int x : cur) {
                next.add(x | num);
            }
            cur = next;
            res.addAll(cur);
        }

        return res.size();
    }
}
