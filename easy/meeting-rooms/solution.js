/*
252. Meeting Rooms   [Easy]
https://leetcode.com/problems/meeting-rooms/

Runtime: 2 ms   Memory: 55.5 MB

You are given an array of meeting times `intervals` where `intervals[i] = [starti, endi]`.

A person can attend all meetings if no two meeting intervals overlap. Meetings ending at time `t` and starting at time `t` **do not** overlap.

​​​​​​​Return `true` if a person can attend all meetings. Otherwise, return `false`.

**Example 1:**

**Input:** intervals = \[\[0,30\],\[5,10\],\[15,20\]\]
**Output:** false

**Example 2:**

**Input:** intervals = \[\[7,10\],\[2,4\]\]
**Output:** true

**Constraints:**

*   `0 <= intervals.length <= 104`
*   `intervals[i].length == 2`
*   `0 <= starti < endi <= 106`
*/

/**
 * @param {number[][]} intervals
 * @return {boolean}
 */
var canAttendMeetings = function(intervals) {


    intervals.sort((a,b) => a[0] - b[0]);

    let curr = intervals[0]

    for(let i=1; i<intervals.length; i++)
    {
        let start = intervals[i][0];
        let end = intervals[i][1];

        if(curr[1] > start && curr[0] < end)
        {
            return false;
        }
        else{
            curr = intervals[i];
        }
    }
    return true;
};
