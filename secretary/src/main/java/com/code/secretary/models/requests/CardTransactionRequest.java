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
public class CardTransactionRequest {

    @JsonProperty("CICNumber")
    @NotNull
    private String cicNumber;

    @JsonProperty("CardSequence")
    @NotNull
    private String cardSequence;

    @JsonProperty("SessionId")
    private String sessionId;


}
