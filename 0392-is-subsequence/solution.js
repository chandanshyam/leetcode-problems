/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isSubsequence = function(s, t) {

    let l=0;
    let r=0;
    let lb=s.length;
    let rb = t.length;

    while(l < lb && r< rb)
    {   
        if(s[l] === t[r])
        {
            l++;
        }
        r++;
    }

return l ===lb;

};
