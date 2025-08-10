/*
Convert n to a sorted string of its digits
Generate all powers of two up to the limit (2^30)
Sort each power’s digits and compare to n’s sorted digits
If any match, return true; otherwise false
*/
class Solution {
    public boolean reorderedPowerOf2(int n) {
        String target = sortDigits(n);
        
        for (int i = 0; i < 31; i++) { // 2^0 to 2^30 covers up to 1,073,741,824 > 10^9
            int power = 1 << i;
            if (target.equals(sortDigits(power))) {
                return true;
            }
        }
        return false;
    }
    
    private String sortDigits(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
