import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author jinpeng.fan
 * @date 2022/11/25 10:31 AM
 * description
 */
public class Main {

    public static void main(String[] args) throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
                .withIdentity("TestJob")
                .usingJobData("msg", "Hello quartz job.")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever()
                )
                .withIdentity("3SecondsTrigger")
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
