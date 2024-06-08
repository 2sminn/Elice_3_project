package com.eliceteam8.edupay.academy_management.controller;

import com.eliceteam8.edupay.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/academy")
public class AcademyController {


    @GetMapping
    public String getAcademy() {
        UserDTO user = (UserDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("user = " + user.getAcademyId());
        return "academy";
    }
}
