/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
 /**
 we have to do a center left right approach
 where each 
  */
var maxPalindromes = function (s, k) {
    const n = s.length;
    let ans = 0,
        start = 0;

    const check = (l, r) => {
        while (l < r) {
            if (s[l++] !== s[r--]) {
                return false;
            }
        }
        return true;
    };

    for (let r = k - 1; r < n; ++r) {
        let l = r - k + 1;
        if (l >= start && check(l, r)) {
            ++ans;
            start = r + 1;
            continue;
        }

        l = r - k;
        if (l >= start && check(l, r)) {
            ++ans;
            start = r + 1;
        }
    }

    return ans;
};
