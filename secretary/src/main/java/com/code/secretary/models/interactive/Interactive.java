package com.code.secretary.models.interactive;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Interactive {
	@JsonProperty("type")
	private String type;

	@JsonProperty("text")
	private Text text;

	@JsonProperty("options")
	private List<Option> options;
}
