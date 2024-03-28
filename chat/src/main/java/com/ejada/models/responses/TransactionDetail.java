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
public class TransactionDetail {

    @JsonProperty("TrxnRefNum")
    private long transactionReferenceNumber;
    @JsonProperty("AcctNum")
    private long accountNumber;

    @JsonProperty("TrxnID")
    private long transactionId;

    @JsonProperty("TrxnCode")
    private int transactionCode;

    @JsonProperty("TrxnDateG")
    private String transactionDateGregorian;

    @JsonProperty("TrxnDateH")
    private String transactionDateHijri;

    @JsonProperty("TrxnTime")
    private String transactionTime;

    @JsonProperty("TrxnAmt")
    private TransactionAmount transactionAmount;
    @JsonProperty("AuthAmt")
    private TransactionAmount authAmount;
    @JsonProperty("SetlAmt")
    private TransactionAmount setlAmount;
    @JsonProperty("TrxnBalAmt")
    private TransactionAmount transactionBalanceAmount;

    @JsonProperty("BillingAmt")
    private TransactionAmount billingAmount;

    @JsonProperty("TrxnDesc")
    private String transactionDescription;

    @JsonProperty("TrxnChID")
    private String transactionChannelId;

    @JsonProperty("MerchantName")
    private String merchantName;

    @JsonProperty("MerchantCity")
    private String merchantCity;


    @JsonProperty("TrxnRemarks")
    private String transactionRemarks;

    @JsonProperty("TrxnAuthStatus")
    private String transactionAuthStatus;
    @JsonProperty("AppliedExRate")
    private int appliedExchangeRate;

    @JsonProperty("BeneficiaryDtls")
    private BeneficiaryDetails beneficiaryDetails;

    @JsonProperty("TrxnBranchCode")
    private int transactionBranchCode;

    @JsonProperty("LoadDate")
    private String LoadDate;
    @JsonProperty("SetlDate")
    private String seltDate;
    @JsonProperty("BillDate")
    private String billDate;

}