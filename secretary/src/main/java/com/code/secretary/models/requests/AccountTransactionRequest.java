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
public class AccountTransactionRequest {

    @JsonProperty("CICNumber")
    @NotNull
    private String cicNumber;

    @JsonProperty("AccountNumber")
    @NotNull
    private String accountNumber;

    @JsonProperty("SessionId")
    private String sessionId;


}
