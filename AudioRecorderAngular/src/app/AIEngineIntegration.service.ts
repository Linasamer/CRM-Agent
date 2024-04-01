import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import { RestEndpoints } from './eunms/RestEndpoints';
import { DataResponseModel } from './model/data-response.model';
import { GreetingResponse } from './model/greetingResponse.model';

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
        return throwError(error); // Re-throwing error to be caught by the caller
      })
    );
}
  

  voiceToText(body: any) {
    return this.httpClient.post(RestEndpoints.POST_VOICE_TO_TEXT, body).subscribe(
      (res) => {console.log(res)},
      (error) =>{console.log(error)}
    );
  }
  textToVoice(body: any) {
    return this.httpClient.post(RestEndpoints.POST_TEXT_TO_VOICE, body).subscribe(
      (res) => { console.log(res)},
      (error) =>{console.log(error)}
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
  }


