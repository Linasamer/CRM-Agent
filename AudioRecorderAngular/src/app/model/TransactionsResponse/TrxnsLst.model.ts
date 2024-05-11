import { RecPgCtrlOut } from "../profileDataModelResponse/recpgctrlout-response.model";
import { TransactionDetail } from "./TransactionDetail.model";

export interface TrxnsLst {
    CICNum: string;
    AvailableBal: number;
    UnclearedBal: number;
    RecPgCtrlOut: RecPgCtrlOut;
    TrxnDtls: TransactionDetail[];
  }