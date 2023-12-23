package org.lab4.wed.weblab4.db.service;

import java.util.List;

import org.lab4.wed.weblab4.db.entity.Results;
import org.lab4.wed.weblab4.db.repository.ResultRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultService {
    
    private final ResultRepository repository;
    
    public List<Results> findAll(){
        return repository.findAll();
    }
}
