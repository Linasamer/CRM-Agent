import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { RestEndpoints } from './eunms/RestEndpoints';
import { DataResponseModel } from './model/data-response.model';
import { ProfileData } from './model/profileDataModelResponse/profileData-response.model';

@Injectable({
  providedIn: 'root'
})
export class AIEngineIntegrationService  {

  constructor(private httpClient: HttpClient) {
  }
  
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
  }


