import { GenaralAmountInfo } from "./genaral-amount-info-response.model";

export interface Account {
  AccountNumber: string;
  AvailableBalance: GenaralAmountInfo;
  LedgerBalance: GenaralAmountInfo;
  ShowFlag: string;
  IBAN: string;
  ERNumber?: string;
  AccountStatus: string;
  ATMCardNum?: string;
  DailyLimit: GenaralAmountInfo;
  ConsumedDailyLimit: GenaralAmountInfo;
  RemainingDailyLimit: GenaralAmountInfo;
  SocialTrxnLimit: GenaralAmountInfo;
  FavoriteFlg: string;
  AcctIconFlg: string;
  AcctName: string;
  AcctOpeningDate: string;
  AcctType: string;
  AcctBranch: string;
  AcctSubcategory: string;
}
