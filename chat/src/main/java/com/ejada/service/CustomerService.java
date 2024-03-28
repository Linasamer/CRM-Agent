package com.ejada.service;

import com.ejada.models.requests.AccountTransactionRequest;
import com.ejada.models.requests.CardTransactionRequest;
import com.ejada.models.requests.CustomerDataRequest;
import com.ejada.models.requests.GreetingDataRequest;
import com.ejada.models.responses.AccountResponse;
import com.ejada.models.responses.AccountTransactionResponse;
import com.ejada.models.responses.AccountsLstResponse;
import com.ejada.models.responses.CardTransactionResponse;
import com.ejada.models.responses.CreditCard;
import com.ejada.models.responses.CreditCardLst;
import com.ejada.models.responses.CustomerDataResponse;
import com.ejada.models.responses.GreetingDataResponse;
import com.ejada.models.responses.MarketingMessage;
import com.ejada.models.responses.Notification;
import com.ejada.models.responses.RecPgCtrlOutResponse;
import com.ejada.models.responses.RewardPoints;
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
        return buildGreetingDataResponse();
    }

    public CustomerDataResponse getCustomerData(CustomerDataRequest body) {
        return buildCustomerDataResponse();
    }

    private GreetingDataResponse buildGreetingDataResponse(){
        return GreetingDataResponse.builder()
                .cicNumber("1000000625")
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
    }

    private CustomerDataResponse buildCustomerDataResponse(){
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
                .accountNumber("126000010006086040000")
                .accountStatus("ACTIVE")
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

        return CustomerDataResponse.builder()
                .profileNumber("0000000018707728")
                .accountsLst(accountsLstResponse)
                .creditCardLst(creditCardLst)
                .notificationsLst(List.of(notification))
                .marketingMsgssLst(List.of(marketingMessage))
                .rewardPoints(List.of(rewardPoints))
                .build();
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

        trxnLstList.add( TrxnLst.builder()
                .availableBalance(1020.3)
                .cicNumber(accountTransactionRequest.getCicNumber())
                .unclearedBalance(100.2)
                .transactionDetails(transactionDetails)
                .build());

        return AccountTransactionResponse.builder()
                .trxnLstList(trxnLstList).build();
    }

    public String getBase46(String text) throws IOException {
        Path fileName = Path.of( "src/main/resources/base46File.xml");
        String base64 = Files.readString(fileName);
        return base64;
    }

}