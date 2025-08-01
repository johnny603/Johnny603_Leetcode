class Solution {
  
    // Function to find the room that gets booked the most
    public int mostBooked(int n, int[][] meetings) {
        // Sort all the meetings by their start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
      
        // Priority queue to keep track of busy rooms
        // Sort by end time; if end times are same, sort by room id
        PriorityQueue<int[]> busyRooms = new PriorityQueue<>(
        (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
      
        // Priority queue for idle rooms, sorted by room id
        PriorityQueue<Integer> idleRooms = new PriorityQueue<>();
      
        // Initialize all rooms as idle
        for (int i = 0; i < n; i++) {
            idleRooms.offer(i);
        }
      
        // Counter for the number of meetings each room has
        int[] count = new int[n];
      
        // Iterate over all meetings
        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];
          
            // Free up rooms that have finished their meeting before the start of the current meeting
            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= start) {
                idleRooms.offer(busyRooms.poll()[1]);
            }
          
            // Choose a room for the current meeting
            int roomCurrent;
            if (!idleRooms.isEmpty()) {
                // If there is an idle room available, use the one with the smallest id
                roomCurrent = idleRooms.poll();
                // Add the meeting to the busy queue with the new end time and the room id
                busyRooms.offer(new int[] {end, roomCurrent});
            } else {
                // If no idle room is available, wait for the next room to be free
                int[] busyRoom = busyRooms.poll();
                roomCurrent = busyRoom[1];
                // Schedule the meeting in this room, adjusting the meeting length accordingly
                busyRooms.offer(new int[] {busyRoom[0] + end - start, roomCurrent});
            }
          
            // Increment the count of meetings for the chosen room
            count[roomCurrent]++;
        }
      
        // Find the room with the maximum number of meetings booked
        int mostBookedRoom = 0;
        for (int i = 0; i < n; i++) {
            if (count[mostBookedRoom] < count[i]) {
                mostBookedRoom = i;
            }
        }
      
        // Return the room id with the highest booking count
        return mostBookedRoom;
    }
}