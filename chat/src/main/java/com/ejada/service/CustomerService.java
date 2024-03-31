package com.ejada.service;

import com.ejada.models.requests.AccountTransactionRequest;
import com.ejada.models.requests.CardTransactionRequest;
import com.ejada.models.requests.CustomerDataRequest;
import com.ejada.models.requests.DataRequest;
import com.ejada.models.requests.GreetingDataRequest;
import com.ejada.models.responses.AccountResponse;
import com.ejada.models.responses.AccountTransactionResponse;
import com.ejada.models.responses.AccountsLstResponse;
import com.ejada.models.responses.AvailableBalance;
import com.ejada.models.responses.CardTransactionResponse;
import com.ejada.models.responses.ConsumedDailyLimit;
import com.ejada.models.responses.CreditCard;
import com.ejada.models.responses.CreditCardLst;
import com.ejada.models.responses.CustomerDataResponse;
import com.ejada.models.responses.DailyLimit;
import com.ejada.models.responses.DataResponse;
import com.ejada.models.responses.GreetingDataResponse;
import com.ejada.models.responses.LedgerBalance;
import com.ejada.models.responses.MarketingMessage;
import com.ejada.models.responses.Notification;
import com.ejada.models.responses.RecPgCtrlOutResponse;
import com.ejada.models.responses.RemainingDailyLimit;
import com.ejada.models.responses.RewardPoints;
import com.ejada.models.responses.SocialTrxnLimit;
import com.ejada.models.responses.TotalBal;
import com.ejada.models.responses.TransactionAmount;
import com.ejada.models.responses.TransactionDetail;
import com.ejada.models.responses.TrxnLst;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;


@Singleton
@RequiredArgsConstructor
public class CustomerService {



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

    public GreetingDataResponse getGreetingMessage(GreetingDataRequest body) {
        return buildGreetingDataResponse(body);
    }

    public CustomerDataResponse getCustomerData(String customerCic) {
        return buildCustomerDataResponse(customerCic);
    }



    private  GreetingDataResponse buildGreetingDataResponse(GreetingDataRequest body){
        HashMap<String, GreetingDataResponse> greetingDataResponseHashMap = new HashMap<>();
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

        greetingDataResponseHashMap.put(firstCustomer.getCicNumber(), firstCustomer);
        greetingDataResponseHashMap.put(secondCustomer.getCicNumber(), secondCustomer);

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
                .Accounts(List.of(accountResponse))
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
                .creditCard(List.of(creditCard))
                .build();

        customerDataResponseHashMap.put("0000000018707728", CustomerDataResponse.builder()
                .profileNumber("0000000018707728")
                .accountsLst(accountsLstResponse)
                .creditCardLst(creditCardLst)
                .notificationsLst(List.of(notification))
                .marketingMsgssLst(List.of(marketingMessage))
                .rewardPoints(List.of(rewardPoints))
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
                .acctName("AHMED SAID ATTIA ATTIA")
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
                .Accounts(List.of(accountResponse2, accountResponse))
                .recPgCtrlOut(recPgCtrlOutResponse)
                .totalBal(totalBal)
                .build();

        AccountsLstResponse accountsLstResponse3 = AccountsLstResponse.builder()
                .Accounts(List.of(accountResponse2, accountResponse, accountResponse3))
                .recPgCtrlOut(recPgCtrlOutResponse)
                .totalBal(totalBal)
                .build();

        AccountsLstResponse accountsLstResponse4 = AccountsLstResponse.builder()
                .Accounts(List.of(accountResponse2, accountResponse,accountResponse3, accountResponse4))
                .recPgCtrlOut(recPgCtrlOutResponse)
                .totalBal(totalBal)
                .build();

        customerDataResponseHashMap.put("123456789", CustomerDataResponse.builder()
                .profileNumber("123456789")
                .accountsLst(accountsLstResponse3)
                .creditCardLst(creditCardLst)
                .notificationsLst(List.of(notification))
                .marketingMsgssLst(List.of(marketingMessage))
                .rewardPoints(List.of(rewardPoints))
                .build());

        customerDataResponseHashMap.put("987654321", CustomerDataResponse.builder()
                .profileNumber("987654321")
                .accountsLst(accountsLstResponse4)
                .creditCardLst(creditCardLst)
                .notificationsLst(List.of(notification))
                .marketingMsgssLst(List.of(marketingMessage))
                .rewardPoints(List.of(rewardPoints))
                .build());

        customerDataResponseHashMap.put("000022224444", CustomerDataResponse.builder()
                .profileNumber("000022224444")
                .accountsLst(accountsLstResponse2)
                .creditCardLst(creditCardLst)
                .notificationsLst(List.of(notification))
                .marketingMsgssLst(List.of(marketingMessage))
                .rewardPoints(List.of(rewardPoints))
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
        DataResponse dataResponse = getDataResponse(dataRequest);
        if(dataRequest.getLanguage().equals("en-US"))
        {
        Path fileName = Path.of( "src/main/resources/base46File.xml");
        dataResponse.setBase46(Files.readString(fileName));
        }
        else {
            Path fileName = Path.of( "src/main/resources/base46FileArabic.xml");
            dataResponse.setBase46(Files.readString(fileName));
        }
        return dataResponse;
    }

    public DataResponse getDataResponse(DataRequest dataRequest)  {
        DataResponse dataResponse = new DataResponse();
        if(dataRequest.getLanguage().equals("en-US")){
            dataResponse.setText( "Good Morning Customer");
        }
        else {
            dataResponse.setText( "و عليكم السلام");
        }
        return dataResponse;
    }


}