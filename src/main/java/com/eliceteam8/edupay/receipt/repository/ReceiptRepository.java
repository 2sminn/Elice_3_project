package com.eliceteam8.edupay.receipt.repository;

import com.eliceteam8.edupay.receipt.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {
}
