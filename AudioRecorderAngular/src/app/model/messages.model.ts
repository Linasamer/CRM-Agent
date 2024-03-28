export class Message {
    type: string;
    content: string;
    voiceNote: boolean;
    voiceContent: string;

    constructor(type: string, content: string, voiceNote: boolean, voiceContent: string) {
        this.type = type;
        this.content =content;
        this.voiceNote = voiceNote;
        this.voiceContent = voiceContent;
      }
}