package com.mutants.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MutantGenDto{
    private String[] dnaVertical;
    private String[] dnaHorizontal;
    private String[] dnaDiagonal;
}
