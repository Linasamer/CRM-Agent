package com.code.secretary.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LedgerBalance {

    @JsonProperty("Amount")
    private double amount;

    @JsonProperty("Currency")
    private String currency;
}
