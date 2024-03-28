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
public class TrxnLst {
    @JsonProperty("CICNum")
    private String cicNumber;

    @JsonProperty("AvailableBal")
    private double availableBalance;

    @JsonProperty("UnclearedBal")
    private double unclearedBalance;

    @JsonProperty("RecPgCtrlOut")
    private RecPgCtrlOutResponse recordPageControlOut;

    @JsonProperty("TrxnDtls")
    private List<TransactionDetail> transactionDetails;
}
