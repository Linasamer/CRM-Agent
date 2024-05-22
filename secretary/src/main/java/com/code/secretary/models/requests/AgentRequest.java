package com.code.secretary.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentRequest {

	@JsonProperty("Channel_Id")
	private String channelId;
	
	@JsonProperty("Authorization")
	private String authorization;
	
	@JsonProperty("x-correlation-id")
	private String correlationId;
	
	@JsonProperty("Accept-Language")
	private String acceptLanguage;

	@JsonProperty("CustomerCIC")
	@NonNull
	private String customerCic;

	@JsonProperty("SessionID")
	@NonNull
	private String sessionId;

	@JsonProperty("UserText")
	private String userText;

	@JsonProperty("UserAudio")
	private String userAudio;

}
