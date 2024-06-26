package com.ejada.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable.Deserializable
public class AgentRequest {

    @JsonProperty("CustomerCIC")
    @NonNull
    private String customerCic;

    @JsonProperty("SessionID")
    @NonNull
    private String sessionId;

    @JsonProperty("UserText")
    private String userText;

    @JsonProperty("UserAudio")
    private String userAudio;


}
