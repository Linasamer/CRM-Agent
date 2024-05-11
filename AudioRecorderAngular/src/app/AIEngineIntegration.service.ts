import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { RestEndpoints } from './eunms/RestEndpoints';
import { DataResponseModel } from './model/data-response.model';
import { ProfileData } from './model/profileDataModelResponse/profileData-response.model';
import { TransactionResponse } from './model/TransactionsResponse/TransactionResponse.model';

@Injectable({
  providedIn: 'root'
})
export class AIEngineIntegrationService  {

  constructor(private httpClient: HttpClient) {
  }

  headers = new HttpHeaders({
    'x-correlation-id': '123',
    'Accept-Language': 'EN'
  });
  
  voiceToVoice(body: any) {
    return this.httpClient.post<DataResponseModel>(RestEndpoints.POST_VOICE_TO_VOICE, body)
    .pipe(
      map((res: DataResponseModel) => res),
      catchError(error => {
        console.log(error);
        return throwError(error);
      })
    );
}
  


  textToText(body: any): Observable<DataResponseModel> {
    return this.httpClient.post<DataResponseModel>(RestEndpoints.POST_TEXT_TO_TEXT, body)
      .pipe(
        map((res: DataResponseModel) => res),
        catchError(error => {
          console.log(error);
          return throwError(error); 
        })
      );
  }

  getGreetingData(body: any): Observable<DataResponseModel> {
    return this.httpClient.post<DataResponseModel>(RestEndpoints.POST_GET_CUSTOMER_GREETING_DATA, body)
      .pipe(
        map((res: DataResponseModel) => res),
        catchError(error => {
          console.log(error);
          return throwError(error);
        })
      );
  }

  getCustomerData(body: any): Observable<ProfileData> {
    return this.httpClient.get<ProfileData>(RestEndpoints.POST_GET_CUSTOMER_DATA_PROFILE + '?customerCIC=' + body)
      .pipe(
        map((res: ProfileData) => res),
        catchError(error => {
          console.log(error);
          return throwError(error);
        })
      );
  }

  getAccountTransactions(customerCic: any, accountNumber:any): Observable<TransactionResponse> {
    return this.httpClient.get<TransactionResponse>(RestEndpoints.GET_ACCOUNT_TRANSACTIONS + '?CustomerCIC=' + customerCic +
     '&AccountNumber=' + accountNumber, { headers: this.headers })
      .pipe(
        map((res: TransactionResponse) => res),
        catchError(error => {
          console.log(error);
          return throwError(error);
        })
      );
  }

  getCardTransactions(customerCic: any, cardSeq: any): Observable<TransactionResponse> {
    return this.httpClient.get<TransactionResponse>(RestEndpoints.GET_CARD_TRANSACTIONS + '?CustomerCIC=' +
     customerCic + '&CardSequence=' + cardSeq, { headers: this.headers })
      .pipe(
        map((res: TransactionResponse) => res),
        catchError(error => {
          console.log(error);
          return throwError(error);
        })
      );
  }

  }


