import { TransactionAmount } from "./TransactionAmount.model";

export interface BeneficiaryDetails  {
    BeneficiaryName: string;
    BeneficiaryBankAcct: string;
    BeneficiaryAmt: TransactionAmount;
  }