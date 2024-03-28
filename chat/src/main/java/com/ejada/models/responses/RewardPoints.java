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
public class RewardPoints {

    @JsonProperty("AvailableBalance")
    private int availableBalance;

    @JsonProperty("ExpiredPoints")
    private int expiredPoints;

    @JsonProperty("RedeemedPoints")
    private int redeemedPoints;

    @JsonProperty("ExpiryPeriod")
    private int expiryPeriod;

    @JsonProperty("NextExpiryDate")
    private String nextExpiryDate;

    @JsonProperty("NextExpiringPoints")
    private int nextExpiringPoints;

    @JsonProperty("Email")
    private String email;
}
