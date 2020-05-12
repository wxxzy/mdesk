package com.example.adminserver.service;

import com.example.adminserver.dao.ManualDiagnoseDao;
import com.example.adminserver.dao.ManualDiagnoseModel;
import com.example.adminserver.dao.MatchDao;
import com.example.adminserver.dao.MatchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManualService {
    @Autowired
    private ManualDiagnoseDao manualDiagnoseDao;


    public void insert(ManualDiagnoseModel manualDiagnoseModel){
        manualDiagnoseDao.insert(manualDiagnoseModel);
    }
}
