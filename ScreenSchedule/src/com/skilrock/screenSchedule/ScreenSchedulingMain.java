package com.skilrock.screenSchedule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ScreenSchedulingMain {

	public static WebDriver driver;
	public static String screenOneWindowHandle;
	public static String screenTwoWindowHandle;
	public static String mainWindowHandle;
	public static String screenScheduleInfo;
	public static Scheduler screenOneScheduler;
	public static Scheduler screenTwoScheduler;
	public static void main(String[] args) {
		try {
			JsonParser parser = new JsonParser();
			//screenScheduleInfo = "{'drawGame':['8:00:00','8:05:00','8:10:00','8:15:00','8:20:00','8:25:00','8:30:00','8:35:00','8:40:00','8:45:00','8:50:00','8:55:00','9:00:00','9:05:00','9:10:00','9:15:00','9:20:00','9:25:00','9:30:00','9:35:00','9:40:00','9:45:00','9:50:00','9:55:00','10:00:00','10:05:00','10:10:00','10:15:00','10:20:00','10:25:00','10:30:00','10:35:00','10:40:00','10:45:00','10:50:00','10:55:00','11:00:00','11:05:00','11:10:00','11:15:00','11:20:00','11:25:00','11:30:00','11:35:00','11:40:00','11:45:00','11:50:00','11:55:00','12:00:00','12:05:00','12:10:00','12:15:00','12:20:00','12:25:00','12:30:00','12:35:00','12:40:00','12:45:00','12:50:00','12:55:00','13:00:00','13:05:00','13:10:00','13:15:00','13:20:00','13:25:00','13:30:00','13:35:00','13:40:00','13:45:00','13:50:00','13:55:00','14:00:00','14:05:00','14:10:00','14:15:00','14:20:00','14:25:00','14:30:00','14:35:00','14:40:00','14:45:00','14:50:00','14:55:00','15:00:00','15:05:00','15:10:00','15:15:00','15:20:00','15:25:00','15:30:00','15:35:00','15:40:00','15:45:00','15:50:00','15:55:00','16:00:00','16:05:00','16:10:00','16:15:00','16:20:00','16:25:00','16:30:00','16:35:00','16:40:00','16:45:00','16:50:00','16:55:00','17:00:00','17:05:00','17:10:00','17:15:00','17:20:00','17:25:00','17:30:00','17:35:00','17:40:00','17:45:00','17:50:00','17:55:00','18:00:00','18:05:00','18:10:00','18:15:00','18:20:00','18:25:00','18:30:00','18:35:00','18:40:00','18:45:00','18:50:00','18:55:00','19:00:00','19:05:00','19:10:00','19:15:00','19:20:00','19:25:00','19:30:00','19:35:00','19:40:00','19:45:00','19:50:00','19:55:00','20:00:00','20:05:00','20:10:00','20:15:00','20:20:00','20:25:00','20:30:00','20:35:00','20:40:00','20:45:00','20:50:00','20:55:00','21:00:00','21:05:00','21:10:00','21:15:00','21:20:00','21:25:00','21:30:00','21:35:00','21:40:00','21:45:00','21:50:00','21:55:00','22:00:00','22:05:00','22:10:00','22:15:00'],'goldenRace':['8:17:00','8:22:00','8:27:00','8:32:00','8:37:00','8:42:00','8:47:00','8:52:00','8:57:00','9:02:00','9:07:00','9:12:00','9:17:00','9:22:00','9:27:00','9:32:00','9:37:00','9:42:00','9:47:00','9:52:00','9:57:00','10:02:00','10:07:00','10:12:00','10:17:00','10:22:00','10:27:00','10:32:00','10:37:00','10:42:00','10:47:00','10:52:00','10:57:00','11:02:00','11:07:00','11:12:00','11:17:00','11:22:00','11:27:00','11:32:00','11:37:00','11:42:00','11:47:00','11:52:00','11:57:00','12:02:00','12:07:00','12:12:00','12:17:00','12:22:00','12:27:00','12:32:00','12:37:00','12:42:00','12:47:00','12:52:00','12:57:00','13:02:00','13:07:00','13:12:00','13:17:00','13:22:00','13:27:00','13:32:00','13:37:00','13:42:00','13:47:00','13:52:00','13:57:00','14:02:00','14:07:00','14:12:00','14:17:00','14:22:00','14:27:00','14:32:00','14:37:00','14:42:00','14:47:00','14:52:00','14:57:00','15:02:00','15:07:00','15:12:00','15:17:00','15:22:00','15:27:00','15:32:00','15:37:00','15:42:00','15:47:00','15:52:00','15:57:00','16:02:00','16:07:00','16:12:00','16:17:00','16:22:00','16:27:00','16:32:00','16:37:00','16:42:00','16:47:00','16:52:00','16:57:00','17:02:00','17:07:00','17:12:00','17:17:00','17:22:00','17:27:00','17:32:00','17:37:00','17:42:00','17:47:00','17:52:00','17:57:00','18:02:00','18:07:00','18:12:00','18:17:00','18:22:00','18:27:00','18:32:00','18:37:00','18:42:00','18:47:00','18:52:00','18:57:00','19:02:00','19:07:00','19:12:00','19:17:00','19:22:00','19:27:00','19:32:00','19:37:00','19:42:00','19:47:00','19:52:00','19:57:00','20:02:00','20:07:00','20:12:00','20:17:00','20:22:00','20:27:00','20:32:00','20:37:00','20:42:00','20:47:00','20:52:00','20:57:00','21:02:00','21:07:00','21:12:00','21:17:00','21:22:00','21:27:00','21:32:00','21:37:00','21:42:00','21:47:00','21:52:00','21:57:00','22:02:00','22:07:00','22:17:00'],'drawGameURL':'http://192.168.124.205:8081/DrawGameWeb/DrawGameMachine.jsp','goldenRaceURL':'http://localhost/viewer/?display\u003d1'}";
			
			screenScheduleInfo=TpIntegrationImpl.getSchedulingResponseString();
			
			//str{"drawGame":["8:00:00","8:05:00","8:10:00","8:15:00","8:20:00","8:25:00","8:30:00","8:35:00","8:40:00","8:45:00","8:50:00","8:55:00","9:00:00","9:05:00","9:10:00","9:15:00","9:20:00","9:25:00","9:30:00","9:35:00","9:40:00","9:45:00","9:50:00","9:55:00","10:00:00","10:05:00","10:10:00","10:15:00","10:20:00","10:25:00","10:30:00","10:35:00","10:40:00","10:45:00","10:50:00","10:55:00","11:00:00","11:05:00","11:10:00","11:15:00","11:20:00","11:25:00","11:30:00","11:35:00","11:40:00","11:45:00","11:50:00","11:55:00","12:00:00","12:05:00","12:10:00","12:15:00","12:20:00","12:25:00","12:30:00","12:35:00","12:40:00","12:45:00","12:50:00","12:55:00","13:00:00","13:05:00","13:10:00","13:15:00","13:20:00","13:25:00","13:30:00","13:35:00","13:40:00","13:45:00","13:50:00","13:55:00","14:00:00","14:05:00","14:10:00","14:15:00","14:20:00","14:25:00","14:30:00","14:35:00","14:40:00","14:45:00","14:50:00","14:55:00","15:00:00","15:05:00","15:10:00","15:15:00","15:20:00","15:25:00","15:30:00","15:35:00","15:40:00","15:45:00","15:50:00","15:55:00","16:00:00","16:05:00","16:10:00","16:15:00","16:20:00","16:25:00","16:30:00","16:35:00","16:40:00","16:45:00","16:50:00","16:55:00","17:00:00","17:05:00","17:10:00","17:15:00","17:20:00","17:25:00","17:30:00","17:35:00","17:40:00","17:45:00","17:50:00","17:55:00","18:00:00","18:05:00","18:10:00","18:15:00","18:20:00","18:25:00","18:30:00","18:35:00","18:40:00","18:45:00","18:50:00","18:55:00","19:00:00","19:05:00","19:10:00","19:15:00","19:20:00","19:25:00","19:30:00","19:35:00","19:40:00","19:45:00","19:50:00","19:55:00","20:00:00","20:05:00","20:10:00","20:15:00","20:20:00","20:25:00","20:30:00","20:35:00","20:40:00","20:45:00","20:50:00","20:55:00","21:00:00","21:05:00","21:10:00","21:15:00","21:20:00","21:25:00","21:30:00","21:35:00","21:40:00","21:45:00","21:50:00","21:55:00","22:00:00","22:05:00","22:10:00","22:15:00"],"goldenRace":["8:17:00","8:22:00","8:27:00","8:32:00","8:37:00","8:42:00","8:47:00","8:52:00","8:57:00","9:02:00","9:07:00","9:12:00","9:17:00","9:22:00","9:27:00","9:32:00","9:37:00","9:42:00","9:47:00","9:52:00","9:57:00","10:02:00","10:07:00","10:12:00","10:17:00","10:22:00","10:27:00","10:32:00","10:37:00","10:42:00","10:47:00","10:52:00","10:57:00","11:02:00","11:07:00","11:12:00","11:17:00","11:22:00","11:27:00","11:32:00","11:37:00","11:42:00","11:47:00","11:52:00","11:57:00","12:02:00","12:07:00","12:12:00","12:17:00","12:22:00","12:27:00","12:32:00","12:37:00","12:42:00","12:47:00","12:52:00","12:57:00","13:02:00","13:07:00","13:12:00","13:17:00","13:22:00","13:27:00","13:32:00","13:37:00","13:42:00","13:47:00","13:52:00","13:57:00","14:02:00","14:07:00","14:12:00","14:17:00","14:22:00","14:27:00","14:32:00","14:37:00","14:42:00","14:47:00","14:52:00","14:57:00","15:02:00","15:07:00","15:12:00","15:17:00","15:22:00","15:27:00","15:32:00","15:37:00","15:42:00","15:47:00","15:52:00","15:57:00","16:02:00","16:07:00","16:12:00","16:17:00","16:22:00","16:27:00","16:32:00","16:37:00","16:42:00","16:47:00","16:52:00","16:57:00","17:02:00","17:07:00","17:12:00","17:17:00","17:22:00","17:27:00","17:32:00","17:37:00","17:42:00","17:47:00","17:52:00","17:57:00","18:02:00","18:07:00","18:12:00","18:17:00","18:22:00","18:27:00","18:32:00","18:37:00","18:42:00","18:47:00","18:52:00","18:57:00","19:02:00","19:07:00","19:12:00","19:17:00","19:22:00","19:27:00","19:32:00","19:37:00","19:42:00","19:47:00","19:52:00","19:57:00","20:02:00","20:07:00","20:12:00","20:17:00","20:22:00","20:27:00","20:32:00","20:37:00","20:42:00","20:47:00","20:52:00","20:57:00","21:02:00","21:07:00","21:12:00","21:17:00","21:22:00","21:27:00","21:32:00","21:37:00","21:42:00","21:47:00","21:52:00","21:57:00","22:02:00","22:07:00","22:17:00"],"drawGameURL":"http://192.168.124.205:8081/DrawGameWeb/DrawGameMachine.jsp","goldenRaceURL":"http://localhost/viewer/?display\u003d1"}
			System.out.println("str" + screenScheduleInfo);
			JsonObject object = (JsonObject) parser.parse(screenScheduleInfo);
			JsonArray drawArray = object.get("drawGame").getAsJsonArray();

			Calendar current = getCurrentSchedule(drawArray);
			
			/*for (int i = 0; i < drawArray.size(); i++) {
				String time = drawArray.get(i).getAsString();
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.HOUR_OF_DAY,
						Integer.parseInt(time.split(":")[0]));
				cal1.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
				cal1.set(Calendar.SECOND, 0);
				Calendar cal2 = Calendar.getInstance();
				if (cal1.after(cal2)) {
					//System.out.println("time" + time);
					if (current == null) {
						current = cal1;
					} else {
						if (current.after(cal1)) {
							current = cal1;
						}

					}
				}

			}*/
			if (current != null) {
				System.err.println(current.getTime());
				JobDetail job = JobBuilder.newJob(SwitchWindowJob.class)
						.withIdentity("dummyJobName", "group").build();
				Trigger myTrigger = TriggerBuilder.newTrigger()
						.withIdentity("dummyTriggerName").forJob(job)
						.startAt(current.getTime()).build();

				// schedule it
				screenOneScheduler= new StdSchedulerFactory().getScheduler();
				screenOneScheduler.start();
				screenOneScheduler.scheduleJob(job, myTrigger);
				System.out.println("myTrigger" + myTrigger.getNextFireTime());
			}

			current = null;
			drawArray = object.get("goldenRace").getAsJsonArray();
			current = getCurrentSchedule(drawArray);
			if (current != null) {
				System.err.println(current.getTime());
				JobDetail job1 = JobBuilder.newJob(SwitchWindowTwoJob.class)
						.withIdentity("dummyJobName1", "group1").build();
				Trigger myTrigger1 = TriggerBuilder.newTrigger()
						.withIdentity("dummyTriggerName1").forJob(job1)
						.startAt(current.getTime()).build();

				// schedule it
				screenTwoScheduler = new StdSchedulerFactory().getScheduler();
				screenTwoScheduler.start();
				screenTwoScheduler.scheduleJob(job1, myTrigger1);
				System.out.println("myTrigger1" + myTrigger1.getNextFireTime());
			}
			String drawGameURL = object.get("drawGameURL").getAsString();
			String goldenRaceURL = object.get("goldenRaceURL").getAsString();
			StringBuilder htmlStringBuilder = new StringBuilder();
			// append html header and title
			htmlStringBuilder
					.append("<html><head><title>Draw Machine</title></head>");
			// append body
			htmlStringBuilder.append("<body>");
			// append Draw Machien
			htmlStringBuilder.append("<a href=\"" + drawGameURL
					+ "\" id='link1'  target=\"_blank\">DrawMachine</a>");
			// append row
			htmlStringBuilder.append("<a href=\"" + goldenRaceURL
					+ "\" id='link2'  target=\"_blank\">GoldenRace</a>");
			// write html string content to a file
			String fileName = "ScreenWindow.html";
			WriteToFile(htmlStringBuilder.toString(), "ScreenWindow.html");
			String projectPath = System.getProperty("user.dir");
			System.out.println("projectPath" + projectPath);
			driver = new FirefoxDriver();
			driver.get("file://" + projectPath + File.separator + fileName);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			mainWindowHandle = driver.getWindowHandle();

			driver.findElement(By.id("link1")).click();
			screenOneWindowHandle = driver.getWindowHandle();
			Set<String> allWindowHandles = ScreenSchedulingMain.driver.getWindowHandles();
			for (String currentWindowHandle : allWindowHandles) {
				if (!currentWindowHandle.equals(mainWindowHandle)) {
					screenOneWindowHandle = currentWindowHandle;
					break;
				}
			}
			driver.findElement(By.id("link2")).click();

			allWindowHandles = ScreenSchedulingMain.driver.getWindowHandles();
			for (String currentWindowHandle : allWindowHandles) {
				if (!currentWindowHandle.equals(mainWindowHandle)
						&& !currentWindowHandle.equals(screenOneWindowHandle)) {
					screenTwoWindowHandle = currentWindowHandle;
					break;
				}
			}

			allWindowHandles = ScreenSchedulingMain.driver.getWindowHandles();
			for (String currentWindowHandle : allWindowHandles) {
				if (currentWindowHandle.equals(mainWindowHandle)) {
					driver.close();
					break;
				}
			}

			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						driver.quit();
						Thread.sleep(200);
						System.out.println("Shouting down ...");
						// some cleaning up code...

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void WriteToFile(String fileContent, String fileName)
			throws IOException {
		String projectPath = System.getProperty("user.dir");
		System.out.println("projectPath" + projectPath);
		String tempFile = projectPath + File.separator + fileName;
		File file = new File(tempFile);
		// if file does exists, then delete and create a new file
		if (file.exists()) {
			try {
				File newFileName = new File(projectPath + File.separator
						+ "backup_" + fileName);
				file.renameTo(newFileName);
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// write to file with OutputStreamWriter
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer = new OutputStreamWriter(outputStream);
		writer.write(fileContent);
		writer.close();

	}
	
	public static Calendar getCurrentSchedule(JsonArray drawArray){
		Calendar current=null; 
		for (int i = 0; i < drawArray.size(); i++) {
			String time = drawArray.get(i).getAsString();
			Calendar cal1 = Calendar.getInstance();
			cal1.set(Calendar.HOUR_OF_DAY,
					Integer.parseInt(time.split(":")[0]));
			cal1.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
			cal1.set(Calendar.SECOND, 0);
			Calendar cal2 = Calendar.getInstance();
			if (cal1.after(cal2)) {
				//System.out.println("time" + time);
				if (current == null) {
					current = cal1;
				} else {
					if (current.after(cal1)) {
						current = cal1;
					}

				}
			}

		}
		return current;	
	}
	
}
