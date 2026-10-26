/*
2409. Count Days Spent Together   [Easy]
https://leetcode.com/problems/count-days-spent-together/

Runtime: 0 ms   Memory: 54.3 MB

Alice and Bob are traveling to Rome for separate business meetings.

You are given 4 strings `arriveAlice`, `leaveAlice`, `arriveBob`, and `leaveBob`. Alice will be in the city from the dates `arriveAlice` to `leaveAlice` (**inclusive**), while Bob will be in the city from the dates `arriveBob` to `leaveBob` (**inclusive**). Each will be a 5-character string in the format `"MM-DD"`, corresponding to the month and day of the date.

Return _the total number of days that Alice and Bob are in Rome together._

You can assume that all dates occur in the **same** calendar year, which is **not** a leap year. Note that the number of days per month can be represented as: `[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]`.

**Example 1:**

**Input:** arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
**Output:** 3
**Explanation:** Alice will be in Rome from August 15 to August 18. Bob will be in Rome from August 16 to August 19. They are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.

**Example 2:**

**Input:** arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
**Output:** 0
**Explanation:** There is no day when Alice and Bob are in Rome together, so we return 0.

**Constraints:**

*   All dates are provided in the format `"MM-DD"`.
*   Alice and Bob's arrival dates are **earlier than or equal to** their leaving dates.
*   The given dates are valid dates of a **non-leap** year.
*/

/**
 * @param {string} arriveAlice
 * @param {string} leaveAlice
 * @param {string} arriveBob
 * @param {string} leaveBob
 * @return {number}
 */
var countDaysTogether = function (
    arriveAlice,
    leaveAlice,
    arriveBob,
    leaveBob,
) {
    let datesOfMonths = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    let prefixSum = [0];
    for (let i = 0; i < datesOfMonths.length; i++) {
        prefixSum.push(prefixSum[i] + datesOfMonths[i]);
    }

    let arriveAliceDay = calculateDayOfYear(arriveAlice, prefixSum);
    let leaveAliceDay = calculateDayOfYear(leaveAlice, prefixSum);
    let arriveBobDay = calculateDayOfYear(arriveBob, prefixSum);
    let leaveBobDay = calculateDayOfYear(leaveBob, prefixSum);

    return Math.max(
        0,
        Math.min(leaveAliceDay, leaveBobDay) -
            Math.max(arriveAliceDay, arriveBobDay) +
            1,
    );
};

function calculateDayOfYear(day, prefixSum) {
    let month = parseInt(day.substring(0, 2));
    let date = parseInt(day.substring(3));
    return prefixSum[month - 1] + date;
}
