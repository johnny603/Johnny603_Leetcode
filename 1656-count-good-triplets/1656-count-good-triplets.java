class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int goodCount = 0;

        for (int i = 0; i < arr.length - 2; i++) { // 2 more to form a triplet
            for (int j = i + 1; j < arr.length - 1; j++) { // 1 more to form a triplet
                for (int k = j + 1; k < arr.length; k++) { // 0 more to form a triplet
                        if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c && Math.abs(arr[i] - arr[j]) <= a) {
                            goodCount++;
                        }
                }
            }
        }
        return goodCount;
    }
}
