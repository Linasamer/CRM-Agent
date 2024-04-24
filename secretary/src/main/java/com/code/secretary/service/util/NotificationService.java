package com.code.secretary.service.util;

import com.code.secretary.entity.dto.util.PushNotification;

public interface NotificationService {

	public void sendAlert(Long employeeId, String messageHeader, String messageBody, Integer alertType);

	public void sendNotification(Long employeeId, PushNotification pushNotifications);

	public void parseBase64() throws InterruptedException;

}
