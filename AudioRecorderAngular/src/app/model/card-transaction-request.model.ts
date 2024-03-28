export class CardTransactionRequest {
    CICNumber!: string;
    CardSequence!: string;
  
    constructor(data: Partial<CardTransactionRequest>) {
        Object.assign(this, data);
      }
  }