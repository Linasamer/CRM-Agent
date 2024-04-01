package com.code.secretary.models.responses;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentResponse {

    @JsonProperty("AgentText")
    private String agentText;

    @JsonProperty("AgentAudio")
    private String agentAudio;

}
