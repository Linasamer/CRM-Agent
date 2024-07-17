package com.code.secretary.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.secretary.models.interactive.AgentResponseWithAccount;
import com.code.secretary.models.requests.AgentRequest;
import com.code.secretary.service.customer.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v2/chat")
@CrossOrigin(origins = "*")
public class ChatControllerV2 {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/ai_agent")
	@Operation(description = "Wrapped Ai Api to convert from speach to text and vise versa")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Ai agent convert successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error") })
	public AgentResponseWithAccount aiAgentConversionwithAccount(@RequestBody AgentRequest request) {
		return customerService.interactiveIvr(request);
	}
}