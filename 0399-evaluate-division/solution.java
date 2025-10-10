public class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair>> adj = new HashMap<>(); // Map a -> list of [b, a/b]

        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            adj.putIfAbsent(a, new ArrayList<>());
            adj.putIfAbsent(b, new ArrayList<>());
            adj.get(a).add(new Pair(b, values[i]));
            adj.get(b).add(new Pair(a, 1 / values[i]));
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String target = queries.get(i).get(1);
            res[i] = bfs(src, target, adj);
        }

        return res;
    }

    private double bfs(String src, String target, Map<String, List<Pair>> adj) {
        if (!adj.containsKey(src) || !adj.containsKey(target)) {
            return -1.0;
        }

        Queue<Pair> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(new Pair(src, 1.0));
        visited.add(src);

        while (!q.isEmpty()) {
            Pair current = q.poll();
            String node = current.node;
            double weight = current.weight;

            if (node.equals(target)) {
                return weight;
            }

            for (Pair neighbor : adj.get(node)) {
                if (!visited.contains(neighbor.node)) {
                    visited.add(neighbor.node);
                    q.offer(new Pair(neighbor.node, weight * neighbor.weight));
                }
            }
        }

        return -1.0;
    }

    class Pair {
        String node;
        double weight;

        Pair(String node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
