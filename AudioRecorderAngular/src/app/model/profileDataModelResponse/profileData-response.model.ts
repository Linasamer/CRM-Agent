import { AccountsList } from "./accountsList-response.model";
import { CreditCardList } from "./creditCardList-response.model";
import { RewardPoints } from "./rewardPoints-response.model";

export interface ProfileData {
  ProfileNumber: string;
  AccountsLst: AccountsList[];
  CreditCardLst: CreditCardList[];
  NotificationsLst: Notification[];
  MarketingMsgsLst: Notification[];
  RewardPoints: RewardPoints;
}