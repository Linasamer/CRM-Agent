import { CreditCard } from "./creditCard-response.model";
import { RecPgCtrlOut } from "./recpgctrlout-response.model";

export interface CreditCardList {
  RecPgCtrlOut: RecPgCtrlOut[];
  CreditCards: CreditCard[];
}