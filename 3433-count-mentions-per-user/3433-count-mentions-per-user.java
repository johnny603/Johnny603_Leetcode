class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {

        int[] mentions = new int[numberOfUsers];
        boolean[] online = new boolean[numberOfUsers];
        Arrays.fill(online, true); // all users start online

        int[] offlineUntil = new int[numberOfUsers]; // time when user will return online

        // Convert to Event objects for sorting and easy processing
        List<Event> list = new ArrayList<>();
        for (List<String> e : events) {
            list.add(new Event(e.get(0), Integer.parseInt(e.get(1)), e.get(2)));
        }

        // Sort by timestamp, and when timestamps are equal ensure OFFLINE events
        // come before MESSAGE events (so status changes happen before messages at same time).
        Collections.sort(list, (a, b) -> {
            if (a.time != b.time) return Integer.compare(a.time, b.time);
            if (a.type.equals(b.type)) return 0;
            // OFFLINE should come before MESSAGE at same timestamp
            if (a.type.equals("OFFLINE")) return -1;
            return 1;
        });

        // Process events in chronological order (with tie-breaker)
        for (Event ev : list) {

            int timestamp = ev.time;

            // 1) Before handling this event, bring back any users whose offline period ended
            //    at or before the current timestamp.
            for (int u = 0; u < numberOfUsers; u++) {
                if (!online[u] && offlineUntil[u] <= timestamp) {
                    online[u] = true;
                }
            }

            // 2) Process the event
            if (ev.type.equals("OFFLINE")) {
                // OFFLINE event: user becomes offline for 60 time units
                int id = Integer.parseInt(ev.data);
                online[id] = false;
                offlineUntil[id] = timestamp + 60;

            } else { // MESSAGE
                // Split tokens by single spaces (per problem)
                String[] tokens = ev.data.split(" ");
                for (String token : tokens) {
                    if (token.equals("ALL")) {
                        // ALL mentions every user (offline or online)
                        for (int u = 0; u < numberOfUsers; u++) mentions[u]++;
                    } else if (token.equals("HERE")) {
                        // HERE mentions only currently online users
                        for (int u = 0; u < numberOfUsers; u++) {
                            if (online[u]) mentions[u]++;
                        }
                    } else if (token.startsWith("id")) {
                        // idX -> increment that user's mentions (duplicates count)
                        int id = Integer.parseInt(token.substring(2));
                        mentions[id]++;
                    }
                }
            }
        }

        return mentions;
    }

    // Helper class to store an event compactly
    static class Event {
        String type; // "MESSAGE" or "OFFLINE"
        int time;
        String data; // mentions string or id string

        Event(String t, int tm, String d) {
            type = t;
            time = tm;
            data = d;
        }
    }
}
