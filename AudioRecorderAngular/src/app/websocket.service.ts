import { Injectable } from "@angular/core";
import { AppComponent } from "./app.component";
import { PushNotification } from "./model/push-notification.model";
import { BehaviorSubject, Observable } from "rxjs";

var SockJs = require("sockjs-client");
var Stomp = require("stompjs");

@Injectable({
    providedIn: 'root'
  })
export class WebSocketService {

  baseUrl: string = 'http://localhost:7072/ivr-BE/api';
  topic: string = "/topic/sending";
  private currentEmployeeId !: number;
  private stompClient:any;
  private webSocketMessage: BehaviorSubject<string> = new BehaviorSubject<string>("");
  private webSocketAudioMessage: BehaviorSubject<string> = new BehaviorSubject<string>("");


  connect() {
    // this.currentEmployeeId = employeeId;
    let socket = new SockJs(this.baseUrl + `/websocket/socket`);
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, (frame:any) => {
      this.stompClient.subscribe(this.topic, (notification:any) => {
        notification = notification.body;
        this.onMessageReceived(notification);
      });
    }, this.errorCallBack);
  }

  disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log("Disconnected");
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error:any) {
    console.log("errorCallBack -> " + error)
    setTimeout(() => {
      this.connect();
    }, 5000);
  }

  /**
   * Send message to sever via web socket
   * @param {*} message
   */
  send(message: PushNotification) {
    console.log("calling logout api via web socket");
    this.stompClient.send("/app/send", {}, JSON.stringify(message));
  }

  onMessageReceived(message: any) {
    console.log("Message Recieved from Server :: " + message);
    this.setTitle(message);
    this.base64toBlob(message,null,'application/x-www-form-urlencoded');
  }

  setTitle(value: string) {
    this.webSocketMessage.next(value);
  }

  getTitle(): Observable<string> {
    return this.webSocketMessage.asObservable();
  }

  setAudio(value: string) {
    this.webSocketAudioMessage.next(value);
  }

  getAudio(): Observable<string> {
    return this.webSocketAudioMessage.asObservable();
  }

  private base64toBlob(base64Data: any, text: any, contentType: any) {
    contentType = contentType || '';
    const sliceSize = 1024;
    const byteCharacters = atob(base64Data);
    const bytesLength = byteCharacters.length;
    const slicesCount = Math.ceil(bytesLength / sliceSize);
    const byteArrays = new Array(slicesCount);

    for (let sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
      const begin = sliceIndex * sliceSize;
      const end = Math.min(begin + sliceSize, bytesLength);

      const bytes = new Array(end - begin);
      for (let offset = begin, i = 0; offset < end; ++i, ++offset) {
        bytes[i] = byteCharacters[offset].charCodeAt(0);
      }
      byteArrays[sliceIndex] = new Uint8Array(bytes);
    }
    var myBlob = new Blob(byteArrays, { type: contentType });
    var blobURL = window.URL.createObjectURL(myBlob);
    this.setAudio(blobURL);
  }

}
