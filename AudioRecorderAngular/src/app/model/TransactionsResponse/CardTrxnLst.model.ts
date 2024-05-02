import { RecPgCtrlOut } from "../profileDataModelResponse/recpgctrlout-response.model";
import { TransactionDetail } from "./TransactionDetail.model";

export interface CardTrxnLst {
    RecPgCtrlOut: RecPgCtrlOut;
    TrxnDtls: TransactionDetail[];
  }