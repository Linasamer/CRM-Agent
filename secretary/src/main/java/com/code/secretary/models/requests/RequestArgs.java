package com.code.secretary.models.requests;
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
public class RequestArgs {


    private String correlationId;


    private String authorization;

    private String channelId;

    private String language;


}
