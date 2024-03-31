package com.ejada.client;


import com.ejada.models.requests.AgentRequest;
import com.ejada.models.requests.RequestArgs;
import com.ejada.models.responses.AgentResponse;
import io.micronaut.http.server.exceptions.InternalServerException;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import io.micronaut.http.HttpResponse;


@Slf4j
@Singleton
public class AiAgentClientFacade {

    @Inject
    private AiAgentClient aiAgentClient;


    public AgentResponse getAgentResponse(RequestArgs requestArgs, AgentRequest agentRequest) {
        try {
            HttpResponse<AgentResponse> httpResponse = aiAgentClient.getAgentResponse(
                    requestArgs.getCorrelationId(), requestArgs.getAuthorization(), requestArgs.getChannelId() ,
                    requestArgs.getLanguage(), agentRequest);
            log.info("[{}]:getAgentResponse()::Ai Agent response retrived successfully");
            return httpResponse.getBody().get();
        } catch (Exception exception) {
            throw new InternalServerException("Ai Agent Service throw error");
        }

    }


}
