package com.skilrock.screenSchedule;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestSel {

	public static WebDriver  driver   ;
	public static String  screenOneWindowHandle;
	public static String  screenTwoWindowHandle;
	public static String  mainWindowHandle;
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws Exception {
		
	
		final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
      	JsonParser parser = new JsonParser();
		String str ="{'drawGame':['8:00','8:05'],'goldenRace':['8:17']}";
		JsonObject object =(JsonObject) parser.parse(str);
    	JsonArray  drawArray = object.get("drawGame").getAsJsonArray();
    	Calendar cal1 = Calendar.getInstance();
    	for(int i=0;i<drawArray.size();i++){
    		String time = drawArray.get(i).getAsString();
    		
    		cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
    		cal1.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
    		cal1.set(Calendar.SECOND, 0);
    		Calendar cal2 = Calendar.getInstance();
    		if(cal2.after(cal1)){
    			
    		}
    		System.out.println("time"+time);
    		System.out.println("time"+time);
    		
    	}
    	
		 driver = new FirefoxDriver();
		driver.get("file://"+dir+"/demo.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		mainWindowHandle = driver.getWindowHandle();

		driver.findElement(By.id("link1")).click();
		screenOneWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = TestSel.driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(mainWindowHandle)) {
				screenOneWindowHandle=currentWindowHandle;
				break;
			}
		}
		driver.findElement(By.id("link2")).click();
		
		allWindowHandles = TestSel.driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(mainWindowHandle)&&!currentWindowHandle.equals(screenOneWindowHandle)) {
				screenTwoWindowHandle=currentWindowHandle;
				break;
			}
		}
		
		allWindowHandles = TestSel.driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (currentWindowHandle.equals(mainWindowHandle)) {
				driver.close();
				break;
			}
		}
	 
	
	
		//Quartz 1.6.3
    	//JobDetail job = new JobDetail();
    	//job.setName("dummyJobName");
    	//job.setJobClass(HelloJob.class);    	
    	JobDetail job = JobBuilder.newJob(SwitchWindowJob.class)
		.withIdentity("dummyJobName", "group").build();

	//Quartz 1.6.3
    	//CronTrigger trigger = new CronTrigger();
    	//trigger.setName("dummyTriggerName");
    	//trigger.setCronExpression("0/5 * * * * ?");
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.HOUR_OF_DAY, 8);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	Trigger myTrigger =TriggerBuilder.newTrigger()
                .withIdentity("dummyTriggerName")
                .forJob(job)
                .startAt(cal.getTime())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).repeatForever()).build();
    	
    
    	//schedule it
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, myTrigger);
    	System.out.println("myTrigger"+myTrigger.getNextFireTime());
    	JobDetail job1 = JobBuilder.newJob(SwitchWindowTwoJob.class)
    			.withIdentity("dummyJobName1", "group1").build();
    	 cal = Calendar.getInstance();
    	cal.set(Calendar.HOUR_OF_DAY, 8);
    	cal.set(Calendar.MINUTE, 17);
    	cal.set(Calendar.SECOND, 0);
    	Trigger myTrigger1 =TriggerBuilder.newTrigger()
                .withIdentity("dummyTriggerName1")
                .forJob(job1)
                .startAt( cal.getTime())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).repeatForever()).build();
    	
    
    	//schedule it
    	Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
    	scheduler1.start();
    	scheduler1.scheduleJob(job1, myTrigger1);
    	System.out.println("myTrigger1"+myTrigger1.getNextFireTime());
    	Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                	driver.quit();
                    Thread.sleep(200);
                    System.out.println("Shouting down ...");
                    //some cleaning up code...

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    	 
    	
	}
	
	public static String getCronExpForDateTime(Date dateTime) {
		String cronExp = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(dateTime.getTime());
			int sec = cal.get(Calendar.SECOND);
			int mm = cal.get(Calendar.MINUTE);
			int hh = cal.get(Calendar.HOUR_OF_DAY);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			cronExp = "" + sec + " " + mm + " " + hh + " " + day + " "
					+ (month + 1) + " ? " + year;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cronExp;
	}

	public static Date convertStringToDate(String dateTime)throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(dateTime);
		return date;
	}
	
	
}
