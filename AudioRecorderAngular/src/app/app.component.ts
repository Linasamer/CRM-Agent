import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AudioRecordingService } from './audio-recording.service';
import { AIEngineIntegrationService } from './AIEngineIntegration.service';
import { CardTransactionRequest } from './model/card-transaction-request.model';
import { DataRequestModel } from './model/data-request.model';
import { DataResponseModel } from './model/data-response.model';
import { Language } from './model/language.model';
import { CustomerData } from './model/customer-data-request.model';
import { CustomerDatalList } from './model/customer-data-list.model';
import { Message } from './model/messages.model';
import { ProfileData } from './model/profileDataModelResponse/profileData-response.model';
import { WebSocketService } from './Service/webSocketService.service';
import { Subscription } from 'rxjs';
import { TransactionResponse } from './model/TransactionsResponse/TransactionResponse.model';
import { staticMap } from './model/static-map-object.model';
import { AccountTransactionResponse } from './model/TransactionsResponse/AccountTransactionResponse.model';

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
  Customers: CustomerDatalList[] = [];
  selectedCustomer!: CustomerData;
  voiceBlob: any;
  audioMessage: Boolean = false;
  name: string = '';
  base46audio: any;
  startFlag:boolean = false;
  userInfo !: ProfileData;
  sessionId!: string;
  accountTransaction !: AccountTransactionResponse;
  cardTransaction !: TransactionResponse;
  staticMapData = new Map<string, staticMap>();

  bankImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSb_HeXq0uLKUHF1Hyynl-zXTfBADq8RuPxzgAvnhhG0A&s"
  userImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQnO7QLbgqxCIswhJPOO0750lzSDeSD4k5L_2ahBU9ew&s"
  messages: Message[] = [];
  @ViewChild('audioPlayer') audioPlayer!: ElementRef<HTMLAudioElement>;
  @ViewChild('msgInput') messageInput!: ElementRef<HTMLInputElement>;

  cardTransactionRequest!: CardTransactionRequest;
  isStreaming = false;
  @ViewChild('audioPlayer2') audioPlayer2!: ElementRef<HTMLAudioElement>;
  audioSubscription!: Subscription;




  constructor(private audioRecordingService: AudioRecordingService, private cd: ChangeDetectorRef,
    private aiEngineIntegrationService: AIEngineIntegrationService
    , private webSocketService: WebSocketService
  ) {


  }

  ngOnInit() {
    this.languages = [
      { name: 'English', code: 'en-US' },
      { name: 'Arabic', code: 'ar-EG' }
    ];
    this.Customers = [
      { CICNumber: '123456789',  },
      { CICNumber: '0000000018707728' },
      { CICNumber: '000022224444' },
      { CICNumber: '987654321' }

    ];
    this.audioRecordingService.audioBlob$.subscribe(blob => {
      this.audioURL = window.URL.createObjectURL(blob);
      this.voiceBlob = this.audioURL;
      this.audioPlayer.nativeElement.src = this.audioURL;
      this.cd.detectChanges();

    });

    this.audioSubscription = this.webSocketService.audioStream.subscribe((audioChunk: Uint8Array) => {
      // If there is audio data, play it
      if (audioChunk) {
        this.playAudioChunk(audioChunk);
      }
    });

    this.staticMapData.set( '123456789', { accountNumber: '126000110006080006423', cardNumber: '2321' })
      this.staticMapData.set( '987654321', { accountNumber: '126000110006080009937', cardNumber: '2789' })
      this.staticMapData.set( '0000000018707728',{ accountNumber: '126000110006080000331', cardNumber: '2728' })
      this.staticMapData.set(  '000022224444' ,{ accountNumber: '126000110006080008552', cardNumber: '2444' })
   
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
      };
    } else {
      alert('Your browser does not support voice recognition!');
    }
  }

  async stopRecording() {
    this.isRecording = false;
   this.base46audio = await this.audioRecordingService.stopRecording();
    if ('webkitSpeechRecognition' in window) {
      console.log(this.results);

      this.vSearch.stop();
    }
    this.addRecordMessage();
  }

  addRecordMessage() {
      if (this.audioMessage) {
        this.messages.push({ type: 'user', content: '', voiceNote: true, voiceContent: this.voiceBlob })
        console.log("my base64: ", this.base46audio)
        const dataRequest = new DataRequestModel(this.base46audio, this.checkLanguage(), this.selectedCustomer.CICNumber, this.sessionId);
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
      } 
      this.audioMessage = false
  }


  addNewMessage(inputField: any) {
    const val = inputField.value?.trim()
    if (val.length) {
      if (this.audioMessage) {
        this.messages.push({ type: 'user', content: val, voiceNote: true, voiceContent: this.voiceBlob })
        console.log("my base64: ", this.base46audio)
        var result = '';
        const dataRequest = new DataRequestModel(this.base46audio, this.checkLanguage(), this.selectedCustomer.CICNumber, this.sessionId);
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
      } else {
        this.messages.push({ type: 'user', content: val, voiceNote: false, voiceContent: '' })
        const dataRequest = new DataRequestModel(val, this.checkLanguage(), this.selectedCustomer.CICNumber, this.sessionId);
        this.aiEngineIntegrationService.textToText(dataRequest).subscribe(
          (response: DataResponseModel) => {
            console.log(response);
            this.base64toBlob(response.Base46, response.Text, 'application/x-www-form-urlencoded')
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

  private base64toBlob(base64Data: any, text: any, contentType: any) {
    if (!(base64Data == null || base64Data == '')){
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
    this.messages.push({ type: 'bank', content: text, voiceNote: true, voiceContent: blobURL });
  }
  else{
    this.messages.push({ type: 'bank', content: text, voiceNote: false, voiceContent: '' });

  }
  }

  onCustomerChange(event: any) {
    this.aiEngineIntegrationService.getCustomerData(this.selectedCustomer.CICNumber).subscribe(
      (response: ProfileData) => {
        console.log(response);
        this.userInfo = response;
        console.log("my infooo", this.userInfo)

        this.aiEngineIntegrationService.getAccountTransactions(this.selectedCustomer.CICNumber,
          this.staticMapData.get(this.selectedCustomer.CICNumber)?.accountNumber).subscribe(
            (accTranx: AccountTransactionResponse) => {
              console.log("acouuuuuunt", accTranx)
              this.accountTransaction = accTranx
            }
           )

           this.aiEngineIntegrationService.getCardTransactions(this.selectedCustomer.CICNumber,
            this.staticMapData.get(this.selectedCustomer.CICNumber)?.cardNumber).subscribe(
             (cardTranx: TransactionResponse) => {
              console.log("carrrrrrrrd", cardTranx)
               this.cardTransaction = cardTranx
             }
            )
      },
      (error) => {
        console.log(error);
      });
}
  onStartChat(){
    this.startFlag=true;
    console.log('Selected Customer:', this.selectedCustomer);
    const dataRequest = new CustomerData(this.selectedCustomer.CICNumber,this.checkLanguage());
    this.aiEngineIntegrationService.getGreetingData(dataRequest).subscribe(
      (response: DataResponseModel) => {
        console.log(response);
        this.sessionId = response.SessionId;
        this.base64toBlob(response.Base46, response.Text , 'application/x-www-form-urlencoded' )
      },
      (error) => {
        console.log(error);
      });
  }
   
  checkLanguage(){
    var lang 
    if (this.selectedLanguage.code == 'en-US') {
      lang = 'EN'
  } else{
    lang = "AR"
  }

  return lang

}
startStream(): void {
  this.webSocketService.connect();
  this.isStreaming = true;
}

// Method to stop the audio stream
stopStream(): void {
  this.webSocketService.disconnect();
  this.isStreaming = false;
}



sendMessage(){
  this.webSocketService.sendMessage();

}

playAudioChunk(audioChunk: Uint8Array): void {
  this.webSocketService.audioContext.decodeAudioData(audioChunk.buffer).then((audioBuffer) => {
    const source = this.webSocketService.audioContext.createBufferSource();
    source.buffer = audioBuffer;

    source.connect(this.webSocketService.audioContext.destination);

    source.start();
  }).catch((error) => {
    console.error('Error decoding audio data:', error);
  });
}


}