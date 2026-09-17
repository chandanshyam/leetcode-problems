/*
3734. Lexicographically Smallest Palindromic Permutation Greater Than Target   [Hard]
https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/

Runtime: 328 ms   Memory: 63.8 MB

You are given two strings `s` and `target`, each of length `n`, consisting of lowercase English letters.

Return the **lexicographically smallest string** that is **both** a **palindromic permutation** of `s` and **strictly** greater than `target`. If no such permutation exists, return an empty string.

**Example 1:**

**Input:** s = "baba", target = "abba"

**Output:** "baab"

**Explanation:**

*   The palindromic permutations of `s` (in lexicographical order) are `"abba"` and `"baab"`.
*   The lexicographically smallest permutation that is strictly greater than `target` is `"baab"`.

**Example 2:**

**Input:** s = "baba", target = "bbaa"

**Output:** ""

**Explanation:**

*   The palindromic permutations of `s` (in lexicographical order) are `"abba"` and `"baab"`.
*   None of them is lexicographically strictly greater than `target`. Therefore, the answer is `""`.

**Example 3:**

**Input:** s = "abc", target = "abb"

**Output:** ""

**Explanation:**

`s` has no palindromic permutations. Therefore, the answer is `""`.

**Example 4:**

**Input:** s = "aac", target = "abb"

**Output:** "aca"

**Explanation:**

*   The only palindromic permutation of `s` is `"aca"`.
*   `"aca"` is strictly greater than `target`. Therefore, the answer is `"aca"`.

**Constraints:**

*   `1 <= n == s.length == target.length <= 300`
*   `s` and `target` consist of only lowercase English letters.
*/

var lexPalindromicPermutation = function (s, target) {
    const n = s.length;
    // Special case: length of 1
    if (n === 1) {
        return s > target ? s : "";
    }

    // Count the frequency of each character
    const cnt = new Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - "a".charCodeAt(0)]++;
    }

    // Check if it can form a palindrome and record the characters with odd occurrences
    let oddChar = "";
    for (let i = 0; i < 26; i++) {
        if (cnt[i] % 2 === 1) {
            // More than one character appears an odd number of times, cannot form a palindrome
            if (oddChar !== "") {
                return "";
            }
            oddChar = String.fromCharCode("a".charCodeAt(0) + i);
        }
        cnt[i] = Math.floor(cnt[i] / 2); // It takes only half the characters to construct the left half
    }

    let prefix = [];

    const check = (c) => {
        const left = [...prefix, c];
        for (let i = 25; i >= 0; i--) {
            for (let k = 0; k < cnt[i]; k++) {
                left.push(String.fromCharCode("a".charCodeAt(0) + i));
            }
        }

        const palindrome = [...left, oddChar, ...left.slice().reverse()].join(
            "",
        );

        return palindrome > target;
    };

    // Construct the left part of each digit greedily
    for (let i = 0; i < Math.floor(n / 2); i++) {
        let found = false;
        // Try to place the smallest character in lexicographical order
        for (let j = 0; j < 26; j++) {
            if (cnt[j] === 0) {
                continue;
            }

            cnt[j]--;
            if (check(String.fromCharCode("a".charCodeAt(0) + j))) {
                // If the constructed palindrome is greater than target, choose the character
                prefix.push(String.fromCharCode("a".charCodeAt(0) + j));
                found = true;
                break;
            } else {
                cnt[j]++; // Not meeting the conditions, reset the counter
            }
        }
        if (!found) {
            return ""; // Cannot construct a palindrome larger than target
        }

        if (prefix[i] > target[i]) {
            // prefix is already greater than target
            const left = [...prefix];
            for (let j = 0; j < 26; j++) {
                for (let k = 0; k < cnt[j]; k++) {
                    left.push(String.fromCharCode("a".charCodeAt(0) + j));
                }
            }
            const palindrome = [
                ...left,
                oddChar,
                ...left.slice().reverse(),
            ].join("");
            return palindrome;
        }
    }

    // Construct the final palindrome string
    const ans = [...prefix, oddChar, ...prefix.slice().reverse()].join("");
    return ans;
};
