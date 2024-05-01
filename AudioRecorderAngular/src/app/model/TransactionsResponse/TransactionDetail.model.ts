import { BeneficiaryDetails } from "./BeneficiaryDetails.model";
import { TransactionAmount } from "./TransactionAmount.model";


export interface TransactionDetail {
    TrxnRefNum: number;
  AcctNum: string;
  TrxnID: number;
  TrxnCode: number;
  TrxnDateG: string;
  TrxnDateH: string;
  TrxnTime: string;
  TrxnAmt: TransactionAmount;
  AuthAmt: TransactionAmount;
  SetlAmt: TransactionAmount;
  TrxnBalAmt: TransactionAmount;
  BillingAmt: TransactionAmount;
  TrxnDesc: string;
  TrxnChID: string;
  MerchantName: string;
  MerchantCountry: string;
  TrxnRemarks: string;
  TrxnAuthStatus: string;
  AppliedExRate: number;
  BeneficiaryDtls: BeneficiaryDetails;
  TrxnBranchCode: number;
  LoadDate: string;
  SetlDate: string;
  BillDate: string;
  }