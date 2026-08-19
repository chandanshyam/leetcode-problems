/**
 * @param {number[]} frequencies
 * @return {number}
 */
var minOperations = function(frequencies) {

  const n = frequencies.length;

  // Sort and remove duplicates to get unique frequencies
  const uniqueFreqs = [...new Set(frequencies)].sort((a, b) => a - b);

  let maxValid = 0;
  let j = 0;  // Right pointer for sliding window

  // For each unique frequency as potential window start
  for (let i = 0; i < uniqueFreqs.length; i++) {
    const start = uniqueFreqs[i];
    // Valid window spans [start, start + n - 1]
    const end = start + n - 1;

    // Expand right pointer while frequencies fit in window
    while (j < uniqueFreqs.length && uniqueFreqs[j] <= end) {
      j++;
    }

    // Count frequencies in current window
    const count = j - i;
    maxValid = Math.max(maxValid, count);
  }

  // Minimum operations = total transmitters - max already valid
  return n - maxValid;
    
};
