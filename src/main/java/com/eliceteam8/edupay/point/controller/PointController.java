package com.eliceteam8.edupay.point.controller;

import com.eliceteam8.edupay.point.dto.PointLogDTO;
import com.eliceteam8.edupay.point.entity.PointRechargeLog;
import com.eliceteam8.edupay.point.entity.PointUseLog;
import com.eliceteam8.edupay.point.service.PointService;
import com.eliceteam8.edupay.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.siot.IamportRestClient.exception.IamportResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {
    private final PointService pointService;
    private final UserService userService;

    @GetMapping("/balance")
    public Long getUserPoint(@RequestHeader("Authorization") String token) {
        return pointService.getPoint(userService.getUserIdByToken(token));
    }

    @GetMapping("/recharge/log")
    public List<PointRechargeLog> getUserPointRechargeLog(@RequestHeader("Authorization") String token) {
        return pointService.getPointRechargeLog(userService.getUserIdByToken(token));
    }

    @PostMapping("/recharge")
    public ResponseEntity<Void> createPoint(@RequestBody PointLogDTO request) throws JsonProcessingException {
        pointService.savePoint(request);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/use/log")
    public List<PointUseLog> getUserPointUseLog(@RequestHeader("Authorization") String token) {
        return pointService.getPointUseLog(userService.getUserIdByToken(token));
    }

    @PostMapping("/use")
    public ResponseEntity<Void> usePoint(@RequestBody PointLogDTO request) throws JsonProcessingException {
        pointService.usePoint(request);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/refund")
    public ResponseEntity<Void> refundPoint(@RequestBody PointLogDTO request) throws IOException, IamportResponseException {
        pointService.refundPoint(request);

        return new ResponseEntity(HttpStatus.OK);
    }
}
