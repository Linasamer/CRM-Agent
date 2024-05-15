package com.code.secretary.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.code.secretary.exceptions.custom.BusinessException;
import com.code.secretary.models.requests.AIRequest;
import com.code.secretary.models.requests.AgentRequest;
import com.code.secretary.models.responses.AIResponse;
import com.code.secretary.models.responses.AgentResponse;

@Component
public class RestClientService {

	public static <T extends Object> AIResponse getPostObjectForIntegration(AIRequest request, String lang) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Accept-Language", lang);
			HttpEntity<AIRequest> requestEntity = new HttpEntity<>(request, headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<AIResponse> responseEntity = restTemplate.postForEntity("http://41.33.183.2:4012/ai_agent/agent_response", requestEntity,
					AIResponse.class);

			if (responseEntity.getStatusCodeValue() != HttpStatus.OK.value())
				throw new BusinessException("error_webserviceFailed");

			return responseEntity.getBody();
		} catch (Exception e) {
			throw e;
		}
	}

	public static <T extends Object> AgentResponse getPostObject(AgentRequest request, String lang) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Accept-Language", lang);
			HttpEntity<AgentRequest> requestEntity = new HttpEntity<>(request, headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<AgentResponse> responseEntity = restTemplate.postForEntity("http://18.201.252.113:4010/v1/ai_agent/agent_response",
					requestEntity, AgentResponse.class);

			if (responseEntity.getStatusCodeValue() != HttpStatus.OK.value())
				throw new BusinessException("error_webserviceFailed");

			return responseEntity.getBody();
		} catch (Exception e) {
			throw e;
		}
	}

}
