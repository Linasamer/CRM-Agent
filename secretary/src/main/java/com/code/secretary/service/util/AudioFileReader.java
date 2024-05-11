package com.code.secretary.service.util;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.code.secretary.models.responses.AudioResponse;

@Service
public class AudioFileReader {

	private final ResourceLoader resourceLoader;
	@Autowired
	private SimpMessagingTemplate template;

	public AudioFileReader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public void readAudioFile() throws IOException, InterruptedException {
		// Path to the audio file in the classpath
		Resource audioResource = resourceLoader.getResource("classpath:Good.mp3");

		// Read the audio file as a byte array
		byte[] audioData = Files.readAllBytes(audioResource.getFile().toPath());

		// Define the chunk size
		int chunkSize = 4096; // 4 KB

		// Split the audio data into chunks
		List<byte[]> audioChunks = new ArrayList<>();
		int offset = 0;
		while (offset < audioData.length) {
			int end = Math.min(offset + chunkSize, audioData.length);
			byte[] chunk = new byte[end - offset];
			System.arraycopy(audioData, offset, chunk, 0, end - offset);
			audioChunks.add(chunk);
			offset = end;
		}

		// return audioChunks;
		for (byte[] chunk : audioChunks) {
			template.convertAndSend("/topic/sending", AudioResponse.builder().audiochunk(chunk).build());
			Thread.sleep(40000);
		}
	}
}