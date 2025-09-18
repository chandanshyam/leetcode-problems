import java.util.*;

class TaskManager {

    private static class Node {
        int priority, taskId, userId;
        Node(int p, int t, int u) { priority = p; taskId = t; userId = u; }
    }

    private static class Info {
        int userId, priority;
        Info(int u, int p) { userId = u; priority = p; }
    }

    // Max-heap: higher priority first; if tie, higher taskId first
    private final PriorityQueue<Node> pq = new PriorityQueue<>(
        (a, b) -> {
            if (a.priority != b.priority) return Integer.compare(b.priority, a.priority);
            return Integer.compare(b.taskId, a.taskId);
        }
    );

    // Source of truth: taskId -> (userId, priority)
    private final Map<Integer, Info> map = new HashMap<>();

    public TaskManager(List<List<Integer>> tasks) {
        if (tasks != null) {
            for (List<Integer> t : tasks) {
                int userId = t.get(0), taskId = t.get(1), priority = t.get(2);
                map.put(taskId, new Info(userId, priority));
                pq.offer(new Node(priority, taskId, userId));
            }
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        map.put(taskId, new Info(userId, priority));
        pq.offer(new Node(priority, taskId, userId));
    }
    
    public void edit(int taskId, int newPriority) {
        Info info = map.get(taskId); // guaranteed to exist
        info.priority = newPriority; // update truth
        pq.offer(new Node(newPriority, taskId, info.userId)); // old heap entry becomes stale
    }
    
    public void rmv(int taskId) {
        map.remove(taskId); // lazy delete: stale heap entries skipped later
    }
    
    public int execTop() {
        while (!pq.isEmpty()) {
            Node top = pq.peek();
            Info cur = map.get(top.taskId);
            if (cur == null) { // removed task -> stale
                pq.poll();
                continue;
            }
            if (cur.userId == top.userId && cur.priority == top.priority) {
                pq.poll();
                map.remove(top.taskId);
                return top.userId;
            }
            
            pq.poll();
        }
        return -1;
    }
}

