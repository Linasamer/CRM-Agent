import { AudioMetadata } from "./AudioMetadata.model";

export class Message {
    type: string;
    content: string;
    voiceNote: boolean;
    voiceContent: string;
    audioMetadata: AudioMetadata[];

    constructor(type: string, content: string, voiceNote: boolean, voiceContent: string, audioMetadata: AudioMetadata[]) {
        this.type = type;
        this.content = content;
        this.voiceNote = voiceNote;
        this.voiceContent = voiceContent;
        this.audioMetadata = audioMetadata || []; // Initialize the array to an empty array if not provided
    }
}
