package com.code.secretary.controller.customer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.secretary.models.requests.AccountTransactionRequest;
import com.code.secretary.models.requests.CardTransactionRequest;
import com.code.secretary.models.requests.DataRequest;
import com.code.secretary.models.requests.GreetingDataRequest;
import com.code.secretary.models.responses.AccountTransactionResponse;
import com.code.secretary.models.responses.CardTransactionResponse;
import com.code.secretary.models.responses.CustomerDataResponse;
import com.code.secretary.models.responses.DataResponse;
import com.code.secretary.service.customer.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v1/chat")
@CrossOrigin(origins = "*")
public class ChatController {

	@Autowired
	private CustomerService customerService;

	@Operation(description = "Retrieve card data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error")
	})

	@PostMapping("/getCardInfo")
	public CardTransactionResponse saveUploadedAttachment(@RequestBody CardTransactionRequest cardTransactionRequest) {
		return customerService.getCardInfo(cardTransactionRequest);
	}

	@PostMapping(value = "/getGreetingData")
	@Operation(description = "Retrieve Greeting Data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Greeting data retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error")
	})
	public DataResponse getGreetingData(@RequestBody GreetingDataRequest body) throws JsonProcessingException {
		DataResponse dataResponse = customerService.getGreetingMessage(body);
		return dataResponse;
	}

	@GetMapping(value = "/getCustomerData")
	@Operation(description = "Retrieve Customer Data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer data retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error")
	})
	public CustomerDataResponse getCustomerData(@RequestParam String customerCIC) {
		CustomerDataResponse customerData = customerService.getCustomerData(customerCIC);
		return customerData;
	}

	@PostMapping(value = "/getAccountInfo")
	@Operation(description = "Retrieve card data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error")
	})
	public AccountTransactionResponse getCardInfo(@RequestBody AccountTransactionRequest accountTransactionRequest) {
		return customerService.getAccountInfo(accountTransactionRequest);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@PostMapping(value = "/textToText")
	@Operation(description = "Retrieve text data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error")
	})
	public DataResponse textToText(@RequestBody DataRequest text) {
		return customerService.getDataResponse(text);
	}

	@PostMapping(value = "/voiceToVoice")
	@Operation(description = "Retrieve text data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error")
	})
	public DataResponse voiceToVoice(
			@RequestBody DataRequest dataRequest) throws IOException {
		return customerService.getBase46(dataRequest);
	}

	///////////////////////////////////////////////////////////////////
	@PostMapping(value = "/getCustomerDataString")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer data retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error")
	})
	public String getCustomerDataString(@RequestBody String customerCIC) {
		CustomerDataResponse customerData = customerService.getCustomerData(customerCIC);
		return customerData.toString();
	}

	@PostMapping(value = "/textToVoice")
	@Operation(description = "Retrieve text data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error")
	})
	public DataResponse textToVoice(@RequestBody DataRequest dataRequest) throws IOException {
		return customerService.getBase46(dataRequest);
	}

	@PostMapping(value = "/voiceToText")
	@Operation(description = "Retrieve text data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error")
	})
	public String voiceToText(@RequestBody DataRequest text) {
		return "Hello Customer";
	}

}