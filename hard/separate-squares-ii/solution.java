/*
3454. Separate Squares II   [Hard]
https://leetcode.com/problems/separate-squares-ii/

Runtime: 692 ms   Memory: 103.7 MB

You are given a 2D integer array `squares`. Each `squares[i] = [xi, yi, li]` represents the coordinates of the bottom-left point and the side length of a square parallel to the x-axis.

Find the **minimum** y-coordinate value of a horizontal line such that the total area covered by squares above the line _equals_ the total area covered by squares below the line.

Answers within `10-5` of the actual answer will be accepted.

**Note**: Squares **may** overlap. Overlapping areas should be counted **only once** in this version.

**Example 1:**

**Input:** squares = \[\[0,0,1\],\[2,2,1\]\]

**Output:** 1.00000

**Explanation:**

![](https://assets.leetcode.com/uploads/2025/01/15/4065example1drawio.png)

Any horizontal line between `y = 1` and `y = 2` results in an equal split, with 1 square unit above and 1 square unit below. The minimum y-value is 1.

**Example 2:**

**Input:** squares = \[\[0,0,2\],\[1,1,1\]\]

**Output:** 1.00000

**Explanation:**

![](https://assets.leetcode.com/uploads/2025/01/15/4065example2drawio.png)

Since the blue square overlaps with the red square, it will not be counted again. Thus, the line `y = 1` splits the squares into two equal parts.

**Constraints:**

*   `1 <= squares.length <= 5 * 104`
*   `squares[i] = [xi, yi, li]`
*   `squares[i].length == 3`
*   `0 <= xi, yi <= 109`
*   `1 <= li <= 109`
*   The total area of all the squares will not exceed `1015`.
*/

import java.util.*;

class Solution {
    // Helper class to represent active X-intervals
    private static class Interval implements Comparable<Interval> {
        int start, end;
        
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        // Needed for sort
        public int compareTo(Interval other) {
            if (this.start != other.start) return Integer.compare(this.start, other.start);
            return Integer.compare(this.end, other.end);
        }

        // Needed for removing specific objects from ArrayList
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return start == interval.start && end == interval.end;
        }
    }

    // Helper class for Sweep Line events
    private static class Event implements Comparable<Event> {
        int y;
        int type; // 1 for start, -1 for end
        int xStart, xEnd;

        Event(int y, int type, int xStart, int xEnd) {
            this.y = y;
            this.type = type;
            this.xStart = xStart;
            this.xEnd = xEnd;
        }

        public int compareTo(Event other) {
            return Integer.compare(this.y, other.y);
        }
    }

    public double separateSquares(int[][] squares) {
        List<Event> sweepEvents = new ArrayList<>();
        for (int[] sq : squares) {
            int x = sq[0];
            int y = sq[1];
            int l = sq[2];
            sweepEvents.add(new Event(y, 1, x, x + l));
            sweepEvents.add(new Event(y + l, -1, x, x + l));
        }

        Collections.sort(sweepEvents);

        List<Interval> activeIntervals = new ArrayList<>();
        // Store strips as: [y_bottom, height, union_width]
        List<double[]> processedStrips = new ArrayList<>();
        
        double totalArea = 0;
        int prevY = sweepEvents.get(0).y;

        for (Event event : sweepEvents) {
            // Process the gap (strip) between the previous event and this one
            if (event.y > prevY) {
                double unionWidth = getUnionWidth(activeIntervals);
                double height = (double) event.y - prevY;
                
                if (unionWidth > 0) {
                    processedStrips.add(new double[]{prevY, height, unionWidth});
                    totalArea += height * unionWidth;
                }
            }

            // Update active intervals list
            Interval currentInterval = new Interval(event.xStart, event.xEnd);
            if (event.type == 1) {
                activeIntervals.add(currentInterval);
            } else {
                activeIntervals.remove(currentInterval);
            }
            
            prevY = event.y;
        }

        // Second Pass: Find the split point
        double targetArea = totalArea / 2.0;
        double accumulatedArea = 0;

        for (double[] strip : processedStrips) {
            double bottomY = strip[0];
            double height = strip[1];
            double width = strip[2];
            double stripArea = height * width;

            if (accumulatedArea + stripArea >= targetArea) {
                double missingArea = targetArea - accumulatedArea;
                return bottomY + (missingArea / width);
            }
            accumulatedArea += stripArea;
        }

        return 0.0;
    }

    // Brute force union width calculation: O(K log K) where K is active squares
    private double getUnionWidth(List<Interval> intervals) {
        if (intervals.isEmpty()) return 0;

        // Create a copy to sort, so we don't mess up the main list order unnecessarily
        List<Interval> sorted = new ArrayList<>(intervals);
        Collections.sort(sorted);

        double unionLength = 0;
        double currentEnd = -1e18; // Negative infinity

        for (Interval iv : sorted) {
            if (iv.start >= currentEnd) {
                // Disjoint interval
                unionLength += (iv.end - iv.start);
                currentEnd = iv.end;
            } else if (iv.end > currentEnd) {
                // Overlapping interval
                unionLength += (iv.end - currentEnd);
                currentEnd = iv.end;
            }
        }
        return unionLength;
    }
}
