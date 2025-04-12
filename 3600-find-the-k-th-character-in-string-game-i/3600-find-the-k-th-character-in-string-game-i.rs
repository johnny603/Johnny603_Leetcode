impl Solution {
    // Define a public function inside the Solution struct that takes an integer `k` and returns a character
    pub fn kth_character(k: i32) -> char {
        // Start with the initial word being just "a"
        let mut word = String::from("a");
        
        // Continue generating the string until its length is at least `k`
        while word.len() < k as usize {
            // Create a new string to store the next characters (the transformation of current word)
            let mut next = String::new();

            // Iterate through each character in the current word
            for ch in word.chars() {
                // Find the "next" character in the alphabet
                let next_ch = if ch == 'z' {
                    // If the current character is 'z', wrap around to 'a'
                    'a'
                } else {
                    // Otherwise, get the next character by adding 1 to the ASCII value
                    ((ch as u8) + 1) as char
                };

                // Append the next character to the `next` string
                next.push(next_ch);
            }

            // Append the newly generated string to the original word
            word.push_str(&next);
        }
        
        // Return the (k-1)th character from the string, safely unwrapped
        // Rust strings are 0-indexed, so we use `k - 1`
        word.chars().nth((k - 1) as usize).unwrap()
    }
}



// Idea:
// String word = "a"
// Use recursion since the constraints are small
// Until integer k is reached perform
// Use ord
// append a 'character + 1' to "word"



// String::from("a") creates a growable string from a string literal.
// mut means the variable can be changed (mutable).
// as usize is used to convert the i32 type to a usize (needed for indexing or lengths).
// for ch in word.chars(): .chars() splits the string into individual chars (Unicode scalar values).
// as u8 and back to char lets us use ASCII math.
// .push() and .push_str() append characters or strings.
// .nth(n).unwrap() gets the nth character (zero-indexed). .unwrap() assumes it's safe to access it because we ensured the length is big enough.