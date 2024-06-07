package com.eliceteam8.edupay.point.controller;

import com.eliceteam8.edupay.point.dto.PointDTO;
import com.eliceteam8.edupay.point.service.PointService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {
    private final PointService pointService;

    @PostMapping("")
    public ResponseEntity<Void> createPoint(@RequestBody PointDTO request) throws JsonProcessingException {
        pointService.savePoint(request);

        return new ResponseEntity(HttpStatus.OK);
    }
}
