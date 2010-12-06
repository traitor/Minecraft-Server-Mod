import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayedTask - Lets you add a task to a j.u.concurrent.DelayQueue
 * 
 * @author Zeerix
 */
public class DelayedTask implements Runnable, Delayed {

    private final Runnable task;
    private final long endOfDelay;

    /**
     * Wraps a Runnable task so you can put it into a DelayQueue
     * 
     * @param task the task that needs to be run after the delay
     * @param delayMillis the delay in milliseconds
     */
    DelayedTask(Runnable task, long delayMillis) {
        this.task = task;
        this.endOfDelay = System.currentTimeMillis() + delayMillis;
    }

    /**
     * Runs the embedded task
     */
    @Override
    public void run() {
        task.run();
    }

    /**
     * Returns how long this task needs to be delayed
     * 
     * @param unit the TimeUnit of the result
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(endOfDelay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * Compares order of two DelayedTasks
     * 
     * @param delayed the other object to compare to
     */
    @Override
    public int compareTo(Delayed delayed) {
        DelayedTask other = (DelayedTask) delayed;
        if (this.endOfDelay < other.endOfDelay)
            return -1;
        else if (this.endOfDelay > other.endOfDelay)
            return 1;
        return 0;
    }
}
