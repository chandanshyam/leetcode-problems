/**
 * @param {string} s
 * @return {string}
 */
var longestPalindrome = function(s) {
    let start =0;
    let end =0;
    for(let i =0; i < s.length ; i++)
    {
        for(let j=1;j<=2;j++)
        {
            let l = i;
            let r = i+j-1;

             while(l >= 0 && r < s.length && s[l] === s[r])
             {
                l--;
                r++;
             }

             if(r-l-1 > end - start)
             {
                start= l+1;
                end=r-1;
                
             }
             
        }
    }

    return s.substring(start, end+1);

};

