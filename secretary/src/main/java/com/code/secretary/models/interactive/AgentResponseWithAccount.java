package com.code.secretary.models.interactive;

import com.code.secretary.models.responses.AgentResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentResponseWithAccount {
	@JsonProperty("type")
	private String type;

	@JsonProperty("interactive")
	private Interactive interactive;

	@JsonProperty("AgentResponse")
	private AgentResponse agentResponse;
}
