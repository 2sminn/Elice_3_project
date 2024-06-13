package com.eliceteam8.edupay.point.controller;

import com.eliceteam8.edupay.point.dto.PointLogDTO;
import com.eliceteam8.edupay.point.entity.PointRechargeLog;
import com.eliceteam8.edupay.point.entity.PointUseLog;
import com.eliceteam8.edupay.point.service.PointService;
import com.eliceteam8.edupay.user.dto.UserDTO;
import com.eliceteam8.edupay.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.siot.IamportRestClient.exception.IamportResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {
    private final PointService pointService;
    private final UserService userService;

    @GetMapping("/balance")
    public Long getUserPoint() {
        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return pointService.getPoint(user.getUserId());
    }

    @GetMapping("/recharge/log")
    public Page<PointRechargeLog> getUserPointRechargeLog() {
        Pageable pageable = PageRequest.of(0, 10);
        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return pointService.getPointRechargeLog(user.getUserId(), pageable);
    }

    @PostMapping("/recharge")
    public ResponseEntity<Void> createPoint(@RequestBody PointLogDTO request) throws JsonProcessingException {
        pointService.savePoint(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/use/log")
    public Page<PointUseLog> getUserPointUseLog() {
        Pageable pageable = PageRequest.of(0, 10);
        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return pointService.getPointUseLog(user.getUserId(), pageable);
    }

    @PostMapping("/use")
    public ResponseEntity<Void> usePoint(@RequestBody PointLogDTO request) throws JsonProcessingException {
        pointService.usePoint(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/refund")
    public ResponseEntity<Void> refundPoint(@RequestBody PointLogDTO request) throws IOException, IamportResponseException {
        pointService.refundPoint(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
