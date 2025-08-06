class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        List<Integer> sectorMaxCapacities = new ArrayList<>();
        int totalBaskets = baskets.length;
        int sectorSize = (int) Math.sqrt(totalBaskets); // Size of each sector (block) for efficient max tracking

        int countInSector = 0;
        int currentMaxCapacity = 0;
        
        // Divide baskets into sectors and record the max capacity in each sector
        for (int i = 0; i < totalBaskets; i++) {
            if (countInSector == sectorSize) {
                // Completed one sector; add max capacity to list
                sectorMaxCapacities.add(currentMaxCapacity);
                currentMaxCapacity = baskets[i];
                countInSector = 1;
            } else {
                countInSector++;
                currentMaxCapacity = Math.max(currentMaxCapacity, baskets[i]);
            }
        }
        // Add the last sector's max capacity
        sectorMaxCapacities.add(currentMaxCapacity);

        int unplacedFruits = 0;

        // Try to allocate each fruit type to a basket
        for (int i = 0; i < fruits.length; i++) {
            int sectorIndex = 0;
            boolean fruitPlaced = false;

            // Iterate over sectors in steps of sectorSize
            for (int sectorStart = 0; sectorStart < totalBaskets; sectorStart += sectorSize) {
                // If max basket capacity in sector is less than fruit quantity, skip sector
                if (sectorMaxCapacities.get(sectorIndex) < fruits[i]) {
                    sectorIndex++;
                    continue;
                }

                // Within this sector, find a basket that can hold the fruit
                for (int basketIndex = sectorStart; basketIndex < Math.min(sectorStart + sectorSize, totalBaskets); basketIndex++) {
                    if (baskets[basketIndex] >= fruits[i]) {
                        // Allocate fruit here by marking basket as used (capacity 0)
                        baskets[basketIndex] = 0;
                        fruitPlaced = true;

                        // Update sector max capacity after allocation
                        int newMax = 0;
                        for (int r = sectorStart; r < Math.min(sectorStart + sectorSize, totalBaskets); r++) {
                            newMax = Math.max(newMax, baskets[r]);
                        }
                        sectorMaxCapacities.set(sectorIndex, newMax);
                        break;
                    }
                }

                if (fruitPlaced) {
                    break; // Move on to next fruit
                }
                sectorIndex++;
            }

            if (!fruitPlaced) {
                unplacedFruits++;
            }
        }

        return unplacedFruits;
    }
}
