package com.code.secretary.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDetail {

	@JsonProperty("TrxnRefNum")
	private long transactionReferenceNumber;

	@JsonProperty("TrxnDate")
	private String transactionDateGregorian;

	@JsonProperty("TrxnDateHG")
	private String transactionDateHijri;

	@JsonProperty("TrxnTime")
	private String transactionTime;

	@JsonProperty("TrxnAmt")
	private TransactionAmount transactionAmount;
	@JsonProperty("AuthAmt")
	private TransactionAmount authAmount;
	@JsonProperty("SetlAmt")
	private TransactionAmount settlementAmount;
	@JsonProperty("TrxnBalAmt")
	private TransactionAmount transactionBalanceAmount;

	@JsonProperty("BillingAmt")
	private TransactionAmount billingAmount;

	@JsonProperty("TrxnDesc")
	private String transactionDescription;

	@JsonProperty("ChID")
	private String transactionChannelId;

	@JsonProperty("MerchantName")
	private String merchantName;

	@JsonProperty("MerchantCtry")
	private String merchantCountry;

	@JsonProperty("TrxnRemarks")
	private String transactionRemarks;

	@JsonProperty("TrxnAuthStatus")
	private String transactionAuthStatus;

	@JsonProperty("BeneficiaryDtls")
	private BeneficiaryDetails beneficiaryDetails;

	@JsonProperty("LoadDate")
	private String loadDate;
	@JsonProperty("SetlDate")
	private String settlementDate;
	@JsonProperty("BillDate")
	private String billDate;

}