package com.mutants.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Subject {
    private long id;
    private String dna;
    private boolean mutant;
    private LocalDateTime creationdate;
}
