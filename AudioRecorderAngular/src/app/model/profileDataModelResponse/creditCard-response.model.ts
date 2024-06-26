export interface CreditCard {
  CardSeqNumber: number;
  CardNumber: string;
  CardNickName?: string;
  EmbossingName: string;
  FirstName: string;
  LastName: string;
  CardExpDate: string;
  SibAccountNumber: string;
  CardAccount: string;
  AddressSeqNumber: number;
  CreditCardType: number;
  ShowStatusFlg: string;
  RewardPoints: number;
  CardIndicator: string;
  CardFullStatus: string;
  eStatementFlg: string;
  FavouriteFlg: string;
  AvailableCash: number;
  AvailableCredit: number;
  ConsumedLimit: number;
  DueAmount: number;
  DueDate: string;
  CardStatus: number;
  CRLimit: number;
  StmtAmt: number;
  UnbilledAmt: number;
  TotalAmt: number;
  ProdCode: string;
  ProdDesc: string;
  ApplePayStatus: string;
  PrimarySerialNum: number;
  DossierID: string;
}
