use std::collections::HashSet;

impl Solution {
    pub fn count_good_integers(n: i32, k: i32) -> i64 {
        let mut count = 0i64;
        let mut seen = HashSet::new();

        // Working copy of n since it may be halved
        let mut half = n;

        if n % 2 == 0 {
            // Even length case: generate full palindromes from half
            half /= 2;
            let start = 10i32.pow((half - 1) as u32);
            let end = 10i32.pow(half as u32);

            for i in start..end {
                // Create palindrome by reversing and appending
                let left = i.to_string();
                let right: String = left.chars().rev().collect();
                let pal = format!("{}{}", left, right);
                let pal_num = pal.parse::<i64>().unwrap();

                // Check divisibility
                if pal_num % (k as i64) == 0 {
                    // Sort digits to find unique combinations
                    let mut chars: Vec<char> = pal.chars().collect();
                    chars.sort_unstable();
                    let sorted_str: String = chars.iter().collect();
                    seen.insert(sorted_str);
                }
            }
        } else {
            // Odd length case: generate palindromes with a middle digit
            half = (n - 1) / 2;
            let start = 10i32.pow(half as u32);
            let end = 10i32.pow((half + 1) as u32);

            for i in start..end {
                let s = i.to_string();
                let prefix = &s[..s.len() - 1];
                let suffix: String = prefix.chars().rev().collect();
                let pal = format!("{}{}", s, suffix);
                let pal_num = pal.parse::<i64>().unwrap();

                if pal_num % (k as i64) == 0 {
                    let mut chars: Vec<char> = pal.chars().collect();
                    chars.sort_unstable();
                    let sorted_str: String = chars.iter().collect();
                    seen.insert(sorted_str);
                }
            }
        }

        // For each unique sorted digit string, compute permutations
        for s in seen {
            let mut total_perms: i64 = 1;
            let mut denom: i64 = 1;
            let mut repeat = 1;
            let mut leading_zero_count = 0;

            // Count leading zero if present
            if s.chars().next().unwrap() == '0' {
                leading_zero_count += 1;
            }

            // Count factorial permutations with duplicate handling
            let chars: Vec<char> = s.chars().collect();
            for i in 1..chars.len() {
                total_perms *= (i as i64 + 1);
                if chars[i] == chars[i - 1] {
                    repeat += 1;
                } else {
                    repeat = 1;
                }
                denom *= repeat as i64;

                if chars[i] == '0' {
                    leading_zero_count += 1;
                }
            }

            // Calculate total permutations, adjust for leading zero if needed
            if leading_zero_count > 0 {
                let perms = total_perms / denom;
                let invalid = (total_perms / chars.len() as i64) / (denom / leading_zero_count as i64);
                count += perms - invalid;
            } else {
                count += total_perms / denom;
            }
        }

        count
    }
}
