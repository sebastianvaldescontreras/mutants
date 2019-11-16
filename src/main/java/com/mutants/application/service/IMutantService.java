package com.mutants.application.service;

import com.mutants.application.dto.StatsDto;

public interface IMutantService{
    boolean isMutant(String[] dna);
    StatsDto getStats();
}
