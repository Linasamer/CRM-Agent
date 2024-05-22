package com.code.secretary.controller.customer;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.code.secretary.models.responses.DataResponse;

@Controller
public class AudioStreamController {
	private final SimpMessagingTemplate template;
	private final AudioFileReader audioFileReader;

	@Autowired
	public AudioStreamController(SimpMessagingTemplate template, AudioFileReader audioFileReader) {
		this.template = template;
		this.audioFileReader = audioFileReader;
	}

	@MessageMapping("/send")
	public void handleMessage(String message) throws IOException, InterruptedException {
		String audioChunks = audioFileReader.Base46ToString("EN");
		template.convertAndSend("/topic/receive",
				DataResponse.builder().base46(audioChunks).text("Lina").sessionId(UUID.randomUUID().toString()).build());
		Thread.sleep(1000);
		audioChunks = audioFileReader.Base46ToString("AR");
		template.convertAndSend("/topic/receive",
				DataResponse.builder().base46(audioChunks).text("Lina").sessionId(UUID.randomUUID().toString()).build());

	}
}
