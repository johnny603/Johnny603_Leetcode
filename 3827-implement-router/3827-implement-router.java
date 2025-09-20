class Router {
    private int memoryLimit;
    private Queue<int[]> queue;  // FIFO storage
    private Set<String> seen;    // duplicate detection
    private Map<Integer, List<Integer>> destMap; // destination -> timestamps

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.queue = new LinkedList<>();
        this.seen = new HashSet<>();
        this.destMap = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String key = source + "," + destination + "," + timestamp;
        if (seen.contains(key)) return false; // duplicate

        // evict oldest if memory is full
        if (queue.size() == memoryLimit) {
            int[] old = queue.poll();
            String oldKey = old[0] + "," + old[1] + "," + old[2];
            seen.remove(oldKey);

            // remove from destMap
            List<Integer> list = destMap.get(old[1]);
            list.remove(0); // since FIFO, earliest is at index 0
            if (list.isEmpty()) destMap.remove(old[1]);
        }

        // add new packet
        queue.offer(new int[]{source, destination, timestamp});
        seen.add(key);

        destMap.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);
        return true;
    }

    public int[] forwardPacket() {
        if (queue.isEmpty()) return new int[]{};

        int[] packet = queue.poll();
        String key = packet[0] + "," + packet[1] + "," + packet[2];
        seen.remove(key);

        // remove from destMap
        List<Integer> list = destMap.get(packet[1]);
        list.remove(0); // oldest first
        if (list.isEmpty()) destMap.remove(packet[1]);

        return packet;
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!destMap.containsKey(destination)) return 0;

        List<Integer> timestamps = destMap.get(destination);

        // binary search range [startTime, endTime]
        int left = lowerBound(timestamps, startTime);
        int right = upperBound(timestamps, endTime);
        return right - left;
    }

    private int lowerBound(List<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (arr.get(m) >= target) r = m;
            else l = m + 1;
        }
        return l;
    }

    private int upperBound(List<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (arr.get(m) > target) r = m;
            else l = m + 1;
        }
        return l;
    }
}
