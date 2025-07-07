class Solution {
    public int maxEvents(int[][] events) {
        // Sort events by their start day
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int day = 1;
        int i = 0;
        int n = events.length;
        int attended = 0;
        
        // While there are events to process or the heap is not empty
        while (i < n || !minHeap.isEmpty()) {
            // Add all events that start today
            while (i < n && events[i][0] == day) {
                minHeap.offer(events[i][1]); // Add end day
                i++;
            }
            
            // Remove all events that have expired before today
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }
            
            // Attend one event that ends the soonest
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                attended++;
            }
            
            day++;
        }
        
        return attended;
    }
}
