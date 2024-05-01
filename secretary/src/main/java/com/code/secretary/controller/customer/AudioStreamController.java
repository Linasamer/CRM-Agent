// package com.code.secretary.controller.customer;
//
// import java.io.IOException;
// import java.util.List;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
// import org.springframework.stereotype.Controller;
//
// import com.code.secretary.models.AudioChunksResponse;
//
// @Controller
// public class AudioStreamController {
// private final SimpMessagingTemplate template;
// private final AudioFileReader audioFileReader;
//
// @Autowired
// public AudioStreamController(SimpMessagingTemplate template, AudioFileReader audioFileReader) {
// this.template = template;
// this.audioFileReader = audioFileReader;
// }
//
// @MessageMapping("/send")
// public void handleMessage(String message) throws IOException {
// List<byte[]> audioChunks = audioFileReader.readAudioFile();
//
// for (byte[] chunk : audioChunks) {
// template.convertAndSend("/topic/receive", AudioChunksResponse.builder().chunk(chunk).build());
// }
//
// }
// }
