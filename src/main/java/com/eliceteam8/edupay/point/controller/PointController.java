package com.eliceteam8.edupay.point.controller;

import com.eliceteam8.edupay.point.dto.PointLogDTO;
import com.eliceteam8.edupay.point.entity.PointRechargeLog;
import com.eliceteam8.edupay.point.entity.PointUseLog;
import com.eliceteam8.edupay.point.service.PointService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {
    private final PointService pointService;

    @GetMapping("/{userId}")
    public Long getUserPoint(@PathVariable(name = "userId", required = false) Long userId) {
        return pointService.getPoint(userId);
    }

    @GetMapping("/recharge/{userId}")
    public List<PointRechargeLog> getUserPointRechargeLog(@PathVariable(name = "userId", required = false) Long userId) {
        return pointService.getPointRechargeLog(userId);
    }

    @PostMapping("/recharge")
    public ResponseEntity<Void> createPoint(@RequestBody PointLogDTO request) throws JsonProcessingException {
        pointService.savePoint(request);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/use/{userId}")
    public List<PointUseLog> getUserPointUseLog(@PathVariable(name = "userId", required = false) Long userId) {
        return pointService.getPointUseLog(userId);
    }

    @PostMapping("/use")
    public ResponseEntity<Void> usePoint(@RequestBody PointLogDTO request) throws JsonProcessingException {
        pointService.usePoint(request);

        return new ResponseEntity(HttpStatus.OK);
    }
}
