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
public class Notification {

    @JsonProperty("NotificationID")
    private int notificationId;

    @JsonProperty("NotificationDate")
    private String notificationDate;

    @JsonProperty("NotificationType")
    private String notificationType;

    @JsonProperty("NotificationDescription")
    private String notificationDescription;

    @JsonProperty("NotificationStatus")
    private String notificationStatus;
}
