export class DataRequestModel {
    Text!: string;
    Language!: string;

    constructor(text: string, language: string) {
        this.Text = text;
        this.Language = language
      }
  }