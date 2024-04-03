package com.code.secretary.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.code.secretary.enums.ConfigurationEnum;
import com.code.secretary.enums.ExternalServerEnum;
import com.code.secretary.exceptions.ExceptionsHandlerService;
import com.code.secretary.exceptions.custom.BusinessException;
import com.code.secretary.models.requests.AgentRequest;
import com.code.secretary.models.responses.AgentResponse;

@Component
public class RestClientService {


//	public static <T extends Object> T getObject(ExternalServerEnum externalServerEnum, String url, Class<T> cls, Object... params) {
//		try {
//			RestTemplate restTemplate = new RestTemplate();
//			ResponseEntity<T> response = restTemplate.getForEntity(getServerPath(externalServerEnum) + url, cls, params);
//
//			if (response.getStatusCodeValue() != HttpStatus.OK.value())
//				throw new BusinessException("error_webserviceFailed");
//
//			return response.getBody();
//		} catch (Exception e) {
//			throw ExceptionsHandlerService.handleException(e, "error_webserviceFailed");
//		}
//	}

	public static <T extends Object> AgentResponse getPostObject(AgentRequest request, String lang) {
		try {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.set("Accept-Language", lang);
	        HttpEntity<AgentRequest> requestEntity = new HttpEntity<>(request, headers);
			RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<AgentResponse> responseEntity = restTemplate.postForEntity("http://41.33.183.2:4010/v1/ai_agent/agent_response", requestEntity, AgentResponse.class);


			if (responseEntity.getStatusCodeValue() != HttpStatus.OK.value())
				throw new BusinessException("error_webserviceFailed");

			return responseEntity.getBody();
		} catch (Exception e) {
			throw ExceptionsHandlerService.handleException(e, "error_webserviceFailed");
		}
	}
	
//	public static <T extends Object> List<T> getList(ExternalServerEnum externalServerEnum, String url, Class<T[]> cls, Object... params) {
//		try {
//			RestTemplate restTemplate = new RestTemplate();
//			ResponseEntity<T[]> response = restTemplate.getForEntity(getServerPath(externalServerEnum) + url, cls, params);
//			if (response.getStatusCodeValue() != HttpStatus.OK.value())
//				throw new BusinessException("error_webserviceFailed");
//			return Arrays.asList(response.getBody());
//		} catch (Exception e) {
//			throw ExceptionsHandlerService.handleException(e, "error_webserviceFailed");
//		}
//	}

//	public static byte[] getImage(ExternalServerEnum externalServerEnum, String url, Object... params) {
//		try {
//			RestTemplate restTemplate = new RestTemplate();
//			ResponseEntity<byte[]> response = restTemplate.getForEntity(getServerPath(externalServerEnum) + url, byte[].class, params);
//			if (response.getStatusCodeValue() != HttpStatus.OK.value())
//				throw new BusinessException("error_webserviceFailed");
//			return response.getBody();
//		} catch (Exception e) {
//			if (e instanceof HttpClientErrorException)
//				throw new BusinessException("error_webserviceFailed");
//			throw ExceptionsHandlerService.handleException(e, "error_webserviceFailed");
//		}
//	}

//	private static String getServerPath(ExternalServerEnum externalServerEnum) {
//		String serverPath = null;
//		if (externalServerEnum.equals(ExternalServerEnum.SETUP))
//			serverPath = configurationService.getStringByCode(ConfigurationEnum.SETUP_PATH);
//		else if (externalServerEnum.equals(ExternalServerEnum.SECURITY))
//			serverPath = configurationService.getStringByCode(ConfigurationEnum.SECURITY_PATH);
//		else if (externalServerEnum.equals(ExternalServerEnum.UNIFIED_UPLOADER))
//			serverPath = "";
//		if (serverPath == null)
//			throw new BusinessException("error_externalServerNotDefined");
//		return serverPath;
//	}
}
