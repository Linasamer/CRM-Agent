package com.code.secretary.service.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.code.secretary.models.requests.AccountTransactionRequest;
import com.code.secretary.models.requests.AgentRequest;
import com.code.secretary.models.requests.CardTransactionRequest;
import com.code.secretary.models.requests.DataRequest;
import com.code.secretary.models.requests.GreetingDataRequest;
import com.code.secretary.models.responses.AccountResponse;
import com.code.secretary.models.responses.AccountTransactionResponse;
import com.code.secretary.models.responses.AccountsLstResponse;
import com.code.secretary.models.responses.AgentResponse;
import com.code.secretary.models.responses.AvailableBalance;
import com.code.secretary.models.responses.BeneficiaryAmount;
import com.code.secretary.models.responses.BeneficiaryDetails;
import com.code.secretary.models.responses.CardTransactionResponse;
import com.code.secretary.models.responses.ConsumedDailyLimit;
import com.code.secretary.models.responses.CreditCard;
import com.code.secretary.models.responses.CreditCardLst;
import com.code.secretary.models.responses.CustomerDataResponse;
import com.code.secretary.models.responses.DailyLimit;
import com.code.secretary.models.responses.DataResponse;
import com.code.secretary.models.responses.GreetingDataResponse;
import com.code.secretary.models.responses.LedgerBalance;
import com.code.secretary.models.responses.MarketingMessage;
import com.code.secretary.models.responses.Notification;
import com.code.secretary.models.responses.RecPgCtrlOutResponse;
import com.code.secretary.models.responses.RemainingDailyLimit;
import com.code.secretary.models.responses.RewardPoints;
import com.code.secretary.models.responses.SocialTrxnLimit;
import com.code.secretary.models.responses.TotalBal;
import com.code.secretary.models.responses.TransactionAmount;
import com.code.secretary.models.responses.TransactionDetail;
import com.code.secretary.models.responses.TrxnLst;
import com.code.secretary.service.RestClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerService {
	private String sessionId;

	private static final Map<String, GreetingDataResponse> GREETING_DATA_RESPONSE_MAP = new HashMap<>();
	private static final HashMap<String, CustomerDataResponse> CUSTOMER_DATA_RESPONSE_MAP = new HashMap<>();
	private static final Map<String, CardTransactionResponse> CARD_TRANSACTION_RESPONSE_MAP = new HashMap<>();
	private static final Map<String, AccountTransactionResponse> ACCOUNT_TRANSACTION_RESPONSE_MAP = new HashMap<>();

	static {
		//////////////////////////////////////////////// Greeting Data Static Response///////////////////////////////////////////////////////

		GreetingDataResponse firstCustomer = GreetingDataResponse.builder().cicNumber("0000000018707728").firstNameEn("Ahmed").secondNameEn("mohamed")
				.thirdNameEn("mahmoud").lastNameEn("ali").firstNameAr("احمد").secondNameAr("محمد").thirdNameAr("محمود").lastNameAr("علي")
				.custSinceDt("2018-03-26").idNumber("2449029186").idType("2").custStatus("ACTIVE").idIssueDate("1439-05-21").birthDate("1989-02-13")
				.gender("M").build();

		GreetingDataResponse secondCustomer = GreetingDataResponse.builder().cicNumber("123456789").firstNameEn("Ali").secondNameEn("Khaled")
				.thirdNameEn("Samer").lastNameEn("ali").firstNameAr("علي").secondNameAr("خالد").thirdNameAr("سامر").lastNameAr("علي")
				.custSinceDt("2018-03-26").idNumber("2449029186").idType("2").custStatus("ACTIVE").idIssueDate("1439-05-21").birthDate("1989-02-13")
				.gender("M").build();

		GreetingDataResponse customer3 = GreetingDataResponse.builder().cicNumber("987654321").firstNameEn("Munira").secondNameEn("Khaled")
				.thirdNameEn("Samer").lastNameEn("ali").firstNameAr("منيره").secondNameAr("خالد").thirdNameAr("سامر").lastNameAr("علي")
				.custSinceDt("2018-03-26").idNumber("2449029186").idType("2").custStatus("ACTIVE").idIssueDate("1439-05-21").birthDate("1989-02-13")
				.gender("M").build();

		GreetingDataResponse customer4 = GreetingDataResponse.builder().cicNumber("000022224444").firstNameEn("Omer").secondNameEn("Khaled")
				.thirdNameEn("Samer").lastNameEn("ali").firstNameAr("عمر").secondNameAr("خالد").thirdNameAr("سامر").lastNameAr("علي")
				.custSinceDt("2018-03-26").idNumber("2449029186").idType("2").custStatus("ACTIVE").idIssueDate("1439-05-21").birthDate("1989-02-13")
				.gender("M").build();

		GREETING_DATA_RESPONSE_MAP.put(firstCustomer.getCicNumber(), firstCustomer);
		GREETING_DATA_RESPONSE_MAP.put(secondCustomer.getCicNumber(), secondCustomer);
		GREETING_DATA_RESPONSE_MAP.put(customer3.getCicNumber(), customer3);
		GREETING_DATA_RESPONSE_MAP.put(customer4.getCicNumber(), customer4);

		//////////////////////////////////////////////// Customer Data Static Response///////////////////////////////////////////////////////
		RecPgCtrlOutResponse recPgCtrlOutResponse = RecPgCtrlOutResponse.builder().sentRecs(4).matchedRecs(5).complFlg("Y").build();

		Notification notification = Notification.builder().notificationId(1).notificationDate("2024-04-21").notificationType("ID Expiry").build();

		MarketingMessage marketingMessage = MarketingMessage.builder().notificationId(1).notificationDate("2024-04-21").notificationType("ID Expiry")
				.build();

		RewardPoints rewardPoints = RewardPoints.builder().availableBalance(175).email("Ahmed.Saeed.Atya@gmail.com").expiredPoints(0).build();

		CreditCard creditCard1 = CreditCard.builder().cardSeqNumber(123).cardNumber("4458271234560990").prodDesc("VISA VIRTUAL").build();
		CreditCard creditCard2 = CreditCard.builder().cardSeqNumber(456).cardNumber("4458271234560991").prodDesc("VISA VIRTUAL").build();
		CreditCard creditCard3 = CreditCard.builder().cardSeqNumber(789).cardNumber("4458271234560992").prodDesc("VISA VIRTUAL").build();
		CreditCard creditCard4 = CreditCard.builder().cardSeqNumber(101).cardNumber("4458271234560993").prodDesc("VISA VIRTUAL").build();
		CreditCardLst creditCardLst1 = CreditCardLst.builder().recPgCtrlOut(recPgCtrlOutResponse).creditCard(Arrays.asList(creditCard1)).build();
		CreditCardLst creditCardLst2 = CreditCardLst.builder().recPgCtrlOut(recPgCtrlOutResponse).creditCard(Arrays.asList(creditCard2)).build();
		CreditCardLst creditCardLst3 = CreditCardLst.builder().recPgCtrlOut(recPgCtrlOutResponse).creditCard(Arrays.asList(creditCard3)).build();
		CreditCardLst creditCardLst4 = CreditCardLst.builder().recPgCtrlOut(recPgCtrlOutResponse).creditCard(Arrays.asList(creditCard4)).build();

		TotalBal totalBal = TotalBal.builder().amount(1961.06).currency("SAR").build();

		AccountResponse accountResponse = buildAccountResponse("126000110006080000000", "0000000018707728");
		AccountResponse accountResponse2 = buildAccountResponse("126000110006080000001", "000022224444");
		AccountResponse accountResponse3 = buildAccountResponse("126000110006080000002", "123456789");
		AccountResponse accountResponse4 = buildAccountResponse("126000110006080000003", "987654321");

		AccountsLstResponse accountsLstResponse = AccountsLstResponse.builder().Accounts(Arrays.asList(accountResponse))
				.recPgCtrlOut(recPgCtrlOutResponse).totalBal(totalBal).build();

		AccountsLstResponse accountsLstResponse2 = AccountsLstResponse.builder().Accounts(Arrays.asList(accountResponse2, accountResponse))
				.recPgCtrlOut(recPgCtrlOutResponse).totalBal(totalBal).build();

		AccountsLstResponse accountsLstResponse3 = AccountsLstResponse.builder()
				.Accounts(Arrays.asList(accountResponse3, accountResponse, accountResponse2)).recPgCtrlOut(recPgCtrlOutResponse).totalBal(totalBal)
				.build();

		AccountsLstResponse accountsLstResponse4 = AccountsLstResponse.builder()
				.Accounts(Arrays.asList(accountResponse4, accountResponse, accountResponse3, accountResponse2)).recPgCtrlOut(recPgCtrlOutResponse)
				.totalBal(totalBal).build();

		CUSTOMER_DATA_RESPONSE_MAP.put("0000000018707728",
				CustomerDataResponse.builder().profileNumber("0000000018707728").accountsLst(accountsLstResponse).creditCardLst(creditCardLst1)
						.notificationsLst(Arrays.asList(notification)).marketingMsgssLst(Arrays.asList(marketingMessage))
						.rewardPoints(Arrays.asList(rewardPoints)).build());

		CUSTOMER_DATA_RESPONSE_MAP.put("000022224444",
				CustomerDataResponse.builder().profileNumber("000022224444").accountsLst(accountsLstResponse2).creditCardLst(creditCardLst2)
						.notificationsLst(Arrays.asList(notification)).marketingMsgssLst(Arrays.asList(marketingMessage))
						.rewardPoints(Arrays.asList(rewardPoints)).build());

		CUSTOMER_DATA_RESPONSE_MAP.put("123456789",
				CustomerDataResponse.builder().profileNumber("123456789").accountsLst(accountsLstResponse3).creditCardLst(creditCardLst3)
						.notificationsLst(Arrays.asList(notification)).marketingMsgssLst(Arrays.asList(marketingMessage))
						.rewardPoints(Arrays.asList(rewardPoints)).build());

		CUSTOMER_DATA_RESPONSE_MAP.put("987654321",
				CustomerDataResponse.builder().profileNumber("987654321").accountsLst(accountsLstResponse4).creditCardLst(creditCardLst4)
						.notificationsLst(Arrays.asList(notification)).marketingMsgssLst(Arrays.asList(marketingMessage))
						.rewardPoints(Arrays.asList(rewardPoints)).build());

		//////////////////////////////////////////////// Card Transaction Data Static Response///////////////////////////////////////////////////////\
		List<TrxnLst> trxnLstList = new ArrayList<>();
		List<TransactionDetail> transactionDetails = new ArrayList<>();
		transactionDetails.add(TransactionDetail.builder().transactionReferenceNumber(920547953L)
				.transactionAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).transactionDateGregorian("2024-02-24")
				.transactionDateHijri("1445-08-14").transactionTime("17:11:42").transactionChannelId("OTHER").transactionAuthStatus("AUTH")
				.authAmount(TransactionAmount.builder().amount(150).currency("SAR").build())
				.billingAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).merchantName("ANNUAL FEE").merchantCountry("SAU")
				.loadDate("2024-02-24").settlementAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
				.transactionRemarks("ANNUAL FEE").settlementDate("2263-08-31").billDate("2024-02-24").build());
		trxnLstList.add(TrxnLst.builder().availableBalance(1020.3).cicNumber("123456789").unclearedBalance(100.2)
				.transactionDetails(transactionDetails).build());

		List<TrxnLst> trxnLstList1 = new ArrayList<>();
		List<TransactionDetail> transactionDetails1 = new ArrayList<>();
		transactionDetails1.add(TransactionDetail.builder().transactionReferenceNumber(920547954L)
				.transactionAmount(TransactionAmount.builder().amount(-75).currency("SAR").build()).transactionDateGregorian("2024-03-01")
				.transactionDateHijri("1445-08-20").transactionTime("10:30:15").transactionChannelId("ONLINE").transactionAuthStatus("AUTH")
				.authAmount(TransactionAmount.builder().amount(75).currency("SAR").build())
				.billingAmount(TransactionAmount.builder().amount(-75).currency("SAR").build()).merchantName("ONLINE STORE").merchantCountry("USA")
				.loadDate("2024-03-01").settlementAmount(TransactionAmount.builder().amount(-75).currency("SAR").build())
				.transactionRemarks("Online purchase").settlementDate("2024-03-02").billDate("2024-03-01").build());
		trxnLstList1.add(TrxnLst.builder().availableBalance(1020.3).cicNumber("987654321").unclearedBalance(100.2)
				.transactionDetails(transactionDetails1).build());
		List<TrxnLst> trxnLstList2 = new ArrayList<>();
		List<TransactionDetail> transactionDetails2 = new ArrayList<>();
		transactionDetails2.add(TransactionDetail.builder().transactionReferenceNumber(920547955L)
				.transactionAmount(TransactionAmount.builder().amount(-200).currency("SAR").build()).transactionDateGregorian("2024-03-05")
				.transactionDateHijri("1445-08-24").transactionTime("14:00:00").transactionChannelId("ATM").transactionAuthStatus("AUTHORIZED")
				.authAmount(TransactionAmount.builder().amount(200).currency("SAR").build())
				.billingAmount(TransactionAmount.builder().amount(-200).currency("SAR").build()).merchantName("CASH WITHDRAWAL")
				.merchantCountry("SAU").loadDate("2024-03-05").settlementAmount(TransactionAmount.builder().amount(-200).currency("SAR").build())
				.transactionRemarks("ATM cash withdrawal").settlementDate("2024-03-05").billDate("2024-03-05").build());
		trxnLstList2.add(TrxnLst.builder().availableBalance(1020.3).cicNumber("000022224444").unclearedBalance(100.2)
				.transactionDetails(transactionDetails2).build());
		List<TrxnLst> trxnLstList3 = new ArrayList<>();
		List<TransactionDetail> transactionDetails3 = new ArrayList<>();
		transactionDetails3.add(TransactionDetail.builder().transactionReferenceNumber(920547956L)
				.transactionAmount(TransactionAmount.builder().amount(-50).currency("SAR").build()).transactionDateGregorian("2024-03-07")
				.transactionDateHijri("1445-08-26").transactionTime("09:15:30").transactionChannelId("POS").transactionAuthStatus("AUTHORIZED")
				.authAmount(TransactionAmount.builder().amount(50).currency("SAR").build())
				.billingAmount(TransactionAmount.builder().amount(-50).currency("SAR").build()).merchantName("SUPERMARKET").merchantCountry("SAU")
				.loadDate("2024-03-07").settlementAmount(TransactionAmount.builder().amount(-50).currency("SAR").build())
				.transactionRemarks("Grocery purchase").settlementDate("2024-03-07").billDate("2024-03-07").build());
		trxnLstList3.add(TrxnLst.builder().availableBalance(1020.3).cicNumber("0000000018707728").unclearedBalance(100.2)
				.transactionDetails(transactionDetails3).build());
		CARD_TRANSACTION_RESPONSE_MAP.put("123456789:789", CardTransactionResponse.builder().transactionList(trxnLstList).build());
		CARD_TRANSACTION_RESPONSE_MAP.put("987654321:321", CardTransactionResponse.builder().transactionList(trxnLstList1).build());
		CARD_TRANSACTION_RESPONSE_MAP.put("000022224444:444", CardTransactionResponse.builder().transactionList(trxnLstList2).build());
		CARD_TRANSACTION_RESPONSE_MAP.put("0000000018707728:728", CardTransactionResponse.builder().transactionList(trxnLstList3).build());

		//////////////////////////////////////////////// Account Transaction Data Static Response///////////////////////////////////////////////////////

		List<TransactionDetail> accountTransactionDetails1 = new ArrayList<>();
		accountTransactionDetails1.add(TransactionDetail.builder().accountNumber("126000110006080000000").transactionId(9000001).transactionCode(562)
				.transactionDateGregorian("2024-03-15").transactionDateHijri("1445-09-05").transactionTime("11:46:41")
				.transactionAmount(TransactionAmount.builder().amount(-1000).currency("SAR").build())
				.transactionBalanceAmount(TransactionAmount.builder().amount(1000.58).currency("SAR").build()).transactionDescription("تحويل")
				.transactionChannelId("W").transactionRemarks("W-/TOACCT/12600608016042276TOAHMED-").appliedExchangeRate(1)
				.beneficiaryDetails(BeneficiaryDetails.builder().beneficiaryName("Ali khaled Samer").beneficiaryBankAccount("126000010006086040000")
						.beneficiaryAmount(BeneficiaryAmount.builder().amount(1000).currency("SAR").build()).build())
				.transactionBranchCode(12600).build());

		TrxnLst accTrxnLst1 = TrxnLst.builder().availableBalance(1020.3).cicNumber("123456789").unclearedBalance(100.2)
				.transactionDetails(accountTransactionDetails1).build();

		ACCOUNT_TRANSACTION_RESPONSE_MAP.put("123456789:126000110006080000000",
				AccountTransactionResponse.builder().trxnLstList(Arrays.asList(accTrxnLst1)).build());

		// Additional Transactions
		// You can add additional transactions in a similar manner

		List<TransactionDetail> accTransactionDetails2 = new ArrayList<>();
		accTransactionDetails2.add(TransactionDetail.builder().accountNumber("126000110006080000001").transactionId(9000002).transactionCode(563)
				.transactionDateGregorian("2024-03-11").transactionDateHijri("1445-09-01").transactionTime("17:33:09")
				.transactionAmount(TransactionAmount.builder().amount(-1000).currency("SAR").build())
				.transactionBalanceAmount(TransactionAmount.builder().amount(2000.58).currency("SAR").build()).transactionDescription("تحويل")
				.transactionChannelId("W").transactionRemarks("W-/TOACCT/12600608016042276TOAHMED-").appliedExchangeRate(1)
				.beneficiaryDetails(
						BeneficiaryDetails.builder().beneficiaryName("AHMED SAID ATTIA ATTIA").beneficiaryBankAccount("126000010006086040000")
								.beneficiaryAmount(BeneficiaryAmount.builder().amount(1000).currency("SAR").build()).build())
				.transactionBranchCode(12600).build());

		TrxnLst accTrxnLst2 = TrxnLst.builder().availableBalance(1020.3).cicNumber("987654321").unclearedBalance(100.2)
				.transactionDetails(accTransactionDetails2).build();

		ACCOUNT_TRANSACTION_RESPONSE_MAP.put("987654321:126000110006080000001",
				AccountTransactionResponse.builder().trxnLstList(Arrays.asList(accTrxnLst2)).build());

		List<TransactionDetail> accTransactionDetails3 = new ArrayList<>();
		accTransactionDetails3.add(TransactionDetail.builder().accountNumber("126000110006080000002").transactionId(9000003).transactionCode(564)
				.transactionDateGregorian("2024-03-05").transactionDateHijri("1445-08-24").transactionTime("13:20:37")
				.transactionAmount(TransactionAmount.builder().amount(-7000).currency("SAR").build())
				.transactionBalanceAmount(TransactionAmount.builder().amount(3000.58).currency("SAR").build()).transactionDescription("تحويل")
				.transactionChannelId("W").transactionRemarks("W-/TOACCT/12600608016042276TOAHMED-").appliedExchangeRate(1)
				.beneficiaryDetails(
						BeneficiaryDetails.builder().beneficiaryName("AHMED SAID ATTIA ATTIA").beneficiaryBankAccount("126000010006086040000")
								.beneficiaryAmount(BeneficiaryAmount.builder().amount(7000).currency("SAR").build()).build())
				.transactionBranchCode(12600).build());

		TrxnLst accTrxnLst3 = TrxnLst.builder().availableBalance(1020.3).cicNumber("000022224444").unclearedBalance(100.2)
				.transactionDetails(accTransactionDetails3).build();

		ACCOUNT_TRANSACTION_RESPONSE_MAP.put("000022224444:126000110006080000002",
				AccountTransactionResponse.builder().trxnLstList(Arrays.asList(accTrxnLst3)).build());

		// Transaction 4
		List<TransactionDetail> accTransactionDetails4 = new ArrayList<>();
		accTransactionDetails4.add(TransactionDetail.builder().accountNumber("126000110006080000003").transactionId(9000004).transactionCode(565)
				.transactionDateGregorian("2024-03-02").transactionDateHijri("1445-08-21").transactionTime("19:07:09")
				.transactionAmount(TransactionAmount.builder().amount(10000).currency("SAR").build())
				.transactionBalanceAmount(TransactionAmount.builder().amount(10000.58).currency("SAR").build()).transactionDescription("تحويل")
				.transactionChannelId("W").transactionRemarks("W-/FRACCT/12600608016042276FRAHMED").appliedExchangeRate(1)
				.beneficiaryDetails(
						BeneficiaryDetails.builder().beneficiaryAmount(BeneficiaryAmount.builder().amount(0).currency("SAR").build()).build())
				.transactionBranchCode(12600).build());

		TrxnLst accTrxnLst4 = TrxnLst.builder().availableBalance(1020.3).cicNumber("0000000018707728").unclearedBalance(100.2)
				.transactionDetails(accTransactionDetails4).build();

		ACCOUNT_TRANSACTION_RESPONSE_MAP.put("0000000018707728:126000110006080000003",
				AccountTransactionResponse.builder().trxnLstList(Arrays.asList(accTrxnLst4)).build());

	}

	/////////////////////////////////////////////////// Static Methods///////////////////////////////////////

	private static GreetingDataResponse getGreetingDataResponse(String cicNumber) {
		return GREETING_DATA_RESPONSE_MAP.get(cicNumber);
	}

	public static CardTransactionResponse getCustomerCreditCardTransactions(String customerCIC, String lang, String cardSequence) {
		String key = customerCIC + ":" + cardSequence;
		return CARD_TRANSACTION_RESPONSE_MAP.get(key);
	}

	public static CustomerDataResponse getCustomerDataResponse(String customerCic) {
		return CUSTOMER_DATA_RESPONSE_MAP.get(customerCic);
	}

	public AccountTransactionResponse getCustomerAccountTransactions(String customerCIC, String lang, String accountNumber) {
		return ACCOUNT_TRANSACTION_RESPONSE_MAP.get(customerCIC + ":" + accountNumber);
	}

	private static AccountResponse buildAccountResponse(String accountNumber, String customerCIC) {
		GreetingDataResponse dataResponse = getGreetingDataResponse(customerCIC);
		String[] accountTypes = { "Saving Account", "Current Account" };
		String randomAccountType = accountTypes[new Random().nextInt(accountTypes.length)];
		return AccountResponse.builder().accountNumber(accountNumber)
				.availableBalance(AvailableBalance.builder().amount(1.25).currency("SAR").build())
				.ledgerBalance(LedgerBalance.builder().amount(1.25).currency("SAR").build()).showFlag("Y").iban("SA3580000126608114010589")
				.accountStatus("ACTIVE").dailyLimit(DailyLimit.builder().amount(75000).currency("SAR").build())
				.consumedDailyLimit(ConsumedDailyLimit.builder().amount(0).currency("SAR").build())
				.remainingDailyLimit(RemainingDailyLimit.builder().amount(75000).currency("SAR").build())
				.socialTrxnLimit(SocialTrxnLimit.builder().amount(200).currency("SAR").build()).favoriteFlg("N").acctIconFlg("0")
				.acctName(dataResponse.getFirstNameEn() + " " + dataResponse.getSecondNameEn() + " " + dataResponse.getThirdNameEn())
				.acctOpeningDate("2022-08-01").acctType(randomAccountType).acctBranch("12600")
				.acctSubcategory("0160 Hassad Digital account monthly profit  minimum balance in month").build();
	}

	///////////////////////////////// general method/////////////////////////////

	public DataResponse getGreetingMessage(GreetingDataRequest body) throws JsonProcessingException {
		DataResponse dataResponse = new DataResponse();

		GreetingDataResponse greetingDataResponse = getGreetingDataResponse(body.getCicNumber());
		AgentRequest request = new AgentRequest();

		request.setSessionId(getSessionId(body.getCicNumber()));
		request.setCustomerCic(greetingDataResponse.getCicNumber());
		request.setUserText(toJsonProperty(greetingDataResponse));

		AgentResponse response = RestClientService.getPostObject(request, body.getLanguage());
		dataResponse.setText(response.getAgentText());
		dataResponse.setBase46(response.getAgentAudio());
		dataResponse.setSessionId(request.getSessionId());
		return dataResponse;
	}

	public DataResponse getBase46(DataRequest dataRequest) throws IOException {
		DataResponse dataResponse = new DataResponse();

		AgentRequest request = new AgentRequest();
		request.setSessionId(dataRequest.getSessionId());
		request.setCustomerCic(dataRequest.getCustomerCiC());
		request.setUserAudio("data:audio/mp3;base64," + dataRequest.getText());

		AgentResponse response = RestClientService.getPostObject(request, dataRequest.getLanguage());
		dataResponse.setText(response.getAgentText());
		dataResponse.setBase46(response.getAgentAudio());

		return dataResponse;
	}

	public DataResponse getDataResponse(DataRequest dataRequest) {
		DataResponse dataResponse = new DataResponse();

		AgentRequest request = new AgentRequest();
		request.setSessionId(dataRequest.getSessionId());
		request.setCustomerCic(dataRequest.getCustomerCiC());
		request.setUserText(dataRequest.getText());
		AgentResponse response = RestClientService.getPostObject(request, dataRequest.getLanguage());
		dataResponse.setText(response.getAgentText());
		dataResponse.setBase46(response.getAgentAudio());
		return dataResponse;
	}

	//////////////////////////// common////////////////////////
	private String toJsonProperty(Object object) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

	}

	private String getSessionId(String cic) {
		sessionId = cic + UUID.randomUUID().toString();
		return sessionId;
	}
	////////////////////////////////////// Not Important ///////////////////

	public CardTransactionResponse getCardInfo(CardTransactionRequest cardTransactionRequest) {
		List<TrxnLst> trxnLstList = new ArrayList<>();
		List<TransactionDetail> transactionDetails = new ArrayList<>();

		transactionDetails.add(TransactionDetail.builder().transactionReferenceNumber(920547953)
				.transactionAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).transactionDateGregorian("2024-02-24")
				.transactionDateHijri("1445-08-14").transactionTime("17:11:42").transactionChannelId("OTHER").transactionAuthStatus("AUTH")
				.authAmount(TransactionAmount.builder().amount(150).currency("SAR").build())
				.billingAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).merchantName("ANNUAL FEE").merchantCountry("SAU")
				.loadDate("2024-02-24").settlementAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
				.transactionRemarks("ANNUAL FEE").settlementDate("2263-08-31").billDate("2024-02-24").build());

		trxnLstList.add(TrxnLst.builder().availableBalance(1020.3).cicNumber(cardTransactionRequest.getCicNumber()).unclearedBalance(100.2)
				.transactionDetails(transactionDetails).build());

		return CardTransactionResponse.builder().transactionList(trxnLstList).build();
	}

	public AccountTransactionResponse getAccountInfo(AccountTransactionRequest accountTransactionRequest) {
		List<TrxnLst> trxnLstList = new ArrayList<>();
		List<TransactionDetail> transactionDetails = new ArrayList<>();

		transactionDetails.add(TransactionDetail.builder().transactionReferenceNumber(920547953)
				.transactionAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).transactionDateGregorian("2024-02-24")
				.transactionDateHijri("1445-08-14").transactionTime("17:11:42").transactionChannelId("OTHER").transactionAuthStatus("AUTH")
				.authAmount(TransactionAmount.builder().amount(150).currency("SAR").build())
				.billingAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).merchantName("ANNUAL FEE").merchantCountry("SAU")
				.loadDate("2024-02-24").settlementAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
				.transactionRemarks("ANNUAL FEE").settlementDate("2263-08-31").billDate("2024-02-24").build());

		trxnLstList.add(TrxnLst.builder().availableBalance(1020.3).cicNumber(accountTransactionRequest.getCicNumber()).unclearedBalance(100.2)
				.transactionDetails(transactionDetails).build());

		return AccountTransactionResponse.builder().trxnLstList(trxnLstList).build();
	}

}
