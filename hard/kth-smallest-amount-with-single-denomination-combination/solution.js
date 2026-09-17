/*
3116. Kth Smallest Amount With Single Denomination Combination   [Hard]
https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/

Runtime: 4 ms   Memory: 58.5 MB

You are given an integer array `coins` representing coins of different denominations and an integer `k`.

You have an infinite number of coins of each denomination. However, you are **not allowed** to combine coins of different denominations.

Return the `kth` **smallest** amount that can be made using these coins.

**Example 1:**

**Input:** coins = \[3,6,9\], k = 3

**Output:** 9

**Explanation:** The given coins can make the following amounts:  
Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.  
Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.  
Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.  
All of the coins combined produce: 3, 6, **9**, 12, 15, etc.

**Example 2:**

**Input:** coins = \[5,2\], k = 7

**Output:** 12

**Explanation:** The given coins can make the following amounts:  
Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.  
Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.  
All of the coins combined produce: 2, 4, 5, 6, 8, 10, **12**, 14, 15, etc.

**Constraints:**

*   `1 <= coins.length <= 15`
*   `1 <= coins[i] <= 25`
*   `1 <= k <= 2 * 109`
*   `coins` contains pairwise distinct integers.
*/

var findKthSmallest = function (coins, k) {
    coins.sort((a, b) => a - b);
    const newCoins = [];
    for (const x of coins) {
        let flag = true;
        for (const y of newCoins) {
            if (x % y === 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            newCoins.push(x);
        }
    }
    coins = newCoins;

    const n = coins.length;
    const m = 1 << n;
    const lcm = new Array(m).fill(0n);
    let l = BigInt(k);
    let r = BigInt(coins[0]) * BigInt(k) + 1n;

    const gcd = (a, b) => {
        a = a < 0n ? -a : a;
        b = b < 0n ? -b : b;
        while (b !== 0n) {
            [a, b] = [b, a % b];
        }
        return a;
    };

    const trailingZeros = (x) => {
        let count = 0;
        while ((x & 1) === 0) {
            count++;
            x >>= 1;
        }
        return count;
    };

    const popcount = (x) => {
        let count = 0;
        while (x) {
            count += x & 1;
            x >>= 1;
        }
        return count;
    };

    lcm[0] = 1n;
    for (let mask = 1; mask < m; mask++) {
        const preMask = mask & (mask - 1);
        const i = trailingZeros(mask);

        const coin = BigInt(coins[i]);
        const tmp = lcm[preMask] / gcd(lcm[preMask], coin);
        if (tmp <= r / coin) {
            lcm[mask] = tmp * coin;
        } else {
            lcm[mask] = r + 1n;
        }
    }

    const count = (x) => {
        let res = 0n;
        for (let mask = 1; mask < m; mask++) {
            if (lcm[mask] > x) continue;

            if (popcount(mask) & 1) {
                res += x / lcm[mask];
            } else {
                res -= x / lcm[mask];
            }
        }
        return res;
    };

    while (l < r) {
        const mid = (l + r) / 2n;
        if (count(mid) >= k) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }
    return Number(l);
};
