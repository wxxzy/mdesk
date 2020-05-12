package com.example.adminserver.service;

import com.example.adminserver.dao.MatchDao;
import com.example.adminserver.dao.MatchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    @Autowired
    private MatchDao matchDao;


    //@Cacheable(cacheNames = {"icd10"})
    public List<MatchModel> findAll( ){
        return matchDao.findAll();
    }

    public void insert(MatchModel matchModel){
        matchDao.insert(matchModel);
    }
}
