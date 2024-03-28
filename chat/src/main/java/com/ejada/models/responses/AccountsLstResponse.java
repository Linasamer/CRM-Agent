package com.ejada.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable.Serializable
public class AccountsLstResponse {

    @JsonProperty("RecPgCtrlOut")
    private RecPgCtrlOutResponse recPgCtrlOut;

    @JsonProperty("TotalBal")
    private TotalBal totalBal;

    @JsonProperty("Accounts")
    private List<AccountResponse> Accounts;

}
