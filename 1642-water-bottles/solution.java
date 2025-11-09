class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {

        int totalB = numBottles;
        int currBottles =numBottles;
        while(currBottles >= numExchange)
        {
            int fullBottles  = currBottles / numExchange;
            int emptyBottles  = currBottles  % numExchange;
            currBottles = fullBottles + emptyBottles;
            totalB+= fullBottles;
        }

        return totalB;
    }
}
