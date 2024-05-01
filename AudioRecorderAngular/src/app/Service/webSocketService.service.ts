
import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { BehaviorSubject } from 'rxjs';
import { AudioChunks } from '../model/audio-chunk.model';


@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private baseUrl = 'http://localhost:8080';
  private topic = '/topic/receive';
  private stompClient: any;
  public audioStream = new BehaviorSubject<any>(null);
  
  public audioContext: AudioContext = new (window.AudioContext)();




  connect() {
    const socket = new SockJS(`${this.baseUrl}/api/websocket/socket`);
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, (frame: any) => {
      console.log(`Connected: ${frame}`);
      this.stompClient.subscribe(this.topic, (audioData: any) => {
        console.log(audioData.body)
        let audio : AudioChunks
        audio = audioData.body
        // this.audioStream.next(audio.Chunk);
        this.decodeAndPlayAudioChunk(audio.Chunk);

      });
    }, this.errorCallback);
  }
  decodeAndPlayAudioChunk(audioChunk: Uint8Array) {
    // Decode the audio chunk using the AudioContext
    this.audioContext.decodeAudioData(audioChunk.buffer)
      .then((decodedData) => {
        // Create a new AudioBufferSourceNode
        const source = this.audioContext.createBufferSource();
        source.buffer = decodedData;

        // Connect the source to the audio context destination (speakers)
        source.connect(this.audioContext.destination);

        // Play the audio chunk
        source.start();
      })
      .catch((error) => {
        console.error('Error decoding audio chunk:', error);
      });
  }
  sendMessage() {
    this.stompClient.send('/app/send', {}, "lina");
  }

  disconnect() {
    if (this.stompClient) {
      this.stompClient.disconnect();
      console.log('Disconnected');
    }
  }

  errorCallback(error: any) {
    console.error('WebSocket error:', error);
    setTimeout(() => {
      this.connect();
    }, 1000000000);
  }
}
