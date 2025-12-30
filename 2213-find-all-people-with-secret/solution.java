class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

        boolean[] knows_secret = new boolean[n];
        knows_secret[0] = true;
        knows_secret[firstPerson] = true;

        int m = meetings.length;
        int i = 0;

        while (i < m) {
            int curr_time = meetings[i][2];

            Map<Integer, List<Integer>> map = new HashMap<>();
            Set<Integer> set = new HashSet<>();

            while (i < m && meetings[i][2] == curr_time) {
                int x = meetings[i][0];
                int y = meetings[i][1];

                map.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
                map.computeIfAbsent(y, k -> new ArrayList<>()).add(x);

                set.add(x);
                set.add(y);

                i++;
            }

            Set<Integer> visited = new HashSet<>();
            Queue<Integer> q = new LinkedList<>();

            for (int p : set) {
                if (knows_secret[p] && !visited.contains(p)) {
                    q.offer(p);
                    visited.add(p);
                }
            } 

            while (!q.isEmpty()) {
                int curr = q.poll();

                if (!map.containsKey(curr)) continue;

                for (int ng : map.get(curr)) {
                    if (!visited.contains(ng)) {
                        visited.add(ng);
                        knows_secret[ng] = true;
                        q.offer(ng);
                    }
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            if (knows_secret[p]) {
                res.add(p);
            }
        }

        return res;
    }
}
