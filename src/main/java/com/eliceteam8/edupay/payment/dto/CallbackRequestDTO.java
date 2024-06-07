package com.eliceteam8.edupay.payment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CallbackRequestDTO {

    private String impUid;
    private Long billId;

    public static CallbackRequestDTO fromString(String request) {
        JsonObject requestJson = JsonParser.parseString(request).getAsJsonObject();
        String impUid = requestJson.getAsJsonPrimitive("imp_uid") == null ? null : requestJson.getAsJsonPrimitive("imp_uid").getAsString();
        Long billId = requestJson.getAsJsonPrimitive("bill_id") == null ? null : requestJson.getAsJsonPrimitive("bill_id").getAsLong();

        return CallbackRequestDTO.builder()
                .impUid(impUid)
                .billId(billId)
                .build();
    }
}
