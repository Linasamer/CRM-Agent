package com.code.secretary.controller.customer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.secretary.models.requests.AIRequest;
import com.code.secretary.models.responses.AIResponse;
import com.code.secretary.service.customer.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("enrichment-data/ai_agent")
@CrossOrigin(origins = "*")
public class AiController {
	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/agent_response")
	@Operation(description = "Agent Response")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "terms and conditions retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "503", description = "Service Unavailable"),
			@ApiResponse(responseCode = "502", description = "Generic bad gateway error") })
	public AIResponse getCardInfo(@RequestBody AIRequest agentRequest, @RequestHeader("Accept-Language") String languageId) throws IOException {
		return customerService.callAIDirect(agentRequest, languageId);
	}
}