import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author jinpeng.fan
 * @date 2022/11/25 10:29 AM
 * description
 */
public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String msg = jobExecutionContext.getJobDetail().getJobDataMap().getString("msg");
        System.out.println("The scheduler message is: " + msg);
    }
}
