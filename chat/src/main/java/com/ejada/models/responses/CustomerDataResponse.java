package com.ejada.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable.Serializable
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
