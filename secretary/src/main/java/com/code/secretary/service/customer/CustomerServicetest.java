package com.code.secretary.service.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.code.secretary.models.requests.AccountTransactionRequest;
import com.code.secretary.models.requests.AgentRequest;
import com.code.secretary.models.requests.CardTransactionRequest;
import com.code.secretary.models.requests.DataRequest;
import com.code.secretary.models.requests.GreetingDataRequest;
import com.code.secretary.models.responses.AccountResponse;
import com.code.secretary.models.responses.AccountTransactionDetail;
import com.code.secretary.models.responses.AccountTransactionResponse;
import com.code.secretary.models.responses.AccountTrxnLst;
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
public class CustomerServicetest {

	private String sessionId;

	public CardTransactionResponse getCardInfo(CardTransactionRequest cardTransactionRequest) {
		TrxnLst trxnLstList = new TrxnLst();
		List<TransactionDetail> transactionDetails = new ArrayList<>();

		transactionDetails.add(TransactionDetail.builder().transactionReferenceNumber(920547953)
				.transactionAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).transactionDateGregorian("2024-02-24")
				.transactionDateHijri("1445-08-14").transactionTime("17:11:42").transactionChannelId("OTHER").transactionAuthStatus("AUTH")
				.authAmount(TransactionAmount.builder().amount(150).currency("SAR").build())
				.billingAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).merchantName("ANNUAL FEE").merchantCountry("SAU")
				.loadDate("2024-02-24").settlementAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
				.transactionRemarks("ANNUAL FEE").settlementDate("2263-08-31").billDate("2024-02-24").build());

		trxnLstList = TrxnLst.builder().transactionDetails(transactionDetails).build();

		return CardTransactionResponse.builder().transactionList(trxnLstList).build();
	}

	public DataResponse getGreetingMessage(GreetingDataRequest body) throws JsonProcessingException {
		DataResponse dataResponse = new DataResponse();

		GreetingDataResponse greetingDataResponse = buildGreetingDataResponse(body);
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

	public CustomerDataResponse getCustomerData(String customerCic) {
		return buildCustomerDataResponse(customerCic);
	}

	private GreetingDataResponse buildGreetingDataResponse(GreetingDataRequest body) {
		Map<String, GreetingDataResponse> greetingDataResponseHashMap = new HashMap<>();
		GreetingDataResponse firstCustomer = GreetingDataResponse.builder().cicNumber("0000000018707728").firstNameEn("Ahmed").secondNameEn("mohamed")
				.thirdNameEn("mahmoud").lastNameEn("ali").firstNameAr("احمد").secondNameAr("محمد").thirdNameAr("محمود").lastNameAr("علي")
				.custSinceDt("2018-03-26").idNumber("2449029186").idType("2").custStatus("ACTIVE").idIssueDate("1439-05-21").birthDate("1989-02-13")
				.gender("M").build();

		GreetingDataResponse secondCustomer = GreetingDataResponse.builder().cicNumber("123456789").firstNameEn("Ali").secondNameEn("Khaled")
				.thirdNameEn("Samer").lastNameEn("ali").firstNameAr("علي").secondNameAr("خالد").thirdNameAr("سامر").lastNameAr("علي")
				.custSinceDt("2018-03-26").idNumber("2449029186").idType("2").custStatus("ACTIVE").idIssueDate("1439-05-21").birthDate("1989-02-13")
				.gender("M").build();

		GreetingDataResponse Customer3 = GreetingDataResponse.builder().cicNumber("987654321").firstNameEn("Munira").secondNameEn("Khaled")
				.thirdNameEn("Samer").lastNameEn("ali").firstNameAr("منيره").secondNameAr("خالد").thirdNameAr("سامر").lastNameAr("علي")
				.custSinceDt("2018-03-26").idNumber("2449029186").idType("2").custStatus("ACTIVE").idIssueDate("1439-05-21").birthDate("1989-02-13")
				.gender("M").build();

		GreetingDataResponse Customer4 = GreetingDataResponse.builder().cicNumber("000022224444").firstNameEn("Omer").secondNameEn("Khaled")
				.thirdNameEn("Samer").lastNameEn("ali").firstNameAr("عمر").secondNameAr("خالد").thirdNameAr("سامر").lastNameAr("علي")
				.custSinceDt("2018-03-26").idNumber("2449029186").idType("2").custStatus("ACTIVE").idIssueDate("1439-05-21").birthDate("1989-02-13")
				.gender("M").build();

		greetingDataResponseHashMap.put(firstCustomer.getCicNumber(), firstCustomer);
		greetingDataResponseHashMap.put(secondCustomer.getCicNumber(), secondCustomer);
		greetingDataResponseHashMap.put(Customer3.getCicNumber(), Customer3);
		greetingDataResponseHashMap.put(Customer4.getCicNumber(), Customer4);

		return greetingDataResponseHashMap.get(body.getCicNumber());
	}

	private CustomerDataResponse buildCustomerDataResponse(String customerCic) {
		HashMap<String, CustomerDataResponse> customerDataResponseHashMap = new HashMap<>();
		RecPgCtrlOutResponse recPgCtrlOutResponse = RecPgCtrlOutResponse.builder().sentRecs(4).matchedRecs(5).complFlg("Y").build();

		Notification notification = Notification.builder().notificationId(1).notificationDate("2024-04-21").notificationType("ID Expiry").build();

		MarketingMessage marketingMessage = MarketingMessage.builder().notificationId(1).notificationDate("2024-04-21").notificationType("ID Expiry")
				.build();

		RewardPoints rewardPoints = RewardPoints.builder().availableBalance(175).email("Ahmed.Saeed.Atya@gmail.com").expiredPoints(0).build();
		CreditCard creditCard = CreditCard.builder().cardSeqNumber(123).cardNumber("4458271234560990").prodDesc("VISA VIRTUAL").build();
		CreditCard creditCard1 = CreditCard.builder().cardSeqNumber(456).cardNumber("4458271234560991").prodDesc("VISA VIRTUAL").build();
		CreditCard creditCard2 = CreditCard.builder().cardSeqNumber(789).cardNumber("4458271234560992").prodDesc("VISA VIRTUAL").build();
		CreditCard creditCard3 = CreditCard.builder().cardSeqNumber(101).cardNumber("4458271234560993").prodDesc("VISA VIRTUAL").build();
		CreditCardLst creditCardLst = CreditCardLst.builder().recPgCtrlOut(recPgCtrlOutResponse).creditCard(Arrays.asList(creditCard)).build();
		CreditCardLst creditCardLst1 = CreditCardLst.builder().recPgCtrlOut(recPgCtrlOutResponse).creditCard(Arrays.asList(creditCard1)).build();
		CreditCardLst creditCardLst2 = CreditCardLst.builder().recPgCtrlOut(recPgCtrlOutResponse).creditCard(Arrays.asList(creditCard2)).build();
		CreditCardLst creditCardLst3 = CreditCardLst.builder().recPgCtrlOut(recPgCtrlOutResponse).creditCard(Arrays.asList(creditCard3)).build();

		TotalBal totalBal = TotalBal.builder().amount(1961.06).currency("SAR").build();
		AccountResponse accountResponse = AccountResponse.builder().accountNumber("126000110006080000000").accountStatus("ACTIVE")
				.availableBalance(AvailableBalance.builder().amount(9.1).currency("SAR").build())
				.ledgerBalance(LedgerBalance.builder().amount(9.1).currency("SAR").build()).showFlag("Y").iban("SA5280000126608016062787")
				.atmCardNum("484783******0325").dailyLimit(DailyLimit.builder().amount(75000).currency("SAR").build())
				.consumedDailyLimit(ConsumedDailyLimit.builder().amount(0).currency("SAR").build())
				.remainingDailyLimit(RemainingDailyLimit.builder().amount(75000).currency("SAR").build())
				.socialTrxnLimit(SocialTrxnLimit.builder().amount(200).currency("SAR").build()).favoriteFlg("N").acctIconFlg("7")
				.acctName("AHMED SAID ATTIA ATTIA").acctOpeningDate("2021-05-10").acctType("Current Account (CR)").acctBranch("12600")
				.acctSubcategory("0001 - C/A  - CR Bal. - Individuals - Local").build();
		AccountsLstResponse accountsLstResponse = AccountsLstResponse.builder().Accounts(Arrays.asList(accountResponse))
				.recPgCtrlOut(recPgCtrlOutResponse).totalBal(totalBal).build();

		AccountResponse accountResponse2 = AccountResponse.builder().accountNumber("126000110006080000001").accountStatus("ACTIVE")
				.availableBalance(AvailableBalance.builder().amount(950.13).currency("SAR").build())
				.ledgerBalance(LedgerBalance.builder().amount(950.13).currency("SAR").build()).showFlag("Y").iban("SA7680000126608016042276")
				.erNumber("24350888").atmCardNum("484783******4732").dailyLimit(DailyLimit.builder().amount(75000).currency("SAR").build())
				.consumedDailyLimit(ConsumedDailyLimit.builder().amount(0).currency("SAR").build())
				.remainingDailyLimit(RemainingDailyLimit.builder().amount(75000).currency("SAR").build())
				.socialTrxnLimit(SocialTrxnLimit.builder().amount(200).currency("SAR").build()).favoriteFlg("N").acctIconFlg("1")
				.acctName("Omer Khaled Samer").acctOpeningDate("2021-02-21").acctType("Current Account (CR)").acctBranch("12600")
				.acctSubcategory("0001 - C/A  - CR Bal. - Individuals - Local").build();
		AccountResponse accountResponse3 = AccountResponse.builder().accountNumber("126000110006080000002")
				.availableBalance(AvailableBalance.builder().amount(1000.58).currency("SAR").build())
				.ledgerBalance(LedgerBalance.builder().amount(1000.58).currency("SAR").build()).showFlag("Y").iban("SA8780000126608110003844")
				.accountStatus("ACTIVE").dailyLimit(DailyLimit.builder().amount(75000).currency("SAR").build())
				.consumedDailyLimit(ConsumedDailyLimit.builder().amount(0).currency("SAR").build())
				.remainingDailyLimit(RemainingDailyLimit.builder().amount(75000).currency("SAR").build())
				.socialTrxnLimit(SocialTrxnLimit.builder().amount(200).currency("SAR").build()).favoriteFlg("N").acctIconFlg("0")
				.acctName("Ali Khaled Samer").acctOpeningDate("2023-08-17").acctType("011 - Saving Account").acctBranch("12600")
				.acctSubcategory("0170 - Million Saving Account").build();

		AccountResponse accountResponse4 = AccountResponse.builder().accountNumber("1260001100060800000003")
				.availableBalance(AvailableBalance.builder().amount(1.25).currency("SAR").build())
				.ledgerBalance(LedgerBalance.builder().amount(1.25).currency("SAR").build()).showFlag("Y").iban("SA3580000126608114010589")
				.accountStatus("ACTIVE").dailyLimit(DailyLimit.builder().amount(75000).currency("SAR").build())
				.consumedDailyLimit(ConsumedDailyLimit.builder().amount(0).currency("SAR").build())
				.remainingDailyLimit(RemainingDailyLimit.builder().amount(75000).currency("SAR").build())
				.socialTrxnLimit(SocialTrxnLimit.builder().amount(200).currency("SAR").build()).favoriteFlg("N").acctIconFlg("0")
				.acctName("Mounira Khaled Samer").acctOpeningDate("2022-08-01").acctType("011 - Saving Account").acctBranch("12600")
				.acctSubcategory("0160 Hassad Digital account monthly profit  minimum balance in month").build();
		AccountsLstResponse accountsLstResponse2 = AccountsLstResponse.builder().Accounts(Arrays.asList(accountResponse2, accountResponse))
				.recPgCtrlOut(recPgCtrlOutResponse).totalBal(totalBal).build();

		AccountsLstResponse accountsLstResponse3 = AccountsLstResponse.builder()
				.Accounts(Arrays.asList(accountResponse3, accountResponse, accountResponse2)).recPgCtrlOut(recPgCtrlOutResponse).totalBal(totalBal)
				.build();

		AccountsLstResponse accountsLstResponse4 = AccountsLstResponse.builder()
				.Accounts(Arrays.asList(accountResponse4, accountResponse, accountResponse3, accountResponse2)).recPgCtrlOut(recPgCtrlOutResponse)
				.totalBal(totalBal).build();

		customerDataResponseHashMap.put("0000000018707728",
				CustomerDataResponse.builder().profileNumber("0000000018707728").accountsLst(accountsLstResponse).creditCardLst(creditCardLst)
						.notificationsLst(Arrays.asList(notification)).marketingMsgssLst(Arrays.asList(marketingMessage))
						.rewardPoints(Arrays.asList(rewardPoints)).build());
		customerDataResponseHashMap.put("000022224444",
				CustomerDataResponse.builder().profileNumber("000022224444").accountsLst(accountsLstResponse2).creditCardLst(creditCardLst1)
						.notificationsLst(Arrays.asList(notification)).marketingMsgssLst(Arrays.asList(marketingMessage))
						.rewardPoints(Arrays.asList(rewardPoints)).build());

		customerDataResponseHashMap.put("123456789",
				CustomerDataResponse.builder().profileNumber("123456789").accountsLst(accountsLstResponse3).creditCardLst(creditCardLst2)
						.notificationsLst(Arrays.asList(notification)).marketingMsgssLst(Arrays.asList(marketingMessage))
						.rewardPoints(Arrays.asList(rewardPoints)).build());

		customerDataResponseHashMap.put("987654321",
				CustomerDataResponse.builder().profileNumber("987654321").accountsLst(accountsLstResponse4).creditCardLst(creditCardLst3)
						.notificationsLst(Arrays.asList(notification)).marketingMsgssLst(Arrays.asList(marketingMessage))
						.rewardPoints(Arrays.asList(rewardPoints)).build());

		return customerDataResponseHashMap.get(customerCic);
	}

	public AccountTransactionResponse getAccountInfo(AccountTransactionRequest accountTransactionRequest) {
		AccountTrxnLst trxnLstList = new AccountTrxnLst();
		List<AccountTransactionDetail> transactionDetails = new ArrayList<>();

		transactionDetails.add(AccountTransactionDetail.builder()
				.transactionAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).transactionDateGregorian("2024-02-24")
				.transactionDateHijri("1445-08-14").transactionTime("17:11:42").transactionChannelId("OTHER").transactionAuthStatus("AUTH")
				.authAmount(TransactionAmount.builder().amount(150).currency("SAR").build())
				.billingAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).merchantName("ANNUAL FEE").merchantCountry("SAU")
				.loadDate("2024-02-24").settlementAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
				.transactionRemarks("ANNUAL FEE").settlementDate("2263-08-31").billDate("2024-02-24").build());

		trxnLstList = AccountTrxnLst.builder().availableBalance(1020.3).cicNumber(accountTransactionRequest.getCicNumber()).unclearedBalance(100.2)
				.transactionDetails(transactionDetails).build();

		return AccountTransactionResponse.builder().trxnLstList(trxnLstList).build();
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

	private String toJsonProperty(Object object) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		// objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

	}

	private String getSessionId(String cic) {
		sessionId = cic + UUID.randomUUID().toString();
		return sessionId;
	}

	/////////////////////////////

//	public CardTransactionResponse getCustomerCreditCardTransactions(String customerCIC, String lang, String cardSequence) {
//		TrxnLst trxnLstList = new TrxnLst();
//		TrxnLst trxnLstList1 = new TrxnLst();
//		TrxnLst trxnLstList2 = new TrxnLst();
//		TrxnLst trxnLstList3 = new TrxnLst();
//
//		List<TransactionDetail> transactionDetails = new ArrayList<>();
//		List<TransactionDetail> transactionDetails1 = new ArrayList<>();
//		List<TransactionDetail> transactionDetails2 = new ArrayList<>();
//		List<TransactionDetail> transactionDetails3 = new ArrayList<>();
//		TrxnLst trxnLst = new TrxnLst();
//		TrxnLst trxnLst1 = new TrxnLst();
//		TrxnLst trxnLst2 = new TrxnLst();
//		TrxnLst trxnLst3 = new TrxnLst();
//
//		TransactionDetail transactionDetail = new TransactionDetail();
//		TransactionDetail transactionDetail1 = new TransactionDetail();
//		TransactionDetail transactionDetail2 = new TransactionDetail();
//		TransactionDetail transactionDetail3 = new TransactionDetail();
//
//		Map<String, CardTransactionResponse> cardTransactionResponseMap = new HashMap<>();
//
//		transactionDetail = TransactionDetail.builder().transactionReferenceNumber(920547953L)
//				.transactionAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).transactionDateGregorian("2024-02-24")
//				.transactionDateHijri("1445-08-14").transactionTime("17:11:42").transactionChannelId("OTHER").transactionAuthStatus("AUTH")
//				.authAmount(TransactionAmount.builder().amount(150).currency("SAR").build())
//				.billingAmount(TransactionAmount.builder().amount(-150).currency("SAR").build()).merchantName("ANNUAL FEE").merchantCountry("SAU")
//				.loadDate("2024-02-24").settlementAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
//				.transactionRemarks("ANNUAL FEE").settlementDate("2263-08-31").billDate("2024-02-24").build();
//
//		// Additional transactions
//		// Second transaction
//		transactionDetail1 = TransactionDetail.builder().transactionReferenceNumber(920547954L)
//				.transactionAmount(TransactionAmount.builder().amount(-75).currency("SAR").build()).transactionDateGregorian("2024-03-01")
//				.transactionDateHijri("1445-08-20").transactionTime("10:30:15").transactionChannelId("ONLINE").transactionAuthStatus("AUTH")
//				.authAmount(TransactionAmount.builder().amount(75).currency("SAR").build())
//				.billingAmount(TransactionAmount.builder().amount(-75).currency("SAR").build()).merchantName("ONLINE STORE").merchantCountry("USA")
//				.loadDate("2024-03-01").settlementAmount(TransactionAmount.builder().amount(-75).currency("SAR").build())
//				.transactionRemarks("Online purchase").settlementDate("2024-03-02").billDate("2024-03-01").build();
//
//		// Third transaction
//		transactionDetail2 = TransactionDetail.builder().transactionReferenceNumber(920547955L)
//				.transactionAmount(TransactionAmount.builder().amount(-200).currency("SAR").build()).transactionDateGregorian("2024-03-05")
//				.transactionDateHijri("1445-08-24").transactionTime("14:00:00").transactionChannelId("ATM").transactionAuthStatus("AUTHORIZED")
//				.authAmount(TransactionAmount.builder().amount(200).currency("SAR").build())
//				.billingAmount(TransactionAmount.builder().amount(-200).currency("SAR").build()).merchantName("CASH WITHDRAWAL")
//				.merchantCountry("SAU").loadDate("2024-03-05").settlementAmount(TransactionAmount.builder().amount(-200).currency("SAR").build())
//				.transactionRemarks("ATM cash withdrawal").settlementDate("2024-03-05").billDate("2024-03-05").build();
//
//		// Fourth transaction
//		transactionDetail3 = TransactionDetail.builder().transactionReferenceNumber(920547956L)
//				.transactionAmount(TransactionAmount.builder().amount(-50).currency("SAR").build()).transactionDateGregorian("2024-03-07")
//				.transactionDateHijri("1445-08-26").transactionTime("09:15:30").transactionChannelId("POS").transactionAuthStatus("AUTHORIZED")
//				.authAmount(TransactionAmount.builder().amount(50).currency("SAR").build())
//				.billingAmount(TransactionAmount.builder().amount(-50).currency("SAR").build()).merchantName("SUPERMARKET").merchantCountry("SAU")
//				.loadDate("2024-03-07").settlementAmount(TransactionAmount.builder().amount(-50).currency("SAR").build())
//				.transactionRemarks("Grocery purchase").settlementDate("2024-03-07").billDate("2024-03-07").build();
//		transactionDetails.add(transactionDetail);
//		transactionDetails1.add(transactionDetail1);
//		transactionDetails2.add(transactionDetail2);
//		transactionDetails3.add(transactionDetail3);
//
//		// Transaction list
//		trxnLst = TrxnLst.builder().availableBalance(1020.3).cicNumber("123456789").unclearedBalance(100.2).transactionDetails(transactionDetails)
//				.build();
//		trxnLst1 = TrxnLst.builder().availableBalance(1020.3).cicNumber("987654321").unclearedBalance(100.2).transactionDetails(transactionDetails1)
//				.build();
//		trxnLst2 = TrxnLst.builder().availableBalance(1020.3).cicNumber("000022224444").unclearedBalance(100.2)
//				.transactionDetails(transactionDetails2).build();
//		trxnLst3 = TrxnLst.builder().availableBalance(1020.3).cicNumber("0000000018707728").unclearedBalance(100.2)
//				.transactionDetails(transactionDetails3).build();
//		trxnLstList = trxnLst;
//		trxnLstList1 = trxnLst1;
//		trxnLstList2 = trxnLst2;
//		trxnLstList3 = trxnLst3;
//
//		cardTransactionResponseMap.put(trxnLstList.getCicNumber() + ":" + "123",
//				CardTransactionResponse.builder().transactionList(trxnLstList).build());
//		cardTransactionResponseMap.put(trxnLstList1.getCicNumber() + ":" + "456",
//				CardTransactionResponse.builder().transactionList(trxnLstList1).build());
//		cardTransactionResponseMap.put(trxnLstList2.getCicNumber() + ":" + "789",
//				CardTransactionResponse.builder().transactionList(trxnLstList2).build());
//		cardTransactionResponseMap.put(trxnLstList3.getCicNumber() + ":" + "101",
//				CardTransactionResponse.builder().transactionList(trxnLstList3).build());
//
//		return cardTransactionResponseMap.get(customerCIC + ":" + cardSequence);
//	}

	public AccountTransactionResponse getCustomerAccountTransactions(String customerCIC, String lang, String accountNumber) {
		AccountTrxnLst trxnLstList = new AccountTrxnLst();
		AccountTrxnLst trxnLstList1 = new AccountTrxnLst();
		AccountTrxnLst trxnLstList2 = new AccountTrxnLst();
		AccountTrxnLst trxnLstList3 = new AccountTrxnLst();

		List<AccountTransactionDetail> transactionDetails = new ArrayList<>();
		List<AccountTransactionDetail> transactionDetails1 = new ArrayList<>();
		List<AccountTransactionDetail> transactionDetails2 = new ArrayList<>();
		List<AccountTransactionDetail> transactionDetails3 = new ArrayList<>();
		AccountTrxnLst trxnLst = new AccountTrxnLst();
		AccountTrxnLst trxnLst1 = new AccountTrxnLst();
		AccountTrxnLst trxnLst2 = new AccountTrxnLst();
		AccountTrxnLst trxnLst3 = new AccountTrxnLst();

		AccountTransactionDetail transactionDetail = new AccountTransactionDetail();
		AccountTransactionDetail transactionDetail1 = new AccountTransactionDetail();
		AccountTransactionDetail transactionDetail2 = new AccountTransactionDetail();
		AccountTransactionDetail transactionDetail3 = new AccountTransactionDetail();

		Map<String, AccountTransactionResponse> accountTransactionResponseMap = new HashMap<>();

		// Transaction 1
		transactionDetail = AccountTransactionDetail.builder().accountNumber("126000110006080000000").transactionId(9000001).transactionCode(562)
				.transactionDateGregorian("2024-03-15").transactionDateHijri("1445-09-05").transactionTime("11:46:41")
				.transactionAmount(TransactionAmount.builder().amount(-1000).currency("SAR").build())
				.transactionBalanceAmount(TransactionAmount.builder().amount(1000.58).currency("SAR").build()).transactionDescription("تحويل")
				.transactionChannelId("W").transactionRemarks("W-/TOACCT/12600608016042276TOAHMED-").appliedExchangeRate(1)
				.beneficiaryDetails(
						BeneficiaryDetails.builder().beneficiaryName("AHMED SAID ATTIA ATTIA").beneficiaryBankAccount("126000010006086040000")
								.beneficiaryAmount(BeneficiaryAmount.builder().amount(1000).currency("SAR").build()).build())
				.transactionBranchCode(12600).build();

		// Repeat the process for other transactions and add them to the list
		// Transaction 2
		transactionDetail1 = AccountTransactionDetail.builder().accountNumber("126000110006080000001").transactionId(9000001).transactionCode(562)
				.transactionDateGregorian("2024-03-11").transactionDateHijri("1445-09-01").transactionTime("17:33:09")
				.transactionAmount(TransactionAmount.builder().amount(-1000).currency("SAR").build())
				.transactionBalanceAmount(TransactionAmount.builder().amount(2000.58).currency("SAR").build()).transactionDescription("تحويل")
				.transactionChannelId("W").transactionRemarks("W-/TOACCT/12600608016042276TOAHMED-").appliedExchangeRate(1)
				.beneficiaryDetails(
						BeneficiaryDetails.builder().beneficiaryName("AHMED SAID ATTIA ATTIA").beneficiaryBankAccount("126000010006086040000")
								.beneficiaryAmount(BeneficiaryAmount.builder().amount(1000).currency("SAR").build()).build())
				.transactionBranchCode(12600).build();

		// Transaction 3
		transactionDetail2 = AccountTransactionDetail.builder().accountNumber("126000110006080000002").transactionId(9000001).transactionCode(562)
				.transactionDateGregorian("2024-03-05").transactionDateHijri("1445-08-24").transactionTime("13:20:37")
				.transactionAmount(TransactionAmount.builder().amount(-7000).currency("SAR").build())
				.transactionBalanceAmount(TransactionAmount.builder().amount(3000.58).currency("SAR").build()).transactionDescription("تحويل")
				.transactionChannelId("W").transactionRemarks("W-/TOACCT/12600608016042276TOAHMED-").appliedExchangeRate(1)
				.beneficiaryDetails(
						BeneficiaryDetails.builder().beneficiaryName("AHMED SAID ATTIA ATTIA").beneficiaryBankAccount("126000010006086040000")
								.beneficiaryAmount(BeneficiaryAmount.builder().amount(7000).currency("SAR").build()).build())
				.transactionBranchCode(12600).build();

		// Transaction 4
		transactionDetail3 = AccountTransactionDetail.builder().accountNumber("126000110006080000003").transactionId(9000001).transactionCode(562)
				.transactionDateGregorian("2024-03-02").transactionDateHijri("1445-08-21").transactionTime("19:07:09")
				.transactionAmount(TransactionAmount.builder().amount(10000).currency("SAR").build())
				.transactionBalanceAmount(TransactionAmount.builder().amount(10000.58).currency("SAR").build()).transactionDescription("تحويل")
				.transactionChannelId("W").transactionRemarks("W-/FRACCT/12600608016042276FRAHMED").appliedExchangeRate(1)
				.beneficiaryDetails(
						BeneficiaryDetails.builder().beneficiaryAmount(BeneficiaryAmount.builder().amount(0).currency("SAR").build()).build())
				.transactionBranchCode(12600).build();

		transactionDetails.add(transactionDetail);
		transactionDetails1.add(transactionDetail1);
		transactionDetails2.add(transactionDetail2);
		transactionDetails3.add(transactionDetail3);

		// Transaction list
		trxnLst = AccountTrxnLst.builder().availableBalance(1020.3).cicNumber("123456789").unclearedBalance(100.2).transactionDetails(transactionDetails)
				.build();
		trxnLst1 = AccountTrxnLst.builder().availableBalance(1020.3).cicNumber("987654321").unclearedBalance(100.2).transactionDetails(transactionDetails1)
				.build();
		trxnLst2 = AccountTrxnLst.builder().availableBalance(1020.3).cicNumber("000022224444").unclearedBalance(100.2)
				.transactionDetails(transactionDetails2).build();
		trxnLst3 = AccountTrxnLst.builder().availableBalance(1020.3).cicNumber("0000000018707728").unclearedBalance(100.2)
				.transactionDetails(transactionDetails3).build();
		trxnLstList = trxnLst;
		trxnLstList1 = trxnLst1;
		trxnLstList2 = trxnLst2;
		trxnLstList3 = trxnLst3;

		accountTransactionResponseMap.put(trxnLstList.getCicNumber() + ":" + "126000110006080000002",
				AccountTransactionResponse.builder().trxnLstList(trxnLstList).build());
		accountTransactionResponseMap.put(trxnLstList1.getCicNumber() + ":" + "126000110006080000003",
				AccountTransactionResponse.builder().trxnLstList(trxnLstList1).build());
		accountTransactionResponseMap.put(trxnLstList2.getCicNumber() + ":" + "126000110006080000001",
				AccountTransactionResponse.builder().trxnLstList(trxnLstList2).build());
		accountTransactionResponseMap.put(trxnLstList3.getCicNumber() + ":" + "126000110006080000000",
				AccountTransactionResponse.builder().trxnLstList(trxnLstList3).build());
		return accountTransactionResponseMap.get(customerCIC + ":" + accountNumber);
	}
}
