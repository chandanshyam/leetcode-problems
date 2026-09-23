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
