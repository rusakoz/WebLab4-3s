package org.lab4.wed.weblab4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lab4.wed.weblab4.db.dto.ResultsCreateEditDto;
import org.lab4.wed.weblab4.db.dto.ResultsReadDto;
import org.lab4.wed.weblab4.db.service.AuthJwtService;
import org.lab4.wed.weblab4.db.service.ResultService;
import org.lab4.wed.weblab4.jwt.JwtAuthentication;
import org.lab4.wed.weblab4.model.ValidatorCoords;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultsRestController {
    private final AuthJwtService authJwtService;
    private final ResultService resultService;

    @SecurityRequirement(name = "Bearer Authorization")
    @GetMapping("get")
    public ResponseEntity<?> getResults() {
        final JwtAuthentication authInfo = authJwtService.getAuthInfo();

        final List<ResultsReadDto> list = resultService.findByUserId(authInfo.getUserId());
        if (list.isEmpty()) {
            Map<String, String> message = new HashMap<>();
            message.put("error", "Записи не найдены");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bearer Authorization")
    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveResult(@RequestBody ResultsCreateEditDto resultsDto) {
        final JwtAuthentication authInfo = authJwtService.getAuthInfo();

        if (!ValidatorCoords.validate(resultsDto.getX(), resultsDto.getY(), resultsDto.getR())) {
            Map<String, String> message = new HashMap<>();
            message.put("error", "Невалидные данные");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        resultsDto.setUserId(authInfo.getUserId());

        final ResultsReadDto readDto = resultService.checkHitAndCreateNew(resultsDto);

        return new ResponseEntity<>(readDto, HttpStatus.CREATED);
    }
}
