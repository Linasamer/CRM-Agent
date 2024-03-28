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
public class CreditCard {

    @JsonProperty("CardSeqNumber")
    private Integer cardSeqNumber;

    @JsonProperty("CardNumber")
    private String cardNumber;

    @JsonProperty("CardNickName")
    private String cardNickName;

    @JsonProperty("EmbossingName")
    private String embossingName;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("CardExpDate")
    private String cardExpDate;

    @JsonProperty("SibAccountNumber")
    private Integer sibAccountNumber;

    @JsonProperty("CardAccount")
    private String cardAccount;

    @JsonProperty("AddressSeqNumber")
    private Integer addressSeqNumber;

    @JsonProperty("CreditCardType")
    private Integer creditCardType;

    @JsonProperty("ShowStatusFlg")
    private String showStatusFlg;

    @JsonProperty("RewardPoints")
    private Integer rewardPoints;

    @JsonProperty("CardIndicator")
    private String cardIndicator;

    @JsonProperty("CardFullStatus")
    private String cardFullStatus;

    @JsonProperty("eStatementFlg")
    private String eStatementFlg;

    @JsonProperty("FavouriteFlg")
    private String favouriteFlg;

    @JsonProperty("AvailableCash")
    private Integer availableCash;

    @JsonProperty("AvailableCredit")
    private Integer availableCredit;

    @JsonProperty("ConsumedLimit")
    private Integer consumedLimit;

    @JsonProperty("DueAmount")
    private Integer dueAmount;

    @JsonProperty("DueDate")
    private String dueDate;

    @JsonProperty("CardStatus")
    private Integer cardStatus;

    @JsonProperty("StmtAmt")
    private Integer stmtAmt;

    @JsonProperty("UnbilledAmt")
    private Integer unbilledAmt;

    @JsonProperty("TotalAmt")
    private Integer totalAmt;

    @JsonProperty("ProdCode")
    private String prodCode;

    @JsonProperty("ProdDesc")
    private String prodDesc;

    @JsonProperty("ApplePayStatus")
    private String applePayStatus;

    @JsonProperty("PrimarySerialNum")
    private Integer primarySerialNum;

    @JsonProperty("DossierID")
    private String dossierID;

}