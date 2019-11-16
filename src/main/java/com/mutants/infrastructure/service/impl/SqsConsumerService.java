package com.mutants.infrastructure.service.impl;

import com.mutants.domain.entities.Subject;
import com.mutants.infrastructure.dto.MutationEvaluationDto;
import com.mutants.infrastructure.repository.ISubjectRepository;
import com.mutants.infrastructurecross.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
public class SqsConsumerService{
    @Autowired
    ISubjectRepository subjectRepository;

    //@SqsListener(value = "${cloud.aws.end-point.uri}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void getValueSQS(String notification) {
        MutationEvaluationDto mutationEvaluationDto = Json.setObject(MutationEvaluationDto.class, notification);
        Subject subject = new Subject();
        subject.setMutant(mutationEvaluationDto.getMutant());
        subject.setCreationdate(LocalDateTime.now());
        subject.setDna(String.join(",", mutationEvaluationDto.getDna()));
        subjectRepository.postSubject(subject);
        log.info(notification);
    }
}
