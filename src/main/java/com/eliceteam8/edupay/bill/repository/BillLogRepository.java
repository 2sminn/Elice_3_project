package com.eliceteam8.edupay.bill.repository;

import com.eliceteam8.edupay.bill.domain.BillLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BillLogRepository extends JpaRepository<BillLog, Long> {
    Optional<BillLog> findFirstByOrderByCreatedAtDesc();
}
