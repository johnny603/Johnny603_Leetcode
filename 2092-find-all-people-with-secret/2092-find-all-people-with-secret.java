class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        // secretKnown[p] == true means person p currently knows the secret
        boolean[] secretKnown = new boolean[n];

        // Initially, person 0 and firstPerson know the secret at time 0
        secretKnown[0] = true;
        secretKnown[firstPerson] = true;

        // Sort all meetings by time (ascending)
        Arrays.sort(meetings, Comparator.comparingInt(m -> m[2]));

        int meetingCount = meetings.length;

        // Process meetings grouped by the same time
        for (int i = 0; i < meetingCount; ) {

            int currentTime = meetings[i][2];
            int j = i;

            // Find the range of meetings that occur at the same time
            while (j + 1 < meetingCount && meetings[j + 1][2] == currentTime) {
                j++;
            }

            /*
             * Build a temporary graph of people meeting at this exact time.
             * We only allow the secret to spread within this graph.
             */
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Set<Integer> participants = new HashSet<>();

            for (int k = i; k <= j; k++) {
                int personA = meetings[k][0];
                int personB = meetings[k][1];

                // Undirected edge: A <-> B
                graph.computeIfAbsent(personA, x -> new ArrayList<>()).add(personB);
                graph.computeIfAbsent(personB, x -> new ArrayList<>()).add(personA);

                // Track all people involved at this time
                participants.add(personA);
                participants.add(personB);
            }

            /*
             * BFS to spread the secret within the connected components
             * of this time slice.
             *
             * Important: only people who already know the secret at the
             * start of this time can initiate spreading.
             */
            Queue<Integer> queue = new ArrayDeque<>();

            // Initialize BFS with participants who already know the secret
            for (int person : participants) {
                if (secretKnown[person]) {
                    queue.offer(person);
                }
            }

            // Standard BFS to propagate the secret
            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (int neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                    if (!secretKnown[neighbor]) {
                        secretKnown[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }

            // Move to the next group of meeting times
            i = j + 1;
        }

        // Collect all people who know the secret
        List<Integer> result = new ArrayList<>();
        for (int person = 0; person < n; person++) {
            if (secretKnown[person]) {
                result.add(person);
            }
        }

        return result;
    }
}
