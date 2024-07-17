package com.code.secretary.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AIRequest {

	@JsonProperty("text")
	private String text;

	@JsonProperty("audio")
	private String audio;

	@JsonProperty("conv_id")
	private String conv_id;

	@JsonProperty("enable_tts")
	private String enable_tts;

	@JsonProperty("user_id")
	private String user_id;

	@JsonProperty("agent_gender")
	private String agent_gender;

	@JsonProperty("lang")
	private String lang;

	@JsonProperty("agent_speed")
	private String agent_speed;

}
