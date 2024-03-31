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
public class DataRequest {

    @JsonProperty("Text")
    @NotNull
    private String text;

    @JsonProperty("Language")
    @NotNull
    private String language;

    @JsonProperty("SessionId")
    private String sessionId;

}
