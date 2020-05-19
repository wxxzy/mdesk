package com.example.adminserver.service;

import com.example.adminserver.dao.IcdDao;
import com.example.adminserver.dao.IcdModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IcdService {
    @Autowired
    private IcdDao icdDao;

    public IcdModel findIcdByCode(String code){
        return icdDao.findIcdByCode(code);
    }

    @Cacheable(cacheNames = {"icd10"})
    public List<IcdModel> findAll( ){
        return icdDao.findAll();
    }

    @Cacheable(cacheNames = {"icd10bylevel"})
    public List<IcdModel> findIcdByLevel(String level){
        return icdDao.findIcdByLevel(level);
    }

    @Cacheable(cacheNames = {"findIcdByLevelStr"})
    public List<String> findIcdByLevelStr(String level){
        return icdDao.findIcdByLevelStr(level);
    }
}
