package com.code.secretary.controller.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.secretary.entity.dto.util.PushNotification;
import com.code.secretary.service.util.AudioFileReader;
import com.code.secretary.service.util.NotificationService;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "*")
public class NotificationController {

	// TODO controller to be deleted if won't be used later

	@Autowired
	private NotificationService notificationService;
	@Autowired
	private SimpMessagingTemplate template;
	@Autowired
	private AudioFileReader audioFileReader;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	// @GetMapping("/send-alert")
	// public void sendAlert(@RequestParam(name = "employeeId", required = true) Long employeeId,
	// @RequestParam(name = "messageHeader", required = true) String messageHeader,
	// @RequestParam(name = "messageBody", required = true) String messageBody) {
	// this.notificationService.sendAlert(employeeId, messageHeader, messageBody, PushNotificationTypeEnum.ALERT.getCode());
	// }

	// TODO
	@MessageMapping("/send")
	// @SendTo("/topic/sending")
	public void sending(PushNotification message) throws Exception {

		notificationService.parseBase64();
		// return new PushNotification(message.getMessageHeader(), message.getMessage(), message.getType());
	}

	// @MessageMapping("/send")
	// public void handleMessage(PushNotification message) throws IOException, InterruptedException {
	// audioFileReader.readAudioFile();
	// }

}
