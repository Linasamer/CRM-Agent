package com.code.secretary.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {

	@JsonProperty("Text")
	private String text;

	@JsonProperty("Base46")
	private String base46;

	@JsonProperty("SessionId")
	private String sessionId;

}
