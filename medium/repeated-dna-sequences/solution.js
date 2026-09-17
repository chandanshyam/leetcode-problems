/*
187. Repeated DNA Sequences   [Medium]
https://leetcode.com/problems/repeated-dna-sequences/

Runtime: 642 ms   Memory: 90.9 MB

The **DNA sequence** is composed of a series of nucleotides abbreviated as `'A'`, `'C'`, `'G'`, and `'T'`.

*   For example, `"ACGAATTCCG"` is a **DNA sequence**.

When studying **DNA**, it is useful to identify repeated sequences within the DNA.

Given a string `s` that represents a **DNA sequence**, return all the **`10`\-letter-long** sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in **any order**.

**Example 1:**

**Input:** s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
**Output:** \["AAAAACCCCC","CCCCCAAAAA"\]

**Example 2:**

**Input:** s = "AAAAAAAAAAAAA"
**Output:** \["AAAAAAAAAA"\]

**Constraints:**

*   `1 <= s.length <= 105`
*   `s[i]` is either `'A'`, `'C'`, `'G'`, or `'T'`.
*/

/**
 * @param {string} s
 * @return {string[]}
 */
var findRepeatedDnaSequences = function(s) {
    const seen = new Set();
    const duplicates = new Set();
    
    // Loop through the string, stopping when fewer than 10 characters remain
    for (let i = 0; i <= s.length - 10; i++) {
        // Extract a 10-letter sequence using slice
        const sequence = s.slice(i, i + 10);
        
        // If we have seen it before, add it to the duplicates set
        if (seen.has(sequence)) {
            duplicates.add(sequence);
        } else {
            // Otherwise, mark it as seen
            seen.add(sequence);
        }
    }
    
    // Convert the duplicates set back into a string array
    return Array.from(duplicates);
}
