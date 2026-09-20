/**
 * @param {string} s
 * @return {number}
 */
var reverseDegree = function(str) {

    let sum = 0;

for(let i=0;i<str.length;i++)
{
    const char = str[i];
    sum += (26 - (char.charCodeAt(0) - 97)) * (i+1);
}
    
    return sum;

};
