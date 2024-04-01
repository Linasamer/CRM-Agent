//package com.code.secretary.config;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.concurrent.ScheduledFuture;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.TaskScheduler;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//
//import com.code.secretary.entity.dto.util.PushNotification;
//import com.code.secretary.entity.orm.setup.WorkingUnitData;
//import com.code.secretary.enums.PushNotificationTypeEnum;
//import com.code.secretary.enums.util.PrayTimeJobNamesEnum;
//import com.code.secretary.service.setup.WorkingUnitService;
//import com.code.secretary.service.util.MessageService;
//import com.code.secretary.service.util.NotificationService;
//import com.code.secretary.service.util.PrayTimesService;
//
//@Configuration
//public class SchedulerConfig implements SchedulingConfigurer {
//
//	ScheduledTaskRegistrar scheduledTaskRegistrar;
//
//	Map<String, ScheduledFuture<?>> futureMap = new HashMap<String, ScheduledFuture<?>>();
//
//	@Autowired
//	PrayTimesService prayTimesService;
//
//	@Autowired
//	private WorkingUnitService workingUnitService;
//
//	@Autowired
//	private NotificationService notificationService;
//
//	@Bean
//	public TaskScheduler poolScheduler() {
//		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//		scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
//		scheduler.setPoolSize(1);
//		scheduler.initialize();
//		return scheduler;
//	}
//
//	// Initially scheduler has no job on the fly and initialize the pray first run
//	@Override
//	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//		if (scheduledTaskRegistrar == null) {
//			scheduledTaskRegistrar = taskRegistrar;
//		}
//		if (taskRegistrar.getScheduler() == null) {
//			taskRegistrar.setScheduler(poolScheduler());
//			prayTimesService.reCalculatePrayTimes();
//		}
//	}
//
//	public boolean addJob(String jobName, String prayerTime, Date executeDate) {
//
//		if (futureMap.containsKey(jobName) || executeDate.before(new Date())) {
//			System.out.println("FALSE: jobName=" + jobName + ", executeDate=" + executeDate);
//			return false;
//		}
//
//		System.out.println("jobName=" + jobName + ", executeDate=" + executeDate);
//
//		ScheduledFuture<?> future = scheduledTaskRegistrar.getScheduler()
//				.schedule(() -> prayerJobCallback(jobName, prayerTime), t -> {
//					Calendar nextExecutionTime = new GregorianCalendar();
//					Date lastExecutionTime = t.lastActualExecutionTime();
//					nextExecutionTime.setTime(lastExecutionTime != null ? t.lastActualExecutionTime() : executeDate);
//					if (lastExecutionTime != null)
//						nextExecutionTime.add(Calendar.DAY_OF_MONTH, 1);
//					return nextExecutionTime.getTime();
//				});
//
//		configureTasks(scheduledTaskRegistrar);
//		futureMap.put(jobName, future);
//		return true;
//	}
//
//	public boolean addRemindMeAgainJob(String jobName, Date executeDate, PushNotification pushNotification) {
//
//		if (futureMap.containsKey(jobName) || executeDate.before(new Date())) {
//			System.out.println("FALSE: jobName=" + jobName + ", executeDate=" + executeDate);
//			return false;
//		}
//
//		System.out.println("jobName=" + jobName + ", executeDate=" + executeDate);
//
//		ScheduledFuture<?> future = scheduledTaskRegistrar.getScheduler()
//				.schedule(() -> prayerRemindMeAgainJobCallback(jobName, pushNotification), t -> {
//					Calendar nextExecutionTime = new GregorianCalendar();
//					Date lastExecutionTime = t.lastActualExecutionTime();
//					nextExecutionTime.setTime(lastExecutionTime != null ? t.lastActualExecutionTime() : executeDate);
//					if (lastExecutionTime != null)
//						nextExecutionTime.add(Calendar.DAY_OF_MONTH, 1);
//					return nextExecutionTime.getTime();
//				});
//
//		configureTasks(scheduledTaskRegistrar);
//		futureMap.put(jobName, future);
//		return true;
//	}
//
//	public boolean addMeetingAppointmentReminderJob(String jobName, String alertMessage, Date executeDate) {
//
//		if (futureMap.containsKey(jobName)) {
//			System.out.println("FALSE: jobName=" + jobName + ", executeDate=" + executeDate);
//			return false;
//		}
//
//		System.out.println("jobName=" + jobName + ", executeDate=" + executeDate);
//
//		ScheduledFuture<?> future = scheduledTaskRegistrar.getScheduler()
//				.schedule(() -> meetingAppointmentReminderJobCallback(jobName, alertMessage), t -> {
//					Calendar nextExecutionTime = new GregorianCalendar();
//					Date lastExecutionTime = t.lastActualExecutionTime();
//					nextExecutionTime.setTime(lastExecutionTime != null ? t.lastActualExecutionTime() : executeDate);
//					if (lastExecutionTime != null)
//						nextExecutionTime.add(Calendar.DAY_OF_MONTH, 1);
//					return nextExecutionTime.getTime();
//				});
//
//		configureTasks(scheduledTaskRegistrar);
//		futureMap.put(jobName, future);
//		return true;
//	}
//
//	private void meetingAppointmentReminderJobCallback(String jobName, String alertMessage) {
//		System.out.println("meetingAppointmentReminderJobCallback: call front end with specific alert with jobName " + jobName);
//
//		Long workingUnitId = Long.parseLong(jobName.substring(jobName.indexOf(":") + 1, jobName.length()));
//		Long managerId = this.workingUnitService.getWorkingUnitDataById(workingUnitId).getManagerId();
//
//		// TODO check on don't disturb here too?
//		// if (!workingUnitData.getDontDisturbFlag().equals(FlagsEnum.ON.getCode()))
//
//		this.notificationService.sendAlert(managerId, MessageService.getMessage("ar", "label_warning"), alertMessage, PushNotificationTypeEnum.MEETING_APPOINTMENT_REMINDER.getCode());
//		System.out.println("meetingAppointmentReminderJobCallback: Reminder: " + alertMessage + ", managerId=" + managerId);
//		removeJob(jobName);
//	}
//
//	public void prayerJobCallback(String jobName, String prayerTime) {
//
//		System.out.println("prayerJobCallback: call front end with specific alert with jobName " + jobName + ", prayerTime= " + prayerTime);
//
//		String alertArMessage = "";
//		String prayerName = MessageService.getMessage("ar", "label_prayer" + jobName.substring(0, jobName.indexOf("_")));
//
//		Long workingUnitId = Long.parseLong(jobName.substring(jobName.indexOf(":") + 1, jobName.length()));
//		WorkingUnitData workingUnitData = this.workingUnitService.getWorkingUnitDataById(workingUnitId);
//		Long managerId = workingUnitData.getManagerId();
//
//		// TODO check on don't disturb here too?
//		// if (!workingUnitData.getDontDisturbFlag().equals(FlagsEnum.ON.getCode()))
//
//		if (jobName.substring(jobName.lastIndexOf("_") + 1, jobName.length()).startsWith("REMINDER")) {
//
//			String cityName = workingUnitData.getCityName() == null ? "" : workingUnitData.getCityName();
//			alertArMessage = MessageService.getMessage("ar", "label_prayerTimeSoon", new Object[] { prayerName, cityName, prayerTime });
//
//			this.notificationService.sendAlert(managerId, "", alertArMessage, PushNotificationTypeEnum.PRAYER_REMINDER.getCode());
//			System.out.println("prayerJobCallback: Reminder: " + alertArMessage + ", managerId=" + managerId + ", cityName=" + cityName);
//
//		} else if (jobName.substring(jobName.lastIndexOf("_") + 1, jobName.length()).startsWith("ACTUAL")) {
//
//			alertArMessage = MessageService.getMessage("ar", "label_prayerTimeNow", new Object[] { prayerName });
//
//			this.notificationService.sendAlert(managerId, "", alertArMessage, PushNotificationTypeEnum.PRAYER.getCode());
//			System.out.println("prayerJobCallback: Now: " + alertArMessage + ", managerId=" + managerId);
//
//			// remove the reminder job
//			removeJob(PrayTimeJobNamesEnum.REMIND_ME.getCode() + ":" + workingUnitData.getId());
//		}
//	}
//
//	public void prayerRemindMeAgainJobCallback(String jobName, PushNotification pushNotification) {
//
//		System.out.println("prayerRemindMeAgainJobCallback: call front end with specific alert with jobName " + jobName);
//
//		Long workingUnitId = Long.parseLong(jobName.substring(jobName.indexOf(":") + 1, jobName.length()));
//		Long managerId = this.workingUnitService.getWorkingUnitDataById(workingUnitId).getManagerId();
//
//		// TODO check on don't disturb here too?
//		// if (!workingUnitData.getDontDisturbFlag().equals(FlagsEnum.ON.getCode()))
//
//		this.notificationService.sendAlert(managerId, "", pushNotification.getMessage(), PushNotificationTypeEnum.PRAYER_REMINDER.getCode());
//		System.out.println("prayerRemindMeAgainJobCallback: Reminder: " + pushNotification.getMessage() + ", managerId=" + managerId);
//	}
//
//	// ------------------------------------------------utility----------------------------
//	public boolean removeJob(String name) {
//		if (!futureMap.containsKey(name)) {
//			return false;
//		}
//
//		ScheduledFuture<?> future = futureMap.get(name);
//		future.cancel(true);
//		futureMap.remove(name);
//		return true;
//	}
//
//	public void clearAllJobs() {
//		for (Entry<String, ScheduledFuture<?>> entry : futureMap.entrySet()) {
//			ScheduledFuture<?> future = futureMap.get(entry.getKey());
//			future.cancel(true);
//		}
//		futureMap = new HashMap<String, ScheduledFuture<?>>();
//	}
//
//}
