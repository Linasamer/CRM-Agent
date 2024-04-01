package com.code.secretary.models.responses;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountsLstResponse {

    @JsonProperty("RecPgCtrlOut")
    private RecPgCtrlOutResponse recPgCtrlOut;

    @JsonProperty("TotalBal")
    private TotalBal totalBal;

    @JsonProperty("Accounts")
    private List<AccountResponse> Accounts;

}
