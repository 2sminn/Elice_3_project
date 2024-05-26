package com.eliceteam8.edupay.bill.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class BillLog {
    @Id
    private Long id;

}
