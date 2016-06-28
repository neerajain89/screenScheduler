package com.skilrock.screenSchedule;


import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SwitchWindowTwoJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try{
			
		
			/*String secondWindow = ScreenSchedulingMain.driver.getTitle();
			TestSel.driver.switchTo().window(ScreenSchedulingMain.screenTwoWindowHandle);
			System.out.println("Fire Time2"+arg0.getFireTime());
			System.out.println("NExt Fire Time Tiem2"+arg0.getTrigger().getNextFireTime());*/
			
			// String secondWindow = TestSel.driver.getTitle();
			ScreenSchedulingMain.driver.switchTo().window(ScreenSchedulingMain.screenTwoWindowHandle);
			System.out.println("Fire Time2" + context.getFireTime());
			Scheduler sched = ScreenSchedulingMain.screenTwoScheduler;
		/*	Trigger oldTrigger = sched
					.getTrigger(context.getTrigger().getKey());*/
			JsonParser parser = new JsonParser();
			JsonObject object = (JsonObject) parser.parse(ScreenSchedulingMain.screenScheduleInfo);
			JsonArray drawArray = object.get("goldenRace").getAsJsonArray();

			Calendar current = ScreenSchedulingMain.getCurrentSchedule(drawArray);
			Trigger myTrigger = TriggerBuilder.newTrigger().startAt(current.getTime()).build();

			sched.rescheduleJob(context.getTrigger().getKey(), myTrigger);

			System.out.println("NExt Fire Time2"+ myTrigger.getNextFireTime());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
