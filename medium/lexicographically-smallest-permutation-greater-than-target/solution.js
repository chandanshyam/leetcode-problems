/*
3720. Lexicographically Smallest Permutation Greater Than Target   [Medium]
https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target/

Runtime: 10 ms   Memory: 62.2 MB

You are given two strings `s` and `target`, both having length `n`, consisting of lowercase English letters.

Return the **lexicographically smallest permutation** of `s` that is **strictly** greater than `target`. If no permutation of `s` is lexicographically strictly greater than `target`, return an empty string.

A string `a` is **lexicographically strictly greater** than a string `b` (of the same length) if in the first position where `a` and `b` differ, string `a` has a letter that appears later in the alphabet than the corresponding letter in `b`.

**Example 1:**

**Input:** s = "abc", target = "bba"

**Output:** "bca"

**Explanation:**

*   The permutations of `s` (in lexicographical order) are `"abc"`, `"acb"`, `"bac"`, `"bca"`, `"cab"`, and `"cba"`.
*   The lexicographically smallest permutation that is strictly greater than `target` is `"bca"`.

**Example 2:**

**Input:** s = "leet", target = "code"

**Output:** "eelt"

**Explanation:**

*   The permutations of `s` (in lexicographical order) are `"eelt"`, `"eetl"`, `"elet"`, `"elte"`, `"etel"`, `"etle"`, `"leet"`, `"lete"`, `"ltee"`, `"teel"`, `"tele"`, and `"tlee"`.
*   The lexicographically smallest permutation that is strictly greater than `target` is `"eelt"`.

**Example 3:**

**Input:** s = "baba", target = "bbaa"

**Output:** ""

**Explanation:**

*   The permutations of `s` (in lexicographical order) are `"aabb"`, `"abab"`, `"abba"`, `"baab"`, `"baba"`, and `"bbaa"`.
*   None of them is lexicographically strictly greater than `target`. Therefore, the answer is `""`.

**Constraints:**

*   `1 <= s.length == target.length <= 300`
*   `s` and `target` consist of only lowercase English letters.
*/

var lexGreaterPermutation = function (s, target) {
    const cnt = new Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }

    let res = "";
    const n = target.length;

    for (let i = 0; i < n; i++) {
        const targetChar = target.charCodeAt(i) - 97;

        // Case 1: First try to place the same character as target[i] at the current position
        if (cnt[targetChar] > 0) {
            cnt[targetChar]--;
            // Check if the remaining characters can form a string greater than target[i+1:]
            if (canFormGreater(cnt, target, i + 1)) {
                res += target[i];
                continue;
            }
            // Cannot form a larger string, backtrack
            cnt[targetChar]++;
        }

        // Case 2: Place a character greater than target[i] at the current position
        for (let j = targetChar + 1; j < 26; j++) {
            if (cnt[j] > 0) {
                cnt[j]--;
                res += String.fromCharCode(97 + j);
                // Fill remaining positions with the smallest lexicographical order
                res += getMinString(cnt);
                return res;
            }
        }

        // No feasible solution found, return directly
        return "";
    }

    return "";
};

// Check if the remaining characters can form a string greater than the suffix.
function canFormGreater(cnt, target, start) {
    const maxStr = getMaxString(cnt);
    const suffix = target.substring(start);
    return maxStr > suffix;
}

// Get the maximum lexicographical string (in descending order)
function getMaxString(cnt) {
    let res = "";
    for (let i = 25; i >= 0; i--) {
        res += String.fromCharCode(97 + i).repeat(cnt[i]);
    }
    return res;
}

// Get the lexicographically smallest string (in ascending order)
function getMinString(cnt) {
    let res = "";
    for (let i = 0; i < 26; i++) {
        res += String.fromCharCode(97 + i).repeat(cnt[i]);
    }
    return res;
}
