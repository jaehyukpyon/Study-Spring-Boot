package com.springboot.api.controller;

import com.springboot.api.data.dto.ValidRequestDto;
import com.springboot.api.data.dto.ValidatedRequestDto;
import com.springboot.api.data.group.ValidationGroup1;
import com.springboot.api.data.group.ValidationGroup2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/validation")
public class ValidationController {

    @PostMapping(value = "/valid")
    public ResponseEntity<String> checkValidationByValid(@Validated @RequestBody ValidRequestDto validRequestDto) {
        log.info(validRequestDto.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());
    }

    @PostMapping(value = "/validated/group1")
    public ResponseEntity<String> checkValidation1(@Validated(ValidationGroup1.class) @RequestBody ValidatedRequestDto validatedRequestDto) {
        log.info(validatedRequestDto.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

    @PostMapping(value = "/validated/group2")
    public ResponseEntity<String> checkValidation2(@Validated(ValidationGroup2.class) @RequestBody ValidatedRequestDto validatedRequestDto) {
        log.info(validatedRequestDto.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

    @PostMapping(value = "/validated/all-group")
    public ResponseEntity<String> checkValidation3(@Validated({ValidationGroup1.class, ValidationGroup2.class}) @RequestBody ValidatedRequestDto validatedRequestDto) {
        log.info(validatedRequestDto.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

}
