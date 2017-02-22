import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by tom on 2/21/17.
 */
public class SelectPreferredNeighbors implements Runnable {
    private ScheduledExecutorService SPNscheduler = null;
    private ScheduledFuture<?> futureTask = null;

    public SelectPreferredNeighbors(){
        SPNscheduler = Executors.newScheduledThreadPool(1);
    }


    public void run() {
        //calculate the speed for all neighbor peers
        Date date = new Date();
        date.getTime();
        System.out.println(date);

    }
    public void repeat(int startTime, int unchokingInterval){
        futureTask = SPNscheduler.scheduleAtFixedRate(this, startTime, unchokingInterval, TimeUnit.SECONDS);
    }
}
