import { Account } from "./accounts-response.model";
import { GenaralAmountInfo } from "./genaral-amount-info-response.model";
import { RecPgCtrlOut } from "./recpgctrlout-response.model";

export interface AccountsList {
  RecPgCtrlOut: RecPgCtrlOut[];
  TotalBal: GenaralAmountInfo;
  Accounts: Account[];
}