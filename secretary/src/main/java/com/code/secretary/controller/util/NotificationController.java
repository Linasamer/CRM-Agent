//package com.code.secretary.controller.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.annotation.SendToUser;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.code.secretary.entity.dto.util.PushNotification;
//import com.code.secretary.enums.PushNotificationTypeEnum;
//import com.code.secretary.service.util.NotificationService;
//
//import io.swagger.v3.oas.annotations.Hidden;
//
//@Hidden
//@RestController
//@RequestMapping("/api/notification")
//@CrossOrigin(origins = "*")
//public class NotificationController {
//
//	// TODO controller to be deleted if won't be used later
//
//	@Autowired
//	private NotificationService notificationService;
//
//	public NotificationController(NotificationService notificationService) {
//		this.notificationService = notificationService;
//	}
//
//	@GetMapping("/send-alert")
//	public void sendAlert(@RequestParam(name = "employeeId", required = true) Long employeeId,
//			@RequestParam(name = "messageHeader", required = true) String messageHeader,
//			@RequestParam(name = "messageBody", required = true) String messageBody) {
//		this.notificationService.sendAlert(employeeId, messageHeader, messageBody, PushNotificationTypeEnum.ALERT.getCode());
//	}
//
//	// TODO
//	@MessageMapping("/send")
//	@SendToUser("/topic/sending")
//	public PushNotification sending(PushNotification message) throws Exception {
//		Thread.sleep(1000); // simulated delay
//		return new PushNotification(message.getMessageHeader(), message.getMessage(), message.getType());
//	}
//
//}
