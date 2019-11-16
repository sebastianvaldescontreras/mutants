package com.mutants.infrastructure.repository.impl;

import com.mutants.domain.entities.Subject;
import com.mutants.infrastructure.repository.ISubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;

@Repository
@Transactional
@Slf4j
public class SubjectRepository implements ISubjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int postSubject(Subject subject) {
        String sql = "insert into subject (dna, mutant, creationdate) values(?,?,?)";
        int update = 0;
        try{
            update = jdbcTemplate.update(sql, subject.getDna(), subject.isMutant(), Timestamp.valueOf(subject.getCreationdate()));
        }catch(Exception e){
            log.error("Not insert subject in Database. ");
        }
        return update;
    }

    @Override
    public long countMutant() {
        String sql = "select count(*) from subject where mutant = true";
        long select = 0;
        try{
            select = jdbcTemplate.queryForObject(sql, Integer.class);
        }catch(Exception e){
            log.error("Not select subject in Database. ");
        }
        return select;
    }

    @Override
    public long countHuman() {
        String sql = "select count(*) from subject where mutant = false";
        long select = 0;
        try{
            select = jdbcTemplate.queryForObject(sql, Integer.class);
        }catch(Exception e){
            log.error("Not select subject in Database. ");
        }
        return select;
    }
}
