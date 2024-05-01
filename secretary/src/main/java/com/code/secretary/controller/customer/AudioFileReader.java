// package com.code.secretary.controller.customer;
//
// import java.io.IOException;
// import java.nio.file.Files;
// import java.util.ArrayList;
// import java.util.List;
//
// import org.springframework.core.io.Resource;
// import org.springframework.core.io.ResourceLoader;
// import org.springframework.stereotype.Service;
//
// @Service
// public class AudioFileReader {
//
// private final ResourceLoader resourceLoader;
//
// public AudioFileReader(ResourceLoader resourceLoader) {
// this.resourceLoader = resourceLoader;
// }
//
// public List<byte[]> readAudioFile() throws IOException {
// Resource audioResource = resourceLoader.getResource("classpath:static/Good.mp3");
//
// byte[] audioData = Files.readAllBytes(audioResource.getFile().toPath());
//
// int chunkSize = 4096; // 4 KB
//
// List<byte[]> audioChunks = new ArrayList<>();
// int offset = 0;
// while (offset < audioData.length) {
// int end = Math.min(offset + chunkSize, audioData.length);
// byte[] chunk = new byte[end - offset];
// System.arraycopy(audioData, offset, chunk, 0, end - offset);
// audioChunks.add(chunk);
// offset = end;
// }
//
// return audioChunks;
// }
// }
