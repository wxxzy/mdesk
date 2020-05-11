package com.example.adminserver.dao;

import lombok.Data;

import java.io.Serializable;

@Data
public class MatchModel implements Serializable {
    private Long id;
    private String oldDiagnosis;
    private String newDiagnosis;
    private String icd10;
    private double score;


}
