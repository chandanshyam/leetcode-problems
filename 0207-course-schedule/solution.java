class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //you have to check if the graph is circular 
        //to do that you build a adjacency List 

        //you add the paths from each course to a map 
        ///whose value is a list 
        //for a particular key in the list 
        //if map.contains(map.get(course));
        // and pre = map.get(course)
        //if pre == course;

        Map<Integer, List<Integer>> preMap = new HashMap<>();

        for(int i=0;i<numCourses;i++)
        {
            preMap.put(i, new ArrayList<>());
        }


        for(int[] pre : prerequisites)
        {
            int course = pre[0];
            int prerequisite = pre[1];
            preMap.get(course).add(prerequisite);
        }

        Set<Integer> visiting = new HashSet<>();

        for(int i=0;i<numCourses;i++)
        {
            if(!dfs(preMap,i, visiting))
            {
                return false;
            }
        }

return true;

    }

    private  boolean dfs(Map<Integer, List<Integer>> map, int course, Set<Integer> visit)
    {
        if(visit.contains(course))
        {
            return false;//circle detected
        }

        if(map.get(course).isEmpty()) return true;

        visit.add(course);




        for(int pre: map.get(course))
        {
            if(!dfs(map, pre, visit)) return false;
        }

        visit.remove(course);
        map.put(course, new ArrayList<>());
        return true;

    }
}

