import { Injectable } from '@angular/core';
import SimpleWebRTC from 'simplewebrtc';

@Injectable({
    providedIn: 'root'
})
export class WebRTCService {
    private webrtc = SimpleWebRTC();

    constructor() {
        // Initialize SimpleWebRTC
        this.webrtc = new SimpleWebRTC({
            localVideoEl: '',
            remoteVideosEl: '',
            autoRequestMedia: false,
            media: {
                audio: true,
                video: false // Change to true if you want video too
            }
        });
    }

    // Start local media and join a room
    startStream(roomName: string): void {
        this.webrtc.startLocalMedia();
        this.webrtc.joinRoom(roomName);
    }

    // Listen for remote audio streams
    onRemoteStream(callback: (stream: MediaStream) => void): void {
        this.webrtc.on('videoAdded', (video:any, peer:any) => {
            if (video) {
                callback(video.srcObject as MediaStream);
            }
        });
    }
}