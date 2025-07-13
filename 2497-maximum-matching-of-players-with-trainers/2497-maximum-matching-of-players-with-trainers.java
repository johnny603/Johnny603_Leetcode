/*
class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        boolean[] playerUsed = new boolean[players.length];
        boolean[] trainerUsed = new boolean[trainers.length];
        int matches = 0;

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < trainers.length; j++) {
                if (!playerUsed[i] && !trainerUsed[j] && players[i] <= trainers[j]) {
                    playerUsed[i] = true;
                    trainerUsed[j] = true;
                    matches++;
                    break; // move to next player
                }
            }
        }

        return matches;
    }
}
*/

class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int i = 0; // pointer for players
        int j = 0; // pointer for trainers
        int matches = 0;

        while (i < players.length && j < trainers.length) {
            if (players[i] <= trainers[j]) {
                matches++;
                i++;
                j++;
            } else {
                j++; // trainer too weak, try next trainer
            }
        }

        return matches;
    }
}