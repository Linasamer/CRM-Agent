package com.code.secretary.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestArgs {

	private String correlationId;

	private String authorization;

	private String channelId;

	private String language;

}
