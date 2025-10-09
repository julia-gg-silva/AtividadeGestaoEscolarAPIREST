package com.weg.GestaoEscolar.service;

import com.weg.GestaoEscolar.mapper.TurmaMapper;
import com.weg.GestaoEscolar.repository.TurmaDAO;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    private final TurmaDAO repository;
    private final TurmaMapper mapper;


    public TurmaService(TurmaDAO repository, TurmaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
