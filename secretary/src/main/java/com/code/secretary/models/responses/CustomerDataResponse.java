package com.code.secretary.models.responses;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDataResponse {

    @JsonProperty("ProfileNumber")
    private String profileNumber;

    @JsonProperty("AccountsLst")
    private AccountsLstResponse accountsLst;

    @JsonProperty("CreditCardLst")
    private CreditCardLst creditCardLst;

    @JsonProperty("NotificationsLst")
    private List<Notification> notificationsLst;

    @JsonProperty("MarketingMsgssLst")
    private List<MarketingMessage> marketingMsgssLst;

    @JsonProperty("RewardPoints")
    private List<RewardPoints> rewardPoints;

}
