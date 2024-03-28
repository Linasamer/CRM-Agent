package com.ejada.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable.Serializable
public class AccountResponse {

    @JsonProperty("AccountNumber")
    private String accountNumber;

    @JsonProperty("AvailableBalance")
    private AvailableBalance availableBalance;

    @JsonProperty("LedgerBalance")
    private LedgerBalance ledgerBalance;

    @JsonProperty("ShowFlag")
    private String showFlag;

    @JsonProperty("IBAN")
    private String iban;

    @JsonProperty("ERNumber")
    private String erNumber;

    @JsonProperty("AccountStatus")
    private String accountStatus;

    @JsonProperty("ATMCardNum")
    private String atmCardNum;

    @JsonProperty("DailyLimit")
    private DailyLimit dailyLimit;

    @JsonProperty("ConsumedDailyLimit")
    private ConsumedDailyLimit consumedDailyLimit;

    @JsonProperty("RemainingDailyLimit")
    private RemainingDailyLimit remainingDailyLimit;

    @JsonProperty("SocialTrxnLimit")
    private SocialTrxnLimit socialTrxnLimit;

    @JsonProperty("FavoriteFlg")
    private String favoriteFlg;

    @JsonProperty("AcctIconFlg")
    private String acctIconFlg;

    @JsonProperty("AcctName")
    private String acctName;

    @JsonProperty("AcctOpeningDate")
    private String acctOpeningDate;

    @JsonProperty("AcctType")
    private String acctType;

    @JsonProperty("AcctBranch")
    private String acctBranch;

    @JsonProperty("AcctSubcategory")
    private String acctSubcategory;

}
