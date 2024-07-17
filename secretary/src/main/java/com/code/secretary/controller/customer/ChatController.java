package com.code.secretary.controller.customer;

import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.secretary.exceptions.ErrorResponse;
import com.code.secretary.models.requests.AccountTransactionRequest;
import com.code.secretary.models.requests.AgentRequest;
import com.code.secretary.models.requests.DataRequest;
import com.code.secretary.models.requests.GreetingDataRequest;
import com.code.secretary.models.responses.AccountTransactionResponse;
import com.code.secretary.models.responses.AgentResponse;
import com.code.secretary.models.responses.CardTransactionResponse;
import com.code.secretary.models.responses.CustomerDataResponse;
import com.code.secretary.models.responses.DataResponse;
import com.code.secretary.service.customer.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v1/chat")
@CrossOrigin(origins = "*")
public class ChatController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/getGreetingData")
	@Operation(description = "Retrieve Greeting Data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Greeting data retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error") })
	public DataResponse getGreetingData(@RequestBody GreetingDataRequest body) throws JsonProcessingException {
		DataResponse dataResponse = customerService.getGreetingMessage(body);
		return dataResponse;
	}

	@GetMapping(value = "/getCustomerData")
	@Operation(description = "Retrieve Customer Data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer data retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error") })
	public CustomerDataResponse getCustomerData(@RequestParam String customerCIC) {
		CustomerDataResponse customerData = CustomerService.getCustomerDataResponse(customerCIC);
		return customerData;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@PostMapping(value = "/textToText")
	@Operation(description = "Retrieve text data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error") })
	public DataResponse textToText(@RequestBody DataRequest text) {
		return customerService.getDataResponse(text);
	}

	@PostMapping(value = "/mocktext")
	@Operation(description = "Retrieve text data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error") })
	public DataResponse textMock(@RequestBody DataRequest text) {
		return DataResponse.builder().text("Ana Zeha2t").sessionId(UUID.randomUUID().toString()).build();
	}

	@PostMapping(value = "/voiceToVoice")
	@Operation(description = "Retrieve text data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error") })
	public DataResponse voiceToVoice(@RequestBody DataRequest dataRequest) throws IOException {
		return customerService.getBase46(dataRequest);
	}

	@PostMapping(value = "/textToVoice")
	@Operation(description = "Retrieve text data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error") })
	public DataResponse textToVoice(@RequestBody DataRequest dataRequest) throws IOException {
		return customerService.getBase46(dataRequest);
	}

	////////////////////////////////////////////////////////

	@GetMapping(value = "/card-transactions")
	@Operation(description = "Retrieve card transactions")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer Credit Card data retrieved successfully", content = @Content(schema = @Schema(implementation = CardTransactionResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	public CardTransactionResponse getCardTransaction(@NotNull @RequestParam(name = "CustomerCIC") String customerCIC,
			@NotNull @RequestParam(name = "CardSequence") String cardSequence, @RequestHeader("x-correlation-id") String correlationId,
			@RequestHeader("Accept-Language") String lang) {

		return CustomerService.getCustomerCreditCardTransactions(customerCIC, lang, cardSequence);
	}

	@GetMapping(value = "/account-transactions")
	@Operation(description = "Retrieve account transactions")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer Account Transaction data retrieved successfully", content = @Content(schema = @Schema(implementation = AccountTransactionResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	public AccountTransactionResponse getAccountTransaction(@NotNull @RequestParam(name = "CustomerCIC") String customerCIC,
			@NotNull @RequestParam(name = "AccountNumber") String accountNumber, @RequestHeader("x-correlation-id") String correlationId,
			@RequestHeader("Accept-Language") String lang) {

		return customerService.getCustomerAccountTransactions(customerCIC, lang, accountNumber);
	}

	//////////////

	@PostMapping(value = "/getAccountInfo")
	@Operation(description = "Retrieve card data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error") })
	public AccountTransactionResponse getCardInfo(@RequestBody AccountTransactionRequest accountTransactionRequest) {
		return customerService.getAccountInfo(accountTransactionRequest);
	}

	@PostMapping(value = "/ai_agent")
	@Operation(description = "Wrapped Ai Api to convert from speach to text and vise versa")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ai agent convert successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error") })
	public AgentResponse aiAgentConversion(@RequestBody AgentRequest request) {
		return customerService.ivrCrmAiAgent(request);
	}

}