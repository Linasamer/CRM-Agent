package com.ejada.client;

import com.ejada.models.requests.AgentRequest;
import com.ejada.models.responses.AgentResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import static io.micronaut.http.MediaType.APPLICATION_JSON;
@Client(value = "${ai-agent-client.api.url}")
public interface AiAgentClient {

    @Post(value = "/v1/ai_agent/agent_response", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    HttpResponse<AgentResponse> getAgentResponse(@Header(value = "x-correlation-id") String correlationId,
                                                 @Header(value = HttpHeaders.AUTHORIZATION) String authorization,
                                                 @Header(value = "Channel_Id") String channelId,
                                                 @Header(value = "Accept-Language") String language,
                                                 @Body AgentRequest agentRequest);


}