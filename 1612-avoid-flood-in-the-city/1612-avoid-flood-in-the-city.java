class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        
        Map<Integer, Integer> fullLakes = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();
        
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                dryDays.add(i);
                ans[i] = 1; // default (will update if we assign this day to dry a specific lake)
            } else {
                int lake = rains[i];
                ans[i] = -1; // rainy day
                if (fullLakes.containsKey(lake)) {
                    Integer dryDay = dryDays.ceiling(fullLakes.get(lake) + 1);
                    if (dryDay == null) {
                        return new int[0]; // flood unavoidable
                    }
                    ans[dryDay] = lake; // dry this lake on dryDay
                    dryDays.remove(dryDay);
                }
                fullLakes.put(lake, i); // mark this lake as full
            }
        }
        
        return ans;
    }
}
