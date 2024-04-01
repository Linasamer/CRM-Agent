export class CustomerData {
    CICNumber!: string;
    Language!: string;


    constructor(text: string, language: string) {
        this.CICNumber = text;
        this.Language = language;
      }
}