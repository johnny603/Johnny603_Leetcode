class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        
        // Binary search over the possible minimum power
        long left = 0;
        long right = (long) 2e14; // Large enough upper bound
        long result = 0;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (canAchieve(stations, r, k, mid, n)) {
                result = mid;
                left = mid + 1; // Try for a higher minimum power
            } else {
                right = mid - 1; // Lower the target
            }
        }
        
        return result;
    }
    
    private boolean canAchieve(int[] stations, int r, long k, long minPower, int n) {
        long used = 0;
        long[] added = new long[n];  // Tracks how many stations we added at each index
        
        // Prefix sum for the initial station powers
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stations[i];
        }
        
        long windowAdd = 0; // Current contribution from added stations within the window
        
        for (int i = 0; i < n; i++) {
            // Compute total power for city i (within [i - r, i + r])
            int left = Math.max(0, i - r);
            int right = Math.min(n - 1, i + r);
            long totalPower = prefix[right + 1] - prefix[left] + windowAdd;
            
            // If below minimum required power, add stations at farthest right possible position
            if (totalPower < minPower) {
                long need = minPower - totalPower;
                used += need;
                if (used > k) return false; // Too many added
                
                int addPos = Math.min(n - 1, i + r);
                added[addPos] += need;
                windowAdd += need; // Add effect for future cities
            }
            
            // Slide the window: remove contribution that goes out of range
            if (i - r >= 0) {
                windowAdd -= added[i - r];
            }
        }
        
        return true;
    }
}
