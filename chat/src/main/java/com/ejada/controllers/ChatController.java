package com.ejada.controllers;


import com.ejada.models.requests.AccountTransactionRequest;
import com.ejada.models.requests.CardTransactionRequest;
import com.ejada.models.requests.CustomerDataRequest;
import com.ejada.models.requests.DataRequest;
import com.ejada.models.requests.GreetingDataRequest;
import com.ejada.models.responses.AccountTransactionResponse;
import com.ejada.models.responses.CardTransactionResponse;
import com.ejada.models.responses.CustomerDataResponse;
import com.ejada.models.responses.DataResponse;
import com.ejada.models.responses.GreetingDataResponse;
import com.ejada.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

import java.io.IOException;

import static io.micronaut.http.MediaType.APPLICATION_JSON;



@RequiredArgsConstructor
@Controller("/v1/chat")
public class ChatController {

    @Inject
    private CustomerService customerService;

    @Post(value = "/getCardInfo", consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    @Operation(description = "Retrieve card data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "502", description = "Generic bad gateway error")
    })
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<CardTransactionResponse> getCardInfo(
            @NotNull @Body CardTransactionRequest cardTransactionRequest
            ) {

        return HttpResponse.ok(customerService.getCardInfo(cardTransactionRequest));
    }

    @Post(value = "/getGreetingData", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    @Operation(description = "Retrieve Greeting Data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Greeting data retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "502", description = "Generic bad gateway error")
    })
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<GreetingDataResponse> getGreetingData(@Body GreetingDataRequest body) {
        GreetingDataResponse greetingData = customerService.getGreetingMessage(body);
        return HttpResponse.ok(greetingData);
    }

    @Post(value = "/getCustomerData", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    @Operation(description = "Retrieve Customer Data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer data retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "502", description = "Generic bad gateway error")
    })
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<CustomerDataResponse> getCustomerData(@Body CustomerDataRequest body) {
        CustomerDataResponse customerData = customerService.getCustomerData(body);
        return HttpResponse.ok(customerData);
    }

    @Post(value = "/getAccountInfo", consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    @Operation(description = "Retrieve card data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "502", description = "Generic bad gateway error")
    })
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<AccountTransactionResponse> getCardInfo(
            @NotNull @Body AccountTransactionRequest accountTransactionRequest
    ) {

        return HttpResponse.ok(customerService.getAccountInfo(accountTransactionRequest));
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Post(value = "/textToText", consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    @Operation(description = "Retrieve text data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "502", description = "Generic bad gateway error")
    })
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<DataResponse> textToText(
            @NotNull @Body DataRequest text
    ) {
        return HttpResponse.ok(customerService.getDataResponse(text));
    }


    @Post(value = "/textToVoice", consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    @Operation(description = "Retrieve text data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "502", description = "Generic bad gateway error")
    })
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<DataResponse> textToVoice(
            @NotNull @Body DataRequest dataRequest
    ) throws IOException {
        return HttpResponse.ok(customerService.getBase46(dataRequest));
    }

    @Post(value = "/voiceToVoice", consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    @Operation(description = "Retrieve text data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "502", description = "Generic bad gateway error")
    })
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<DataResponse> voiceToVoice(
            @NotNull @Body DataRequest dataRequest
    ) throws IOException {
        return HttpResponse.ok(customerService.getBase46(dataRequest));
    }

    @Post(value = "/voiceToText", consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    @Operation(description = "Retrieve text data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "502", description = "Generic bad gateway error")
    })
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<String> voiceToText(
            @NotNull @Body DataRequest text
    ) {
        return HttpResponse.ok("Hello Customer");
    }
}