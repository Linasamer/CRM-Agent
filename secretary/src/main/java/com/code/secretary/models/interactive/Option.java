package com.code.secretary.models.interactive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Option {
	@JsonProperty("type")
	private String type;

	@JsonProperty("value")
	private String value;

	@JsonProperty("title")
	private String title;
}
