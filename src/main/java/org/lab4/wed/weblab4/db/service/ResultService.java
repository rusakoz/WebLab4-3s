package org.lab4.wed.weblab4.db.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.lab4.wed.weblab4.db.dto.ResultsCreateEditDto;
import org.lab4.wed.weblab4.db.dto.ResultsReadDto;
import org.lab4.wed.weblab4.db.entity.Results;
import org.lab4.wed.weblab4.db.repository.ResultRepository;
import org.lab4.wed.weblab4.mapper.ResultsCreateEditMapper;
import org.lab4.wed.weblab4.mapper.ResultsReadMapper;
import org.lab4.wed.weblab4.model.CheckHit;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultService {
    
    private final ResultRepository repository;
    private final ResultsReadMapper resultsReadMapper;
    private final ResultsCreateEditMapper resultsCreateEditMapper;
    
    public List<Results> findAll(){
        return repository.findAll();
    }

    public List<ResultsReadDto> findByUserId(long id){
        return repository.findByUserId(id).stream()
                    .map(resultsReadMapper::map)
                    .toList();
    }

    public ResultsReadDto create(ResultsCreateEditDto resultsDto){
        return Optional.of(resultsDto)
            .map(resultsCreateEditMapper::map)
            .map(repository::save)
            .map(resultsReadMapper::map)
            .orElseThrow();
    }


    public ResultsReadDto checkHitAndCreateNew(ResultsCreateEditDto resultsDto){
        final long startCheck = System.nanoTime();
        final boolean hit = CheckHit.checkHit(resultsDto.getX(), resultsDto.getY(), resultsDto.getR());
        final long stopCheck = System.nanoTime();

        resultsDto.setDate(LocalDateTime.now());
        resultsDto.setExecTime(stopCheck - startCheck);
        resultsDto.setHit(hit);
        
        return create(resultsDto);
    }
}
