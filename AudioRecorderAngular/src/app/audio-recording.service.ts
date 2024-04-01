import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { bufferToWave } from './audio-helper';

@Injectable({
  providedIn: 'root'
})
export class AudioRecordingService {
  private chunks: any[] = [];
  private mediaRecorder: any;
  private audioContext: AudioContext = new AudioContext();
  private audioBlobSubject = new Subject<Blob>();
  

  audioBlob$ = this.audioBlobSubject.asObservable();

  async startRecording() {
    if (this.audioContext.state === 'suspended') {
      await this.audioContext.resume();
    }
    
    const stream = await navigator.mediaDevices.getUserMedia({ audio: true });
    this.mediaRecorder = new MediaRecorder(stream);
    this.mediaRecorder.ondataavailable = (event: any) => this.chunks.push(event.data);
    this.mediaRecorder.start();
  }

  async stopRecording(): Promise<string> {
    return new Promise<string>((resolve, reject) => {
        let base64String;
        if (this.mediaRecorder) {
            this.mediaRecorder.onstop = async () => {
                try {
                    const audioData = await new Blob(this.chunks).arrayBuffer();
                    base64String = btoa(String.fromCharCode(...new Uint8Array(audioData)));
                    const audioBuffer = await this.audioContext.decodeAudioData(audioData);
                    const wavBlob = bufferToWave(audioBuffer, audioBuffer.length);
                    this.audioBlobSubject.next(wavBlob);
                    this.chunks = [];
                    resolve(base64String);
                } catch (error) {
                    reject(error);
                }
            };

            this.mediaRecorder.stop();
        } else {
            reject(new Error("MediaRecorder not initialized."));
        }
    });
  }
}