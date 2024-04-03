package com.code.secretary.service.customer;

import com.code.secretary.models.requests.AccountTransactionRequest;
import com.code.secretary.models.requests.AgentRequest;
import com.code.secretary.models.requests.CardTransactionRequest;
import com.code.secretary.models.requests.CustomerDataRequest;
import com.code.secretary.models.requests.DataRequest;
import com.code.secretary.models.requests.GreetingDataRequest;
import com.code.secretary.models.responses.AccountResponse;
import com.code.secretary.models.responses.AccountTransactionResponse;
import com.code.secretary.models.responses.AccountsLstResponse;
import com.code.secretary.models.responses.AgentResponse;
import com.code.secretary.models.responses.AvailableBalance;
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

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class CustomerService {

		private String sessionId;

    public CardTransactionResponse getCardInfo(CardTransactionRequest  cardTransactionRequest) {
        List<TrxnLst> trxnLstList = new ArrayList<>();
        List<TransactionDetail> transactionDetails = new ArrayList<>();

        transactionDetails.add(
                TransactionDetail.builder()
                        .transactionReferenceNumber(920547953)
                        .transactionAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
                        .transactionDateGregorian("2024-02-24")
                        .transactionDateHijri("1445-08-14")
                        .transactionTime("17:11:42")
                        .transactionChannelId("OTHER")
                        .transactionAuthStatus("AUTH")
                        .authAmount(TransactionAmount.builder().amount(150).currency("SAR").build())
                        .billingAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
                        .merchantName("ANNUAL FEE")
                        .merchantCity("SAU")
                        .LoadDate("2024-02-24")
                        .setlAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
                        .transactionRemarks("ANNUAL FEE")
                        .seltDate("2263-08-31")
                        .billDate("2024-02-24")
                        .build()
        );

        trxnLstList.add( TrxnLst.builder()
                .availableBalance(1020.3)
                .cicNumber(cardTransactionRequest.getCicNumber())
                .unclearedBalance(100.2)
                .transactionDetails(transactionDetails)
                .build());

        return CardTransactionResponse.builder()
                        .trxnLstList(trxnLstList).build();
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



    private  GreetingDataResponse buildGreetingDataResponse(GreetingDataRequest body){
        Map<String, GreetingDataResponse> greetingDataResponseHashMap = new HashMap<>();
        GreetingDataResponse firstCustomer = GreetingDataResponse.builder()
                .cicNumber("123456789")
                .firstNameEn("Ahmed")
                .secondNameEn("mohamed")
                .thirdNameEn("mahmoud")
                .lastNameEn("ali")
                .firstNameAr("احمد")
                .secondNameAr("محمد")
                .thirdNameAr("محمود")
                .lastNameAr("علي")
                .custSinceDt("2018-03-26")
                .idNumber("2449029186")
                .idType("2")
                .custStatus("ACTIVE")
                .idIssueDate("1439-05-21")
                .birthDate("1989-02-13")
                .gender("M")
                .build();

        GreetingDataResponse secondCustomer =GreetingDataResponse.builder()
                .cicNumber("987654321")
                .firstNameEn("Ali")
                .secondNameEn("Khaled")
                .thirdNameEn("Samer")
                .lastNameEn("ali")
                .firstNameAr("علي")
                .secondNameAr("خالد")
                .thirdNameAr("سامر")
                .lastNameAr("علي")
                .custSinceDt("2018-03-26")
                .idNumber("2449029186")
                .idType("2")
                .custStatus("ACTIVE")
                .idIssueDate("1439-05-21")
                .birthDate("1989-02-13")
                .gender("M")
                .build();

        GreetingDataResponse Customer3 =GreetingDataResponse.builder()
                .cicNumber("000022224444")
                .firstNameEn("Munira")
                .secondNameEn("Khaled")
                .thirdNameEn("Samer")
                .lastNameEn("ali")
                .firstNameAr("منيره")
                .secondNameAr("خالد")
                .thirdNameAr("سامر")
                .lastNameAr("علي")
                .custSinceDt("2018-03-26")
                .idNumber("2449029186")
                .idType("2")
                .custStatus("ACTIVE")
                .idIssueDate("1439-05-21")
                .birthDate("1989-02-13")
                .gender("M")
                .build();

        GreetingDataResponse Customer4 =GreetingDataResponse.builder()
                .cicNumber("0000000018707728")
                .firstNameEn("Omer")
                .secondNameEn("Khaled")
                .thirdNameEn("Samer")
                .lastNameEn("ali")
                .firstNameAr("عمر")
                .secondNameAr("خالد")
                .thirdNameAr("سامر")
                .lastNameAr("علي")
                .custSinceDt("2018-03-26")
                .idNumber("2449029186")
                .idType("2")
                .custStatus("ACTIVE")
                .idIssueDate("1439-05-21")
                .birthDate("1989-02-13")
                .gender("M")
                .build();

        greetingDataResponseHashMap.put(firstCustomer.getCicNumber(), firstCustomer);
        greetingDataResponseHashMap.put(secondCustomer.getCicNumber(), secondCustomer);
        greetingDataResponseHashMap.put(Customer3.getCicNumber(), Customer3);
        greetingDataResponseHashMap.put(Customer4.getCicNumber(), Customer4);

        return greetingDataResponseHashMap.get(body.getCicNumber());
    }


    private CustomerDataResponse buildCustomerDataResponse(String customerCic){
        HashMap<String, CustomerDataResponse> customerDataResponseHashMap = new HashMap<>();
        RecPgCtrlOutResponse recPgCtrlOutResponse = RecPgCtrlOutResponse.builder()
                .sentRecs(4)
                .matchedRecs(5)
                .complFlg("Y")
                .build();
        TotalBal totalBal = TotalBal.builder()
                .amount(1961.06)
                .currency("SAR")
                .build();
        AccountResponse accountResponse = AccountResponse.builder()
                .accountNumber("126000010006086060000")
                .accountStatus("ACTIVE")
                .availableBalance(AvailableBalance.builder().amount(9.1).currency("SAR").build())
                .ledgerBalance(LedgerBalance.builder().amount(9.1).currency("SAR").build())
                .showFlag("Y")
                .iban("SA5280000126608016062787")
                .atmCardNum("484783******0325")
                .dailyLimit(DailyLimit.builder().amount(75000).currency("SAR").build())
                .consumedDailyLimit(ConsumedDailyLimit.builder().amount(0).currency("SAR").build())
                .remainingDailyLimit(RemainingDailyLimit.builder().amount(75000).currency("SAR").build())
                .socialTrxnLimit(SocialTrxnLimit.builder().amount(200).currency("SAR").build())
                .favoriteFlg("N")
                .acctIconFlg("7")
                .acctName("AHMED SAID ATTIA ATTIA")
                .acctOpeningDate("2021-05-10")
                .acctType("Current Account (CR)")
                .acctBranch("12600")
                .acctSubcategory("0001 - C/A  - CR Bal. - Individuals - Local")
                .build();
        AccountsLstResponse accountsLstResponse = AccountsLstResponse.builder()
                .Accounts(Arrays.asList(accountResponse))
                .recPgCtrlOut(recPgCtrlOutResponse)
                .totalBal(totalBal)
                .build();

        Notification notification = Notification.builder()
                .notificationId(1)
                .notificationDate("2024-04-21")
                .notificationType("ID Expiry")
                .build();

        MarketingMessage marketingMessage = MarketingMessage.builder()
                .notificationId(1)
                .notificationDate("2024-04-21")
                .notificationType("ID Expiry")
                .build();

        RewardPoints rewardPoints = RewardPoints.builder()
                .availableBalance(175)
                .email("Ahmed.Saeed.Atya@gmail.com")
                .expiredPoints(0)
                .build();

        CreditCard creditCard = CreditCard.builder()
                .cardSeqNumber(28679726)
                .cardNumber("445827******0990")
                .prodDesc("VISA VIRTUAL")
                .build();
        CreditCardLst creditCardLst = CreditCardLst.builder()
                .recPgCtrlOut(recPgCtrlOutResponse)
                .creditCard(Arrays.asList(creditCard))
                .build();

        customerDataResponseHashMap.put("0000000018707728", CustomerDataResponse.builder()
                .profileNumber("0000000018707728")
                .accountsLst(accountsLstResponse)
                .creditCardLst(creditCardLst)
                .notificationsLst(Arrays.asList(notification))
                .marketingMsgssLst(Arrays.asList(marketingMessage))
                .rewardPoints(Arrays.asList(rewardPoints))
                .build());

        AccountResponse accountResponse2 = AccountResponse.builder()
                .accountNumber("126000010006086040000")
                .accountStatus("ACTIVE")
                .availableBalance(AvailableBalance.builder().amount(950.13).currency("SAR").build())
                .ledgerBalance(LedgerBalance.builder().amount(950.13).currency("SAR").build())
                .showFlag("Y")
                .iban("SA7680000126608016042276")
                .erNumber("24350888")
                .atmCardNum("484783******4732")
                .dailyLimit(DailyLimit.builder().amount(75000).currency("SAR").build())
                .consumedDailyLimit(ConsumedDailyLimit.builder().amount(0).currency("SAR").build())
                .remainingDailyLimit(RemainingDailyLimit.builder().amount(75000).currency("SAR").build())
                .socialTrxnLimit(SocialTrxnLimit.builder().amount(200).currency("SAR").build())
                .favoriteFlg("N")
                .acctIconFlg("1")
                .acctName("AHMED SAID ATTIA  ATTIA")
                .acctOpeningDate("2021-02-21")
                .acctType("Current Account (CR)")
                .acctBranch("12600")
                .acctSubcategory("0001 - C/A  - CR Bal. - Individuals - Local")
                .build();
        AccountResponse accountResponse3 = AccountResponse.builder()
                .accountNumber("126000110006080000000")
                .availableBalance(AvailableBalance.builder().amount(1000.58).currency("SAR").build())
                .ledgerBalance(LedgerBalance.builder().amount(1000.58).currency("SAR").build())
                .showFlag("Y")
                .iban("SA8780000126608110003844")
                .accountStatus("ACTIVE")
                .dailyLimit(DailyLimit.builder().amount(75000).currency("SAR").build())
                .consumedDailyLimit(ConsumedDailyLimit.builder().amount(0).currency("SAR").build())
                .remainingDailyLimit(RemainingDailyLimit.builder().amount(75000).currency("SAR").build())
                .socialTrxnLimit(SocialTrxnLimit.builder().amount(200).currency("SAR").build())
                .favoriteFlg("N")
                .acctIconFlg("0")
                .acctName("AHMED SAID ATTIA ATTIA")
                .acctOpeningDate("2023-08-17")
                .acctType("011 - Saving Account")
                .acctBranch("12600")
                .acctSubcategory("0170 - Million Saving Account")
                .build();

        AccountResponse accountResponse4 = AccountResponse.builder()
                .accountNumber("126000110006084010000")
                .availableBalance(AvailableBalance.builder().amount(1.25).currency("SAR").build())
                .ledgerBalance(LedgerBalance.builder().amount(1.25).currency("SAR").build())
                .showFlag("Y")
                .iban("SA3580000126608114010589")
                .accountStatus("ACTIVE")
                .dailyLimit(DailyLimit.builder().amount(75000).currency("SAR").build())
                .consumedDailyLimit(ConsumedDailyLimit.builder().amount(0).currency("SAR").build())
                .remainingDailyLimit(RemainingDailyLimit.builder().amount(75000).currency("SAR").build())
                .socialTrxnLimit(SocialTrxnLimit.builder().amount(200).currency("SAR").build())
                .favoriteFlg("N")
                .acctIconFlg("0")
                .acctName("AHMED SAID ATTIA ATTIA")
                .acctOpeningDate("2022-08-01")
                .acctType("011 - Saving Account")
                .acctBranch("12600")
                .acctSubcategory("0160 Hassad Digital account monthly profit  minimum balance in month")
                .build();
        AccountsLstResponse accountsLstResponse2 = AccountsLstResponse.builder()
                .Accounts(Arrays.asList(accountResponse2, accountResponse))
                .recPgCtrlOut(recPgCtrlOutResponse)
                .totalBal(totalBal)
                .build();

        AccountsLstResponse accountsLstResponse3 = AccountsLstResponse.builder()
                .Accounts(Arrays.asList(accountResponse2, accountResponse, accountResponse3))
                .recPgCtrlOut(recPgCtrlOutResponse)
                .totalBal(totalBal)
                .build();

        AccountsLstResponse accountsLstResponse4 = AccountsLstResponse.builder()
                .Accounts(Arrays.asList(accountResponse2, accountResponse,accountResponse3, accountResponse4))
                .recPgCtrlOut(recPgCtrlOutResponse)
                .totalBal(totalBal)
                .build();

        customerDataResponseHashMap.put("123456789", CustomerDataResponse.builder()
                .profileNumber("123456789")
                .accountsLst(accountsLstResponse3)
                .creditCardLst(creditCardLst)
                .notificationsLst(Arrays.asList(notification))
                .marketingMsgssLst(Arrays.asList(marketingMessage))
                .rewardPoints(Arrays.asList(rewardPoints))
                .build());

        customerDataResponseHashMap.put("987654321", CustomerDataResponse.builder()
                .profileNumber("987654321")
                .accountsLst(accountsLstResponse4)
                .creditCardLst(creditCardLst)
                .notificationsLst(Arrays.asList(notification))
                .marketingMsgssLst(Arrays.asList(marketingMessage))
                .rewardPoints(Arrays.asList(rewardPoints))
                .build());

        customerDataResponseHashMap.put("000022224444", CustomerDataResponse.builder()
                .profileNumber("000022224444")
                .accountsLst(accountsLstResponse2)
                .creditCardLst(creditCardLst)
                .notificationsLst(Arrays.asList(notification))
                .marketingMsgssLst(Arrays.asList(marketingMessage))
                .rewardPoints(Arrays.asList(rewardPoints))
                .build());

        return customerDataResponseHashMap.get(customerCic);
    }

    public AccountTransactionResponse getAccountInfo(AccountTransactionRequest accountTransactionRequest) {
        List<TrxnLst> trxnLstList = new ArrayList<>();
        List<TransactionDetail> transactionDetails = new ArrayList<>();

        transactionDetails.add(
                TransactionDetail.builder()
                        .transactionReferenceNumber(920547953)
                        .transactionAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
                        .transactionDateGregorian("2024-02-24")
                        .transactionDateHijri("1445-08-14")
                        .transactionTime("17:11:42")
                        .transactionChannelId("OTHER")
                        .transactionAuthStatus("AUTH")
                        .authAmount(TransactionAmount.builder().amount(150).currency("SAR").build())
                        .billingAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
                        .merchantName("ANNUAL FEE")
                        .merchantCity("SAU")
                        .LoadDate("2024-02-24")
                        .setlAmount(TransactionAmount.builder().amount(-150).currency("SAR").build())
                        .transactionRemarks("ANNUAL FEE")
                        .seltDate("2263-08-31")
                        .billDate("2024-02-24")
                        .build()
        );

        trxnLstList.add(TrxnLst.builder()
                .availableBalance(1020.3)
                .cicNumber(accountTransactionRequest.getCicNumber())
                .unclearedBalance(100.2)
                .transactionDetails(transactionDetails)
                .build());

        return AccountTransactionResponse.builder()
                .trxnLstList(trxnLstList).build();
    }

    public DataResponse getBase46(DataRequest dataRequest) throws IOException {
        DataResponse dataResponse = new DataResponse();
        
        AgentRequest request = new AgentRequest();
        request.setSessionId(dataRequest.getSessionId());
        request.setCustomerCic(dataRequest.getCustomerCiC());
        request.setUserAudio( "data:audio/mp3;base64," +dataRequest.getText());
        
        AgentResponse response = RestClientService.getPostObject(request, dataRequest.getLanguage());
        dataResponse.setText(response.getAgentText());
        dataResponse.setBase46(response.getAgentAudio());
        
        return dataResponse;
    }

    public DataResponse getDataResponse(DataRequest dataRequest)  {
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
    
    private String toJsonProperty(Object object) throws JsonProcessingException  {

    ObjectMapper objectMapper = new ObjectMapper();
//    objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

    }

    		private String getSessionId(String cic)   {
    			sessionId = cic + UUID.randomUUID().toString();
    			return sessionId ;
        }

}