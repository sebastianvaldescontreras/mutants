package com.mutants.infrastructure.repository;

import com.mutants.domain.entities.Subject;

public interface ISubjectRepository {
    int postSubject(Subject subject);
    long countMutant();
    long countHuman();
}
