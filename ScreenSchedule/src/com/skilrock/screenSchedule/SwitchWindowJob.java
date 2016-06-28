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

public class SwitchWindowJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {

			// String secondWindow = TestSel.driver.getTitle();
			ScreenSchedulingMain.driver.switchTo().window(
					ScreenSchedulingMain.screenOneWindowHandle);
			System.out.println("Fire Time1" + context.getFireTime());
			Scheduler sched = ScreenSchedulingMain.screenOneScheduler;
			/*Trigger oldTrigger = sched
					.getTrigger(context.getTrigger().getKey());*/
			JsonParser parser = new JsonParser();
			JsonObject object = (JsonObject) parser.parse(ScreenSchedulingMain.screenScheduleInfo);
			JsonArray drawArray = object.get("drawGame").getAsJsonArray();

			Calendar current = ScreenSchedulingMain.getCurrentSchedule(drawArray);
			Trigger myTrigger = TriggerBuilder.newTrigger().startAt(current.getTime()).build();

			sched.rescheduleJob(context.getTrigger().getKey(), myTrigger);

			System.out.println("NExt Fire Time1"+ myTrigger.getNextFireTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
