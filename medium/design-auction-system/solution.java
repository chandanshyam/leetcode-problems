/*
3815. Design Auction System   [Medium]
https://leetcode.com/problems/design-auction-system/

Runtime: 83 ms   Memory: 176 MB

You are asked to design an auction system that manages bids from multiple users in real time.

Each bid is associated with a `userId`, an `itemId`, and a `bidAmount`.

Implement the `AuctionSystem` class:​​​​​​​

*   `AuctionSystem()`: Initializes the `AuctionSystem` object.
*   `void addBid(int userId, int itemId, int bidAmount)`: Adds a new bid for `itemId` by `userId` with `bidAmount`. If the same `userId` **already** has a bid on `itemId`, **replace** it with the new `bidAmount`.
*   `void updateBid(int userId, int itemId, int newAmount)`: Updates the existing bid of `userId` for `itemId` to `newAmount`. It is **guaranteed** that this bid _exists_.
*   `void removeBid(int userId, int itemId)`: Removes the bid of `userId` for `itemId`. It is **guaranteed** that this bid _exists_.
*   `int getHighestBidder(int itemId)`: Returns the `userId` of the **highest** bidder for `itemId`. If multiple users have the **same highest** `bidAmount`, return the user with the **highest** `userId`. If no bids exist for the item, return -1.

**Example 1:**

**Input:**  
\["AuctionSystem", "addBid", "addBid", "getHighestBidder", "updateBid", "getHighestBidder", "removeBid", "getHighestBidder", "getHighestBidder"\]  
\[\[\], \[1, 7, 5\], \[2, 7, 6\], \[7\], \[1, 7, 8\], \[7\], \[2, 7\], \[7\], \[3\]\]

**Output:**  
\[null, null, null, 2, null, 1, null, 1, -1\]

**Explanation**

AuctionSystem auctionSystem = new AuctionSystem(); // Initialize the Auction system  
auctionSystem.addBid(1, 7, 5); // User 1 bids 5 on item 7  
auctionSystem.addBid(2, 7, 6); // User 2 bids 6 on item 7  
auctionSystem.getHighestBidder(7); // return 2 as User 2 has the highest bid  
auctionSystem.updateBid(1, 7, 8); // User 1 updates bid to 8 on item 7  
auctionSystem.getHighestBidder(7); // return 1 as User 1 now has the highest bid  
auctionSystem.removeBid(2, 7); // Remove User 2's bid on item 7  
auctionSystem.getHighestBidder(7); // return 1 as User 1 is the current highest bidder  
auctionSystem.getHighestBidder(3); // return -1 as no bids exist for item 3

**Constraints:**

*   `1 <= userId, itemId <= 5 * 104`
*   `1 <= bidAmount, newAmount <= 109`
*   At most `5 * 104` total calls to `addBid`, `updateBid`, `removeBid`, and `getHighestBidder`.
*   The input is generated such that for `updateBid` and `removeBid`, the bid from the given `userId` for the given `itemId` will be valid.
*/

class AuctionSystem {

    private static class Bid {
        int userId;
        int amount;
        Bid(int u, int a) {userId = u; amount = a;
                          }
    }

    private static class ItemData {
        HashMap<Integer, Integer> cur = new HashMap<>();

        PriorityQueue<Bid> pq = new PriorityQueue<>(
            (a,b) -> (a.amount != b.amount) ? Integer.compare(b.amount, a.amount)
        : Integer.compare(b.userId, a.userId));
        
    }

    private final HashMap<Integer, ItemData> items = new HashMap<>();

    

    public AuctionSystem() {
        
    }
    
    public void addBid(int userId, int itemId, int bidAmount) {
        ItemData d = items.computeIfAbsent(itemId, k -> new ItemData());
        d.cur.put(userId, bidAmount);
        d.pq.offer(new Bid(userId, bidAmount));
        
    }
    
    public void updateBid(int userId, int itemId, int newAmount) {
        ItemData d = items.get(itemId);
        d.cur.put(userId, newAmount);
        d.pq.offer(new Bid(userId, newAmount));      
    }
    
    public void removeBid(int userId, int itemId) {
        ItemData d = items.get(itemId);
        d.cur.remove(userId);
    }
    
    public int getHighestBidder(int itemId) {
        ItemData d = items.get(itemId);
        if(d == null)  return -1;
         while(!d.pq.isEmpty())
             {
                 Bid top = d.pq.peek();
                 Integer curAmt = d.cur.get(top.userId);

                 if (curAmt != null && curAmt == top.amount)
                 {
                     return top.userId;
                 }
                 d.pq.poll();
             }
        return -1;
    }
}

/**
 * Your AuctionSystem object will be instantiated and called as such:
 * AuctionSystem obj = new AuctionSystem();
 * obj.addBid(userId,itemId,bidAmount);
 * obj.updateBid(userId,itemId,newAmount);
 * obj.removeBid(userId,itemId);
 * int param_4 = obj.getHighestBidder(itemId);
 */
