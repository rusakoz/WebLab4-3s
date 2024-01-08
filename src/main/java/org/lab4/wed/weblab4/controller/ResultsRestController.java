package org.lab4.wed.weblab4.controller;

import java.util.List;

import org.lab4.wed.weblab4.db.dto.ResultsCreateEditDto;
import org.lab4.wed.weblab4.db.dto.ResultsReadDto;
import org.lab4.wed.weblab4.db.service.AuthJwtService;
import org.lab4.wed.weblab4.db.service.ResultService;
import org.lab4.wed.weblab4.jwt.JwtAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultsRestController {
    private final AuthJwtService authJwtService;
    private final ResultService resultService;

    @SecurityRequirement(name = "Bearer Authorization")
    @GetMapping("get")
    public ResponseEntity<?> getResults() {
        log.info("check results");
        final JwtAuthentication authInfo = authJwtService.getAuthInfo();

        final List<ResultsReadDto> list = resultService.findByUserId(authInfo.getUserId());
        if (list.isEmpty()) {
            return new ResponseEntity<>("{\"error\":\""+ "Записи не найдены" +"\"}", HttpStatus.OK);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bearer Authorization")
    @PostMapping("save")
    public ResponseEntity<?> saveResult(@RequestBody ResultsCreateEditDto resultsDto) {
        log.info("check results");
        final JwtAuthentication authInfo = authJwtService.getAuthInfo();

        resultsDto.setUserId(authInfo.getUserId());

        resultService.createNew(resultsDto);
        
        return new ResponseEntity<>("{\"status\":\""+ "Created" +"\"}", HttpStatus.CREATED);
    }
}
