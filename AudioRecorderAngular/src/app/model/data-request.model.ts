export class DataRequestModel {
    Text!: string;
    Language!: string;
    CustomerCIC!: string;
    SessionId!: string;


    constructor(text: string, language: string, CICNumber: string, sessionId: string) {
        this.Text = text;
        this.Language = language
        this.CustomerCIC = CICNumber
        this.SessionId = sessionId
      }
  }