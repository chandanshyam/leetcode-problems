/**
 * @param {string} s
 * @return {boolean}
 */
var checkValidString = function(s) {

    const st = [];
    const sc = [];
    for(let i=0;i<s.length;i++)
    {
        if(s[i] === '(')
        {
            st.push(i);
        }
       else  if(s[i] === '*')
        {
            sc.push(i);
        }
          else
        {
            if(st.length > 0)
            {
                st.pop();

            }
            else if(sc.length > 0)
            {
                sc.pop();
            }
            else{
                return false;
            }
        }}

        while(st.length >0 && sc.length > 0)
        {
            const left = st.pop();
            const star = sc.pop();

            if(star < left)
            {
                return false;
            }
        }


        return st.length === 0;
    
};
