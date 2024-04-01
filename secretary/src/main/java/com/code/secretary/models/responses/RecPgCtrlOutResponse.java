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
public class RecPgCtrlOutResponse {

    @JsonProperty("SentRecs")
    private int sentRecs;

    @JsonProperty("MatchedRecs")
    private int matchedRecs;

    @JsonProperty("ComplFlg")
    private String complFlg;

}
