export class CustomerDatalList{
    CICNumber!: string;
    customerName !: string;



    constructor(text: string, customerName: string) {
        this.CICNumber = text;
        this.customerName = customerName;
      }
}