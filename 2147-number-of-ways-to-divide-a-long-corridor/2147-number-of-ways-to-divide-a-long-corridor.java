class Solution {
    public int numberOfWays(String corridor) {
        long mod = 1_000_000_007L;
        int n = corridor.length();
        
        // Count total number of seats
        int totalSeats = 0;
        for (char c : corridor.toCharArray()) {
            if (c == 'S') totalSeats++;
        }
        
        // Impossible if odd number of seats or less than 2
        if (totalSeats % 2 != 0 || totalSeats == 0) return 0;
        
        long ways = 1;
        int seatsCount = 0;
        int gapsBetweenSections = 0;
        boolean firstSectionFound = false;
        
        for (char c : corridor.toCharArray()) {
            if (c == 'S') {
                seatsCount++;
                if (seatsCount % 2 == 0) { // end of a section
                    if (firstSectionFound) {
                        ways = (ways * (gapsBetweenSections + 1)) % mod;
                    } else {
                        firstSectionFound = true; // first section
                    }
                    gapsBetweenSections = 0; // reset for next section
                }
            } else { // c == 'P'
                if (seatsCount % 2 == 0 && seatsCount != 0) {
                    gapsBetweenSections++;
                }
            }
        }
        
        return (int) ways;
    }
}
