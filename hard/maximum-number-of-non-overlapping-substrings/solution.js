/*
1520. Maximum Number of Non-Overlapping Substrings   [Hard]
https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/

Runtime: 47 ms   Memory: 61.3 MB

Given a string `s` of lowercase letters, you need to find the maximum number of **non-empty** substrings of `s` that meet the following conditions:

1.  The substrings do not overlap, that is for any two substrings `s[i..j]` and `s[x..y]`, either `j < x` or `i > y` is true.
2.  A substring that contains a certain character `c` must also contain all occurrences of `c`.

Find _the maximum number of substrings that meet the above conditions_. If there are multiple solutions with the same number of substrings, _return the one with minimum total length._ It can be shown that there exists a unique solution of minimum total length.

Notice that you can return the substrings in **any** order.

**Example 1:**

**Input:** s = "adefaddaccc"
**Output:** \["e","f","ccc"\]
**Explanation:** The following are all the possible substrings that meet the conditions:
\[
  "adefaddaccc"
  "adefadda",
  "ef",
  "e",
  "f",
  "ccc",
\]
If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose \["e","f","ccc"\] which gives us 3 substrings. No other solution of the same number of substrings exist.

**Example 2:**

**Input:** s = "abbaccd"
**Output:** \["d","bb","cc"\]
**Explanation:** Notice that while the set of substrings \["d","abba","cc"\] also has length 3, it's considered incorrect since it has larger total length.

**Constraints:**

*   `1 <= s.length <= 105`
*   `s` contains only lowercase English letters.
*/

/**
 * @param {string} s
 * @return {string[]}
 */
class Seg {
    constructor(left = -1, right = -1) {
        this.left = left;
        this.right = right;
    }
}

var maxNumOfSubstrings = function (s) {
    const seg = Array.from({ length: 26 }, () => new Seg());

    // Preprocess the left and right endpoints.
    for (let i = 0; i < s.length; i++) {
        const charIdx = s.charCodeAt(i) - "a".charCodeAt(0);

        if (seg[charIdx].left === -1) {
            seg[charIdx].left = seg[charIdx].right = i;
        } else {
            seg[charIdx].right = i;
        }
    }

    for (let i = 0; i < 26; i++) {
        if (seg[i].left !== -1) {
            let j = seg[i].left;

            while (j <= seg[i].right) {
                const charIdx = s.charCodeAt(j) - "a".charCodeAt(0);

                if (
                    seg[i].left <= seg[charIdx].left &&
                    seg[charIdx].right <= seg[i].right
                ) {
                } else {
                    seg[i].left = Math.min(seg[i].left, seg[charIdx].left);
                    seg[i].right = Math.max(seg[i].right, seg[charIdx].right);
                    j = seg[i].left;
                }

                j++;
            }
        }
    }

    // Greedily select intervals.
    seg.sort((a, b) => {
        if (a.right === b.right) {
            return b.left - a.left;
        }
        return a.right - b.right;
    });

    const ans = [];
    let end = -1;

    for (const segment of seg) {
        const { left, right } = segment;

        if (left === -1) {
            continue;
        }

        if (end === -1 || left > end) {
            end = right;
            ans.push(s.slice(left, right + 1));
        }
    }

    return ans;
};
