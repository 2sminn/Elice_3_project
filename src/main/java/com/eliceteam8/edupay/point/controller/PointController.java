package com.eliceteam8.edupay.point.controller;

import com.eliceteam8.edupay.point.dto.PointHistoryDTO;
import com.eliceteam8.edupay.point.entity.PointHistory;
import com.eliceteam8.edupay.point.service.PointService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {
    private final PointService pointService;

    @GetMapping("{userId}")
    public Long getUserPoint(@PathVariable Long userId) {
        return pointService.getPoint(userId);
    }

    @GetMapping("/history/{userId}")
    public List<PointHistory> getUserPointHistory(@PathVariable Long userId) {
        return pointService.getPointHistory(userId);
    }

    @PostMapping("")
    public ResponseEntity<Void> createPoint(@RequestBody PointHistoryDTO request) throws JsonProcessingException {
        pointService.savePoint(request);

        return new ResponseEntity(HttpStatus.OK);
    }
}
