package com.eliceteam8.edupay.point.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ToString
public class PointLogDTO {

    private Long userId;
    private Long point;
    private String impUid;

    public static PointLogDTO fromString(String request) {
        JsonObject requestJson = JsonParser.parseString(request).getAsJsonObject();
        Long userId = requestJson.getAsJsonPrimitive("user_id") == null ? null : requestJson.getAsJsonPrimitive("user_id").getAsLong();
        Long point = requestJson.getAsJsonPrimitive("point") == null ? null : requestJson.getAsJsonPrimitive("point").getAsLong();
        String impUid = requestJson.getAsJsonPrimitive("imp_uid") == null ? null : requestJson.getAsJsonPrimitive("imp_uid").getAsString();

        return PointLogDTO.builder()
                .userId(userId)
                .point(point)
                .impUid(impUid)
                .build();
    }
}
