/**
 * @param {string} s
 * @return {number}
 */
var romanToInt = function(s) {

    const symbol_to_value = {
    "I": 1,
    "V": 5,
    "X": 10,
    "L": 50,
    "C": 100,
    "D": 500,
    "M": 1000
  };

    let total=0
    let i =0

    while( i < s.length)
    {
        curr_letter = s[i]
        if(i+1 < s.length)
    {
        next_letter= s[i+1]
         if(symbol_to_value[curr_letter] <  symbol_to_value[next_letter])
        {

            total+= symbol_to_value[next_letter] - symbol_to_value[curr_letter]
            i = i+2;            
        }
        else{
            total += symbol_to_value[curr_letter]
            i=i+1;
        }
    }
    else{
        total += symbol_to_value[curr_letter]
        i=i+1;
    }
    }
return total;
    
};
