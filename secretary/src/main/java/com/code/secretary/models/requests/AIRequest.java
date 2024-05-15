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

}
