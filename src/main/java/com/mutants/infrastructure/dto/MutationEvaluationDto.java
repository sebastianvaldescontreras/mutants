package com.mutants.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MutationEvaluationDto {
    @JsonProperty("dna")
    private String[] dna;
    @JsonProperty("mutant")
    private Boolean mutant;
}
