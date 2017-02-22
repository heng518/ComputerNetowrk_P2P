import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by tom on 2/21/17.
 */
public class OptimisticNeighbor implements Runnable {
    private ScheduledExecutorService ONscheduler = null;
    private ScheduledFuture<?> futureTask = null;

    public OptimisticNeighbor(){
        ONscheduler = Executors.newScheduledThreadPool(1);
    }


    public void run() {
        //calculate the speed for all neighbor peers
        Date date = new Date();
        date.getTime();
        System.out.println(date);
    }
    public void repeat(int startTime, int optimisticUnchokingInterval){
        futureTask = ONscheduler.scheduleAtFixedRate(this, startTime, optimisticUnchokingInterval, TimeUnit.SECONDS);
    }

}
