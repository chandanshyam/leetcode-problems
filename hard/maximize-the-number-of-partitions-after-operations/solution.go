/*
3003. Maximize the Number of Partitions After Operations   [Hard]
https://leetcode.com/problems/maximize-the-number-of-partitions-after-operations/

Runtime: 1 ms   Memory: 4.3 MB

You are given a string `s` and an integer `k`.

First, you are allowed to change **at most** **one** index in `s` to another lowercase English letter.

After that, do the following partitioning operation until `s` is **empty**:

*   Choose the **longest** **prefix** of `s` containing at most `k` **distinct** characters.
*   **Delete** the prefix from `s` and increase the number of partitions by one. The remaining characters (if any) in `s` maintain their initial order.

Return an integer denoting the **maximum** number of resulting partitions after the operations by optimally choosing at most one index to change.

**Example 1:**

**Input:** s = "accca", k = 2

**Output:** 3

**Explanation:**

The optimal way is to change `s[2]` to something other than a and c, for example, b. then it becomes `"acbca"`.

Then we perform the operations:

1.  The longest prefix containing at most 2 distinct characters is `"ac"`, we remove it and `s` becomes `"bca"`.
2.  Now The longest prefix containing at most 2 distinct characters is `"bc"`, so we remove it and `s` becomes `"a"`.
3.  Finally, we remove `"a"` and `s` becomes empty, so the procedure ends.

Doing the operations, the string is divided into 3 partitions, so the answer is 3.

**Example 2:**

**Input:** s = "aabaab", k = 3

**Output:** 1

**Explanation:**

Initially `s` contains 2 distinct characters, so whichever character we change, it will contain at most 3 distinct characters, so the longest prefix with at most 3 distinct characters would always be all of it, therefore the answer is 1.

**Example 3:**

**Input:** s = "xxyz", k = 1

**Output:** 4

**Explanation:**

The optimal way is to change `s[0]` or `s[1]` to something other than characters in `s`, for example, to change `s[0]` to `w`.

Then `s` becomes `"wxyz"`, which consists of 4 distinct characters, so as `k` is 1, it will divide into 4 partitions.

**Constraints:**

*   `1 <= s.length <= 104`
*   `s` consists only of lowercase English letters.
*   `1 <= k <= 26`
*/

var PREF, SUFF [10000]uint64

func pack(count int, fix, mask uint32) uint64 {
	return uint64(count)<<48 | uint64(fix)<<32 | uint64(mask)
}

func unpack(v uint64) (count int, fix, mask uint32) {
	return int(v >> 48), uint32(v >> 32) & 0xFFFF, uint32(v)
}

func maxPartitionsAfterOperations(s string, k int) int {
	if k == 26 {
		return 1
	}
	var uniqueletters uint32
	for i := range s {
		uniqueletters |= 1 << (s[i] - 'a')
	}
	if bits.OnesCount32(uniqueletters) < k {
		return 1
	}
	n := len(s)
	// prefix and suffix processing
	process := func(FIX *[10000]uint64, start, end, step int) {
		var fix, mask uint32
		var count int
		for i := start; i != end; i += step {
			c := s[i] - 'a'
			newbit := 1 &^ int(mask>>c)
			count += newbit
			bit := uint32(1 << c)
			mask |= bit
			if count > k {
				fix++
				count = 1
				mask = bit
			}
			FIX[i+step] = pack(count, fix, mask)
		}
	}
	process(&PREF, 0, n-1, 1)
	process(&SUFF, n-1, 0, -1)
    SUFF[n-1] = 0
	// calculate result
	var result uint32
	for i := range n {
		pcount, pref, pmask := unpack(PREF[i])
		scount, suff, smask := unpack(SUFF[i])
		val := pref + suff + 1
		count := bits.OnesCount32(pmask | smask)
		if pcount == k && scount == k && count < 26 {
			val += 2
		} else if count >= k {
			val += 1
		}
		result = max(result, val)
	}
	return int(result)
}
