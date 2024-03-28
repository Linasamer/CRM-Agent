import {  NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {  HttpClientModule } from '@angular/common/http';
import { AIEngineIntegrationService } from './AIEngineIntegration.service';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';



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
    BrowserAnimationsModule
    
    
    
  ],
  providers: [AIEngineIntegrationService   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
