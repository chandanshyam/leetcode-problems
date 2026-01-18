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
