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
public class MarketingMessage {

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
