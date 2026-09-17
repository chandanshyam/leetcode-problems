/*
721. Accounts Merge   [Medium]
https://leetcode.com/problems/accounts-merge/

Runtime: 29 ms   Memory: 51.8 MB

Given a list of `accounts` where each element `accounts[i]` is a list of strings, where the first element `accounts[i][0]` is a name, and the rest of the elements are **emails** representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails **in sorted order**. The accounts themselves can be returned in **any order**.

**Example 1:**

**Input:** accounts = \[\["John","johnsmith@mail.com","john\_newyork@mail.com"\],\["John","johnsmith@mail.com","john00@mail.com"\],\["Mary","mary@mail.com"\],\["John","johnnybravo@mail.com"\]\]
**Output:** \[\["John","john00@mail.com","john\_newyork@mail.com","johnsmith@mail.com"\],\["Mary","mary@mail.com"\],\["John","johnnybravo@mail.com"\]\]
**Explanation:**
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer \[\['Mary', 'mary@mail.com'\], \['John', 'johnnybravo@mail.com'\], 
\['John', 'john00@mail.com', 'john\_newyork@mail.com', 'johnsmith@mail.com'\]\] would still be accepted.

**Example 2:**

**Input:** accounts = \[\["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"\],\["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"\],\["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"\],\["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"\],\["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"\]\]
**Output:** \[\["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"\],\["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"\],\["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"\],\["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"\],\["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"\]\]

**Constraints:**

*   `1 <= accounts.length <= 1000`
*   `2 <= accounts[i].length <= 10`
*   `1 <= accounts[i][j].length <= 30`
*   `accounts[i][0]` consists of English letters.
*   `accounts[i][j] (for j > 0)` is a valid email.
*/

public class Solution {
    private Map<String, Integer> emailIdx = new HashMap<>(); // email -> id
    private List<String> emails = new ArrayList<>(); // set of emails of all accounts
    private Map<Integer, Integer> emailToAcc = new HashMap<>(); // email_index -> account_Id
    private List<List<Integer>> adj;
    private Map<Integer, List<String>> emailGroup = new HashMap<>(); // index of acc -> list of emails
    private boolean[] visited;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        int m = 0;

        // Build email index and mappings
        for (int accId = 0; accId < n; accId++) {
            List<String> account = accounts.get(accId);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!emailIdx.containsKey(email)) {
                    emails.add(email);
                    emailIdx.put(email, m);
                    emailToAcc.put(m, accId);
                    m++;
                }
            }
        }

        // Build adjacency list
        adj = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            adj.add(new ArrayList<>());
        }
        for (List<String> account : accounts) {
            for (int i = 2; i < account.size(); i++) {
                int id1 = emailIdx.get(account.get(i));
                int id2 = emailIdx.get(account.get(i - 1));
                adj.get(id1).add(id2);
                adj.get(id2).add(id1);
            }
        }

        // Initialize visited array
        visited = new boolean[m];

        // DFS traversal
        for (int i = 0; i < m; i++) {
            if (!visited[i]) {
                int accId = emailToAcc.get(i);
                emailGroup.putIfAbsent(accId, new ArrayList<>());
                dfs(i, accId);
            }
        }

        // Build result
        List<List<String>> res = new ArrayList<>();
        for (int accId : emailGroup.keySet()) {
            List<String> group = emailGroup.get(accId);
            Collections.sort(group);
            List<String> merged = new ArrayList<>();
            merged.add(accounts.get(accId).get(0));
            merged.addAll(group);
            res.add(merged);
        }

        return res;
    }

    private void dfs(int node, int accId) {
        visited[node] = true;
        emailGroup.get(accId).add(emails.get(node));
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, accId);
            }
        }
    }
}
