package com.mutants.application.service.impl;

import com.mutants.application.dto.MutantGenDto;
import com.mutants.application.dto.MutationEvaluationDto;
import com.mutants.application.dto.StatsDto;
import com.mutants.application.service.IMutantService;
import com.mutants.infrastructure.repository.ISubjectRepository;
import com.mutants.infrastructure.service.ISqsService;
import com.mutants.infrastructurecross.util.ArrayUtil;
import com.mutants.infrastructurecross.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class MutantService implements IMutantService{
    @Autowired
    private ISqsService sqsService;
    @Autowired
    private ISubjectRepository subjectRepository;
    @Autowired
    private MutantGenDto mutantGenDto;
    @Bean
    public MutantGenDto getArrayVertical(@Value("${mutantdna}") String[] mutantdna){
        MutantGenDto mutantGenDto = new MutantGenDto();
        mutantGenDto.setDnaHorizontal(mutantdna);
        mutantGenDto.setDnaVertical(ArrayUtil.orderVerticalArray(mutantdna));
        mutantGenDto.setDnaDiagonal(ArrayUtil.orderDiagonalArray(new String[] {}));
        return mutantGenDto;
    }

    @Override
    @Async("asyncExecutor")
    public boolean isMutant(String[] dna){
        int countFound = 0;
        for(String nitrogenousSubject : dna){
            CompletableFuture<Integer> cantPointMutantHorizontal = checkDnaMutantHorizontalAsync(nitrogenousSubject);
            CompletableFuture<Integer> cantPointMutantVertical = checkDnaMutantVerticalAsync(nitrogenousSubject);
            CompletableFuture<Integer> cantPointMutantDiagonal = checkDnaMutantDiagonalAsync(nitrogenousSubject);
            try {
                CompletableFuture.allOf(cantPointMutantHorizontal, cantPointMutantVertical, cantPointMutantDiagonal).join();
                countFound = countFound +
                cantPointMutantHorizontal.get() +
                cantPointMutantVertical.get() +
                cantPointMutantDiagonal.get();

                if (countFound >= 2) {
                    MutationEvaluationDto mutationEvaluationDto = new MutationEvaluationDto();
                    mutationEvaluationDto.setDna(dna);
                    mutationEvaluationDto.setMutant(true);
                    sqsService.sendNotificationSQS(Json.toJson(mutationEvaluationDto));
                    return true;
                }
            }catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
                log.error(e.getMessage());
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }

        try {
            if (countFound < 2) {
                MutationEvaluationDto mutationEvaluationDto = new MutationEvaluationDto();
                mutationEvaluationDto.setDna(dna);
                mutationEvaluationDto.setMutant(false);
                sqsService.sendNotificationSQS(Json.toJson(mutationEvaluationDto));
                return false;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public StatsDto getStats(){
        long countHuman = subjectRepository.countHuman();
        long countMutant = subjectRepository.countMutant();
        double ratio;
        if(countHuman == 0){
            ratio = 1.0;
        }else if(countMutant > 0){
            ratio = (100 * (countMutant + countHuman) / countHuman) * 0.01;
        }else{
            ratio = 0.0;
        }
        StatsDto statsDto = new StatsDto();
        statsDto.setCountHumanDna(countHuman);
        statsDto.setCountMutantDna(countMutant);
        statsDto.setRatio(ratio);

        return statsDto;
    }

    private CompletableFuture<Integer> checkDnaMutantHorizontalAsync(String nitrogenousSubject){
        int valueHorizontal = 0;
        int countFound = 0;
        try{
            for(String nitrogenousMutant: mutantGenDto.getDnaHorizontal()){
                if(nitrogenousMutant.contains(nitrogenousSubject.substring(valueHorizontal,valueHorizontal+4))){
                    countFound++;
                    break;
                }else{
                    valueHorizontal++;
                }
            }
        }catch(StringIndexOutOfBoundsException e){
            log.error("nitrogenousSubject out range. ");
        }finally{
            return CompletableFuture.completedFuture(countFound);
        }
    }

    private CompletableFuture<Integer> checkDnaMutantVerticalAsync(String nitrogenousSubject){
        int valueVertical = 0;
        int countFound = 0;
        try{
            for(String nitrogenousMutant: mutantGenDto.getDnaVertical()){
                if(nitrogenousMutant.contains(nitrogenousSubject.substring(valueVertical,valueVertical+4))){
                    countFound++;
                    break;
                }else{
                    valueVertical++;
                }
            }
        }catch(StringIndexOutOfBoundsException e){
            log.error("nitrogenousSubject out range. ");
        }finally{
            return CompletableFuture.completedFuture(countFound);
        }
    }

    private CompletableFuture<Integer> checkDnaMutantDiagonalAsync(String nitrogenousSubject){
        int valueDiagonal = 0;
        int countFound = 0;
        try{
            for(String nitrogenousMutant: mutantGenDto.getDnaDiagonal()){
                if(nitrogenousMutant.contains(nitrogenousSubject.substring(valueDiagonal,valueDiagonal+4))){
                    countFound++;
                    break;
                }else{
                    valueDiagonal++;
                }
            }
        }catch(StringIndexOutOfBoundsException e){
            log.error("nitrogenousSubject out range. ");
        }finally{
            return CompletableFuture.completedFuture(countFound);
        }
    }
}
