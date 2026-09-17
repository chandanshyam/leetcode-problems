/*
2296. Design a Text Editor   [Hard]
https://leetcode.com/problems/design-a-text-editor/

Runtime: 175 ms   Memory: 104.6 MB

Design a text editor with a cursor that can do the following:

*   **Add** text to where the cursor is.
*   **Delete** text from where the cursor is (simulating the backspace key).
*   **Move** the cursor either left or right.

When deleting text, only characters to the left of the cursor will be deleted. The cursor will also remain within the actual text and cannot be moved beyond it. More formally, we have that `0 <= cursor.position <= currentText.length` always holds.

Implement the `TextEditor` class:

*   `TextEditor()` Initializes the object with empty text.
*   `void addText(string text)` Appends `text` to where the cursor is. The cursor ends to the right of `text`.
*   `int deleteText(int k)` Deletes `k` characters to the left of the cursor. Returns the number of characters actually deleted.
*   `string cursorLeft(int k)` Moves the cursor to the left `k` times. Returns the last `min(10, len)` characters to the left of the cursor, where `len` is the number of characters to the left of the cursor.
*   `string cursorRight(int k)` Moves the cursor to the right `k` times. Returns the last `min(10, len)` characters to the left of the cursor, where `len` is the number of characters to the left of the cursor.

**Example 1:**

**Input**
\["TextEditor", "addText", "deleteText", "addText", "cursorRight", "cursorLeft", "deleteText", "cursorLeft", "cursorRight"\]
\[\[\], \["leetcode"\], \[4\], \["practice"\], \[3\], \[8\], \[10\], \[2\], \[6\]\]
**Output**
\[null, null, 4, null, "etpractice", "leet", 4, "", "practi"\]

**Explanation**
TextEditor textEditor = new TextEditor(); // The current text is "|". (The '|' character represents the cursor)
textEditor.addText("leetcode"); // The current text is "leetcode|".
textEditor.deleteText(4); // return 4
                          // The current text is "leet|". 
                          // 4 characters were deleted.
textEditor.addText("practice"); // The current text is "leetpractice|". 
textEditor.cursorRight(3); // return "etpractice"
                           // The current text is "leetpractice|". 
                           // The cursor cannot be moved beyond the actual text and thus did not move.
                           // "etpractice" is the last 10 characters to the left of the cursor.
textEditor.cursorLeft(8); // return "leet"
                          // The current text is "leet|practice".
                          // "leet" is the last min(10, 4) = 4 characters to the left of the cursor.
textEditor.deleteText(10); // return 4
                           // The current text is "|practice".
                           // Only 4 characters were deleted.
textEditor.cursorLeft(2); // return ""
                          // The current text is "|practice".
                          // The cursor cannot be moved beyond the actual text and thus did not move. 
                          // "" is the last min(10, 0) = 0 characters to the left of the cursor.
textEditor.cursorRight(6); // return "practi"
                           // The current text is "practi|ce".
                           // "practi" is the last min(10, 6) = 6 characters to the left of the cursor.

**Constraints:**

*   `1 <= text.length, k <= 40`
*   `text` consists of lowercase English letters.
*   At most `2 * 104` calls **in total** will be made to `addText`, `deleteText`, `cursorLeft` and `cursorRight`.

**Follow-up:** Could you find a solution with time complexity of `O(k)` per call?
*/

// class Node
// {
//     constructor(val=null, next=null, prev=null)
//     {
//         this.val = val;
//         this.next = next;
//         this.prev = prev;
//     }
// }


var TextEditor = function() {
    this.left = ''
    this.right = ''
    
};

/** 
 * @param {string} text
 * @return {void}
 */
TextEditor.prototype.addText = function(text) {
    this.left += text;
};

/** 
 * @param {number} k
 * @return {number}
 */
TextEditor.prototype.deleteText = function(k) {
    k= Math.min(k, this.left.length);

    const deletedText = this.left.slice(-k);
    this.left = this.left.slice(0, -k);

    return deletedText.length;
};

/** 
 * @param {number} k
 * @return {string}
 */
TextEditor.prototype.cursorLeft = function(k) {
    k = Math.min(k, this.left.length);

    const cutted = this.left.slice(-k);
    this.right = cutted + this.right;
    this.left = this.left.slice(0, -k);

    return this.left.slice(-10);
    
};

/** 
 * @param {number} k
 * @return {string}
 */
TextEditor.prototype.cursorRight = function(k) {
    k = Math.min(k, this.right.length);

    const cutted = this.right.slice(0, k);
    this.left += cutted;
    this.right = this.right.slice(k);

    return this.left.slice(-10);
    
};

/** 
 * Your TextEditor object will be instantiated and called as such:
 * var obj = new TextEditor()
 * obj.addText(text)
 * var param_2 = obj.deleteText(k)
 * var param_3 = obj.cursorLeft(k)
 * var param_4 = obj.cursorRight(k)
 */
