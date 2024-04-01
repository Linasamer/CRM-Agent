//package com.code.secretary.controller.util;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.code.secretary.entity.dto.util.PrayTime;
//import com.code.secretary.entity.dto.util.PushNotification;
//import com.code.secretary.enums.util.PrayTimesFormatEnum;
//import com.code.secretary.service.util.PrayTimesService;
//
//@RestController
//@RequestMapping("/api/prayTime")
//@CrossOrigin(origins = "*")
//public class PrayTimesController {
//
//	private PrayTimesService prayTimeService;
//
//	@Autowired
//	public PrayTimesController(PrayTimesService prayTimeService) {
//		this.prayTimeService = prayTimeService;
//	}
//
//	@GetMapping("/pray-times/{managerId}")
//	public PrayTime getPrayTimes(@PathVariable Long managerId) {
//		System.out.println("new Date()=" + new Date());
//		return prayTimeService.calculatePrayTimes(PrayTimesFormatEnum.HOURS_12.getCode(), managerId);
//	}
//
//	@PostMapping("/remind-me-again/{managerId}")
//	public void remindMeAgain(@PathVariable Long managerId, @RequestBody PushNotification pushNotification) {
//		prayTimeService.remindMeAgain(managerId, pushNotification);
//	}
//
//	@GetMapping("/reCalculate-pray-Times")
//	public void reCalculatePrayTimes() {
//		prayTimeService.reCalculatePrayTimes();
//	}
//
//	@GetMapping("/get-stop-reminder-before-prayer-time")
//	public String getStopReminderBeforePrayerTime() {
//		return "{\"time\":\"" + prayTimeService.getStopReminderBeforePrayerTime() + "\"}";
//	}
//
//}
