package com.example.adminserver.service;

import com.example.adminserver.dao.MatchDao;
import com.example.adminserver.dao.MatchModel;
import com.example.adminserver.dao.MatchSimnetBowDao;
import com.example.adminserver.dao.MatchSimnetBowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchSimnetBowService {
    @Autowired
    private MatchSimnetBowDao matchSimnetBowDao;


    //@Cacheable(cacheNames = {"icd10"})
    public List<MatchSimnetBowModel> findAll( ){
        return matchSimnetBowDao.findAll();
    }

    public void insert(MatchSimnetBowModel matchModel){
        matchSimnetBowDao.insert(matchModel);
    }
}
