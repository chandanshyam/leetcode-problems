/**
 * @param {string} s
 * @return {string[]}
 */
var findRepeatedDnaSequences = function(s) {
    const seen = new Set();
    const duplicates = new Set();
    
    // Loop through the string, stopping when fewer than 10 characters remain
    for (let i = 0; i <= s.length - 10; i++) {
        // Extract a 10-letter sequence using slice
        const sequence = s.slice(i, i + 10);
        
        // If we have seen it before, add it to the duplicates set
        if (seen.has(sequence)) {
            duplicates.add(sequence);
        } else {
            // Otherwise, mark it as seen
            seen.add(sequence);
        }
    }
    
    // Convert the duplicates set back into a string array
    return Array.from(duplicates);
}
