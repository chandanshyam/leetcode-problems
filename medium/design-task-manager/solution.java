/*
3408. Design Task Manager   [Medium]
https://leetcode.com/problems/design-task-manager/

Runtime: 280 ms   Memory: 174.4 MB

There is a task management system that allows users to manage their tasks, each associated with a priority. The system should efficiently handle adding, modifying, executing, and removing tasks.

Implement the `TaskManager` class:

*   `TaskManager(vector<vector<int>>& tasks)` initializes the task manager with a list of user-task-priority triples. Each element in the input list is of the form `[userId, taskId, priority]`, which adds a task to the specified user with the given priority.
    
*   `void add(int userId, int taskId, int priority)` adds a task with the specified `taskId` and `priority` to the user with `userId`. It is **guaranteed** that `taskId` does not _exist_ in the system.
    
*   `void edit(int taskId, int newPriority)` updates the priority of the existing `taskId` to `newPriority`. It is **guaranteed** that `taskId` _exists_ in the system.
    
*   `void rmv(int taskId)` removes the task identified by `taskId` from the system. It is **guaranteed** that `taskId` _exists_ in the system.
    
*   `int execTop()` executes the task with the **highest** priority across all users. If there are multiple tasks with the same **highest** priority, execute the one with the highest `taskId`. After executing, the `taskId` is **removed** from the system. Return the `userId` associated with the executed task. If no tasks are available, return -1.
    

**Note** that a user may be assigned multiple tasks.

**Example 1:**

**Input:**  
\["TaskManager", "add", "edit", "execTop", "rmv", "add", "execTop"\]  
\[\[\[\[1, 101, 10\], \[2, 102, 20\], \[3, 103, 15\]\]\], \[4, 104, 5\], \[102, 8\], \[\], \[101\], \[5, 105, 15\], \[\]\]

**Output:**  
\[null, null, null, 3, null, null, 5\]

**Explanation**

TaskManager taskManager = new TaskManager(\[\[1, 101, 10\], \[2, 102, 20\], \[3, 103, 15\]\]); // Initializes with three tasks for Users 1, 2, and 3.  
taskManager.add(4, 104, 5); // Adds task 104 with priority 5 for User 4.  
taskManager.edit(102, 8); // Updates priority of task 102 to 8.  
taskManager.execTop(); // return 3. Executes task 103 for User 3.  
taskManager.rmv(101); // Removes task 101 from the system.  
taskManager.add(5, 105, 15); // Adds task 105 with priority 15 for User 5.  
taskManager.execTop(); // return 5. Executes task 105 for User 5.

**Constraints:**

*   `1 <= tasks.length <= 105`
*   `0 <= userId <= 105`
*   `0 <= taskId <= 105`
*   `0 <= priority <= 109`
*   `0 <= newPriority <= 109`
*   At most `2 * 105` calls will be made in **total** to `add`, `edit`, `rmv`, and `execTop` methods.
*   The input is generated such that `taskId` will be valid.
*/

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
