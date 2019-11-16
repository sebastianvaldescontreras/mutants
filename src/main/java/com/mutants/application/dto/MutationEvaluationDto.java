package com.mutants.application.dto;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class MutationEvaluationDto{
    private String[] dna;
    private boolean mutant;
}
