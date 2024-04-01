export class DataRequestModel {
    Text!: string;
    Language!: string;
    CustomerCIC!: string;

    constructor(text: string, language: string, CICNumber: string) {
        this.Text = text;
        this.Language = language
        this.CustomerCIC = CICNumber
      }
  }