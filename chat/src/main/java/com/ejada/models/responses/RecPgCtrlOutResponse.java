package com.ejada.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable.Serializable
public class RecPgCtrlOutResponse {

    @JsonProperty("SentRecs")
    private int sentRecs;

    @JsonProperty("MatchedRecs")
    private int matchedRecs;

    @JsonProperty("ComplFlg")
    private String complFlg;

}
