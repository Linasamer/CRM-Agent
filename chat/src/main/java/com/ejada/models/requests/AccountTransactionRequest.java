package com.ejada.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable.Deserializable
public class AccountTransactionRequest {

    @JsonProperty("CICNumber")
    @NotNull
    private String cicNumber;

    @JsonProperty("AccountNumber")
    @NotNull
    private String accountNumber;

}
