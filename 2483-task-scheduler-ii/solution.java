class Solution {
    public long taskSchedulerII(final int[] tasks, final int space) {
        final Map<Integer, Long> next = new HashMap<>();

        long days = 0L;

        for(final int task : tasks) {
            days = Math.max(next.getOrDefault(task, 1L), days + 1);
            next.put(task, days + space + 1);
        }

        return days;
    }
}
