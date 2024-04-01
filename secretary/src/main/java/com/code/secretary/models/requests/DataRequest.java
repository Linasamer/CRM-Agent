package com.code.secretary.models.requests;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataRequest {
	
	 @JsonProperty("CustomerCIC")
	 @NotNull
	 private String customerCiC;

    @JsonProperty("Text")
    @NotNull
    private String text;

    @JsonProperty("Language")
    @NotNull
    private String language;

    @JsonProperty("SessionId")
    private String sessionId;

}
