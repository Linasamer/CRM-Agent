import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AudioRecordingService } from './audio-recording.service';
import { AIEngineIntegrationService } from './AIEngineIntegration.service';
import { CardTransactionRequest } from './model/card-transaction-request.model';
import { DataRequestModel } from './model/data-request.model';
import { DataResponseModel } from './model/data-response.model';
import { Language } from './model/language.model';
import { CustomerData } from './model/customer-data-request.model';
import { GreetingResponse } from './model/greetingResponse.model';
import { Message } from './model/messages.model';
declare var webkitSpeechRecognition: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isRecording = false;
  audioURL: string | null = null;
  results: any;
  vSearch = new webkitSpeechRecognition();
  recordedMessage: string = '';
  languages: Language[] = [];
  selectedLanguage: Language = { name: 'English', code: 'en-US' };
  Customers: CustomerData[] = [];
  selectedCustomer!: CustomerData ;
  voiceBlob: any;
  audioMessage: Boolean = false;
   name : string = '';

  bankImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSb_HeXq0uLKUHF1Hyynl-zXTfBADq8RuPxzgAvnhhG0A&s"
  userImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQnO7QLbgqxCIswhJPOO0750lzSDeSD4k5L_2ahBU9ew&s" 
  messages : Message[] = [];
  @ViewChild('audioPlayer') audioPlayer!: ElementRef<HTMLAudioElement>;
  @ViewChild('msgInput') messageInput!: ElementRef<HTMLInputElement>;
  cardTransactionRequest!: CardTransactionRequest;


  constructor(private audioRecordingService: AudioRecordingService, private cd: ChangeDetectorRef, 
    private aiEngineIntegrationService: AIEngineIntegrationService) {
  

     }

  ngOnInit() {
    this.languages = [
      { name: 'English', code: 'en-US' },
      { name: 'Arabic', code: 'ar-EG' }
    ];
    this.Customers = [
      {CICNumber: '123456789'},
      {CICNumber: '987654321'}

  ];
    this.audioRecordingService.audioBlob$.subscribe(blob => {
      this.audioURL = window.URL.createObjectURL(blob);
      this.voiceBlob = this.audioURL;
      this.audioPlayer.nativeElement.src = this.audioURL;
      this.cd.detectChanges();
    
    });
  }

  startRecording() {
    this.isRecording = true;
    this.audioMessage = true;
    this.audioRecordingService.startRecording();
    if ('webkitSpeechRecognition' in window) {
      this.vSearch.continuous = true;
      this.vSearch.interimresults = false;
      this.vSearch.lang = this.selectedLanguage.code;
      this.vSearch.start();
      this.vSearch.onresult = (e: any) => {
        console.log(e);
        // voiceHandler.value = e?.results[0][0]?.transcript;
        this.results = e.results[0][0].transcript;
        this.messageInput.nativeElement.value = this.results;
      

      };
    } else {
      alert('Your browser does not support voice recognition!');
    }
  }

  stopRecording() {
    this.isRecording = false;
    this.audioRecordingService.stopRecording();
    if ('webkitSpeechRecognition' in window) {
      console.log(this.results);

      this.vSearch.stop();
    }
  }



  addNewMessage(inputField: any) {
    const val = inputField.value?.trim()
    if (val.length) {
      if(this.audioMessage){
        this.messages.push({type: 'user', content: val, voiceNote: true, voiceContent: this.voiceBlob})
        const base64String = btoa(String.fromCharCode(...new Uint8Array(this.voiceBlob)));
        console.log("my base64: ", base64String)
        var result = '';
        const dataRequest = new DataRequestModel(base64String, this.selectedLanguage.code);
        this.aiEngineIntegrationService.voiceToVoice(dataRequest).subscribe(
          (response: DataResponseModel) => {
            console.log(response);
            this.base64toBlob(response.Base46, response.Text, 'application/x-www-form-urlencoded')
          },
          (error) => {
            console.log(error);
          }
        );      
        console.log(this.messages)
      }else{
        this.messages.push({type: 'user', content: val, voiceNote: false, voiceContent: ''})
        const dataRequest = new DataRequestModel(val, this.selectedLanguage.code);
         this.aiEngineIntegrationService.textToText(dataRequest).subscribe(
          (response: DataResponseModel) => {
            console.log(response);
            this.messages.push({type: 'bank', content: response.Text, voiceNote: false, voiceContent: ''});
          },
          (error) => {
            console.log(error);
          }
        );
       
      }
    }
    inputField.value = '';
    this.audioMessage = false
  }

  private base64toBlob(base64Data:any, text: any, contentType:any){
    contentType = contentType || '';
    const sliceSize = 1024;
    const byteCharacters = atob(base64Data);
    const bytesLength = byteCharacters.length;
    const slicesCount = Math.ceil(bytesLength / sliceSize);
    const byteArrays = new Array(slicesCount);

    for (let sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
      const begin = sliceIndex * sliceSize;
      const end = Math.min(begin + sliceSize, bytesLength);

      const bytes = new Array(end - begin);
      for (let offset = begin, i = 0; offset < end; ++i, ++offset) {
        bytes[i] = byteCharacters[offset].charCodeAt(0);
      }
      byteArrays[sliceIndex] = new Uint8Array(bytes);
    }
    var myBlob = new Blob(byteArrays, { type: contentType });
    var blobURL = window.URL.createObjectURL(myBlob);
    this.messages.push({type: 'bank', content: text, voiceNote: true, voiceContent: blobURL})
  }

  onCustomerChange(event: any) {
    console.log('Selected Customer:', this.selectedCustomer);
    const dataRequest = new CustomerData(this.selectedCustomer.CICNumber);
    this.aiEngineIntegrationService.getGreetingData(dataRequest).subscribe(
      (response: GreetingResponse) => {
        console.log(response);
        this.messages.push({type: 'bank', content: 'Hello ' + response.FirstNameEN + ' How can I help you?', voiceNote: false, voiceContent: ''});
      },
      (error) => {
        console.log(error);
      }
    );
   
 

  }

}


