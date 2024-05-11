import {  NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {  HttpClientModule } from '@angular/common/http';
import { AIEngineIntegrationService } from './AIEngineIntegration.service';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TabViewModule } from 'primeng/tabview';
import { ChipModule } from 'primeng/chip';
import { ButtonModule } from 'primeng/button';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { WebSocketService } from './websocket.service';
import { WebRTCService } from './webrtc.service';
import { AngMusicPlayerModule } from  'ang-music-player';
import { NgxAudioPlayerModule } from 'ngx-audio-player';




@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    DropdownModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    TabViewModule,
    ChipModule,
    ButtonModule,
    ScrollPanelModule,
    NgxAudioPlayerModule
  ],
  providers: [AIEngineIntegrationService, NgxAudioPlayerModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
