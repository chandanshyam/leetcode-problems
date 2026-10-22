/*
3863. Minimum Operations to Sort a String   [Medium]
https://leetcode.com/problems/minimum-operations-to-sort-a-string/

Runtime: 43 ms   Memory: 62.4 MB

You are given a string `s` consisting of lowercase English letters.

In one operation, you can select any **substring** of `s` that is **not** the entire string and **sort** it in **non-descending alphabetical** order.

Return the **minimum** number of operations required to make `s` sorted in **non-descending** order. If it is not possible, return -1.

**Example 1:**

**Input:** s = "dog"

**Output:** 1

**Explanation:**​​​​​​​

*   Sort substring `"og"` to `"go"`.
*   Now, `s = "dgo"`, which is sorted in ascending order. Thus, the answer is 1.

**Example 2:**

**Input:** s = "card"

**Output:** 2

**Explanation:**

*   Sort substring `"car"` to `"acr"`, so `s = "acrd"`.
*   Sort substring `"rd"` to `"dr"`, making `s = "acdr"`, which is sorted in ascending order. Thus, the answer is 2.

**Example 3:**

**Input:** s = "gf"

**Output:** \-1

**Explanation:**

*   It is impossible to sort `s` under the given constraints. Thus, the answer is -1.

**Constraints:**

*   `1 <= s.length <= 105`
*   `s` consists of only lowercase English letters.
*/

/**
 * @param {string} s
 * @return {number}
 */
const minOperations = s => {
    const n = s.length;
    let min = s[0], max = s[0], isSorted = true;
    
    for (let i = 1; i < n; i++) {
        if (s[i] < s[i - 1]) isSorted = false;
        if (s[i] < min) min = s[i];
        if (s[i] > max) max = s[i];
    }

    if (isSorted) return 0;
    if (n === 2) return -1;
    if (s[0] === min || s[n - 1] === max) return 1;
    
    for (let i = 1; i < n - 1; i++) 
        if (s[i] === min || s[i] === max) return 2;    

    return 3;
};
