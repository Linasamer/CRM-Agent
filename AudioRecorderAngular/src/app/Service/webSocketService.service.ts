
import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { BehaviorSubject } from 'rxjs';
import { AudioChunks } from '../model/audio-chunk.model';
import { DataResponseModel } from '../model/data-response.model';


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
        const parsedData = JSON.parse(audioData.body);
            const dataResponseModel = new DataResponseModel();
        dataResponseModel.Text = parsedData.Text;
        dataResponseModel.Base46 = parsedData.Base46;
        this.audioStream.next(dataResponseModel);

      });
    }, this.errorCallback);
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
