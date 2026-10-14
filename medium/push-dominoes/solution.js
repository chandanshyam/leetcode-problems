/*
838. Push Dominoes   [Medium]
https://leetcode.com/problems/push-dominoes/

Runtime: 36 ms   Memory: 65.2 MB

There are `n` dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

You are given a string `dominoes` representing the initial state where:

*   `dominoes[i] = 'L'`, if the `ith` domino has been pushed to the left,
*   `dominoes[i] = 'R'`, if the `ith` domino has been pushed to the right, and
*   `dominoes[i] = '.'`, if the `ith` domino has not been pushed.

Return _a string representing the final state_.

**Example 1:**

**Input:** dominoes = "RR.L"
**Output:** "RR.L"
**Explanation:** The first domino expends no additional force on the second domino.

**Example 2:**

![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/05/18/domino.png)

**Input:** dominoes = ".L.R...LR..L.."
**Output:** "LL.RR.LLRRLL.."

**Constraints:**

*   `n == dominoes.length`
*   `1 <= n <= 105`
*   `dominoes[i]` is either `'L'`, `'R'`, or `'.'`.
*/

/**
 * @param {string} dominoes
 * @return {string}
 */
var pushDominoes = function(dominoes) {

    const s = 'L' + dominoes + 'R';   // sentinels so edges behave naturally
  const rows = s.split('');
    let n = s.length;
    
    let a =0;

    for(let i=1;i<n;i++)
    {
        if(s[i] === '.') continue;

        const  A = s[a];
        const B = s[i];

        if(A===B)
        {
            for(let j=a+1;j<i;j++)
            {
                rows[j] = A;
            }

        }
        else if(A==='R' && B==='L')
        {
            let lo = a+1 , hi = i-1
            while(lo < hi)
            {
                rows[lo] = A;
                rows[hi] = B;
                lo++;
                hi--;
            }
        }

        a=i;
    }

     return rows.slice(1, -1).join('');

    
};
