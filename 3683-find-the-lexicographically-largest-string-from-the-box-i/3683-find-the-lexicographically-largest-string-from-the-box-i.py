class Solution:
    def answerString(self, word: str, numFriends: int) -> str:
        if numFriends == 1:
            return word

        max_substring = ''
        substring_size = len(word) - numFriends + 1

        for i in range(len(word)):
            max_substring = max(max_substring, word[i:i + substring_size])

        return max_substring

        