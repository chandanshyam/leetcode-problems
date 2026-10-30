/*
3841. Palindromic Path Queries in a Tree   [Hard]
https://leetcode.com/problems/palindromic-path-queries-in-a-tree/

Runtime: 739 ms   Memory: 139.3 MB

You are given an undirected tree with `n` nodes labeled 0 to `n - 1`. This is represented by a 2D array `edges` of length `n - 1`, where `edges[i] = [ui, vi]` indicates an undirected edge between nodes `ui` and `vi`.

You are also given a string `s` of length `n` consisting of lowercase English letters, where `s[i]` represents the character assigned to node `i`.

You are also given a string array `queries`, where each `queries[i]` is either:

*   `"update ui c"`: Change the character at node `ui` to `c`. Formally, update `s[ui] = c`.
*   `"query ui vi"`: Determine whether the string formed by the characters on the **unique** path from `ui` to `vi` (inclusive) can be **rearranged** into a **palindrome**.

Return a boolean array `answer`, where `answer[j]` is `true` if the `jth` query of type `"query ui vi"​​​​​​​` can be rearranged into a **palindrome**, and `false` otherwise.

**Example 1:**

**Input:** n = 3, edges = \[\[0,1\],\[1,2\]\], s = "aac", queries = \["query 0 2","update 1 b","query 0 2"\]

**Output:** \[true,false\]

**Explanation:**

*   `"query 0 2"`: Path `0 → 1 → 2` gives `"aac"`, which can be rearranged to form `"aca"`, a palindrome. Thus, `answer[0] = true`.
*   `"update 1 b"`: Update node 1 to `'b'`, now `s = "abc"`.
*   `"query 0 2"`: Path characters are `"abc"`, which cannot be rearranged to form a palindrome. Thus, `answer[1] = false`.

Thus, `answer = [true, false]`.

**Example 2:**

**Input:** n = 4, edges = \[\[0,1\],\[0,2\],\[0,3\]\], s = "abca", queries = \["query 1 2","update 0 b","query 2 3","update 3 a","query 1 3"\]

**Output:** \[false,false,true\]

**Explanation:**

*   `"query 1 2"`: Path `1 → 0 → 2` gives `"bac"`, which cannot be rearranged to form a palindrome. Thus, `answer[0] = false`.
*   `"update 0 b"`: Update node 0 to `'b'`, now `s = "bbca"`.
*   `"query 2 3"`: Path `2 → 0 → 3` gives `"cba"`, which cannot be rearranged to form a palindrome. Thus, `answer[1] = false`.
*   `"update 3 a"`: Update node 3 to `'a'`, `s = "bbca"`.
*   `"query 1 3"`: Path `1 → 0 → 3` gives `"bba"`, which can be rearranged to form `"bab"`, a palindrome. Thus, `answer[2] = true`.

Thus, `answer = [false, false, true]`.

**Constraints:**

*   `1 <= n == s.length <= 5 * 104`
*   `edges.length == n - 1`
*   `edges[i] = [ui, vi]`
*   `0 <= ui, vi <= n - 1`
*   `s` consists of lowercase English letters.
*   The input is generated such that `edges` represents a valid tree.
*   `1 <= queries.length <= 5 * 104`​​​​​​​
    *   `queries[i] = "update ui c"` or
    *   `queries[i] = "query ui vi"`
    *   `0 <= ui, vi <= n - 1`
    *   `c` is a lowercase English letter.
*/

/**
 * @param {number} n
 * @param {number[][]} edges
 * @param {string} s
 * @param {string[]} queries
 * @return {boolean[]}
 */
     class BIT {
    constructor(n) {
        this.n = n;
        this.tree = new Array(n + 1).fill(0);
    }

    static lowbit(x) {
        return x & -x;
    }

    update(x, v) {
        while (x <= this.n) {
            this.tree[x] ^= v;
            x += BIT.lowbit(x);
        }
    }

    query(x) {
        let ret = 0;
        while (x > 0) {
            ret ^= this.tree[x];
            x -= BIT.lowbit(x);
        }
        return ret;
    }

    updateRange(l, r, v) {
        this.update(l, v);
        this.update(r + 1, v);
    }
}

var palindromePath = function (n, edges, s, queries) {
    const g = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }

    const LOG = 32 - Math.clz32(n);
    const fa = Array.from({ length: n }, () => new Array(LOG).fill(0));
    const dep = new Array(n).fill(0);
    const l = new Array(n).fill(0);
    const r = new Array(n).fill(0);
    let timer = 0;

    function dfs(u, p) {
        l[u] = ++timer;
        fa[u][0] = p;
        for (const v of g[u]) {
            if (v === p) {
                continue;
            }
            dep[v] = dep[u] + 1;
            dfs(v, u);
        }
        r[u] = timer;
    }
    dfs(0, 0);

    for (let j = 1; j < LOG; j++) {
        for (let i = 0; i < n; i++) {
            fa[i][j] = fa[fa[i][j - 1]][j - 1];
        }
    }

    function lca(u, v) {
        if (dep[u] < dep[v]) [u, v] = [v, u];
        const diff = dep[u] - dep[v];
        for (let i = 0; i < LOG; i++) {
            if ((diff >> i) & 1) {
                u = fa[u][i];
            }
        }
        if (u === v) return u;
        for (let i = LOG - 1; i >= 0; i--) {
            if (fa[u][i] !== fa[v][i]) {
                u = fa[u][i];
                v = fa[v][i];
            }
        }
        return fa[u][0];
    }

    const bit = new BIT(n + 1);
    const chars = s.split("");
    for (let i = 0; i < n; i++) {
        bit.updateRange(l[i], r[i], 1 << (chars[i].charCodeAt(0) - 97));
    }

    const ans = [];
    for (const q of queries) {
        const parts = q.split(" ");
        if (parts[0] === "update") {
            const u = parseInt(parts[1]);
            const c = parts[2];
            bit.updateRange(
                l[u],
                r[u],
                (1 << (chars[u].charCodeAt(0) - 97)) ^
                    (1 << (c.charCodeAt(0) - 97)),
            );
            chars[u] = c;
        } else {
            const u = parseInt(parts[1]);
            const v = parseInt(parts[2]);
            const w = lca(u, v);
            const mask =
                bit.query(l[u]) ^
                bit.query(l[v]) ^
                (1 << (chars[w].charCodeAt(0) - 97));
            let cnt = 0,
                m = mask;
            while (m) {
                cnt += m & 1;
                m >>>= 1;
            }
            ans.push(cnt <= 1);
        }
    }
    return ans;
};
