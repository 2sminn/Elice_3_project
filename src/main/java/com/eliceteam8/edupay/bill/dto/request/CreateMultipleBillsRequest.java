package com.eliceteam8.edupay.bill.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CreateMultipleBillsRequest {
    private List<Long> studentIds;
}
