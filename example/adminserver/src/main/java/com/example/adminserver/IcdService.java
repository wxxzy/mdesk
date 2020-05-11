package com.example.adminserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IcdService {
    @Autowired
    private IcdDao icdDao;

    public IcdModel findIcdByCode(String code){
        return icdDao.findIcdByCode(code);
    }

    public List<IcdModel> findAll( ){
        return icdDao.findAll();
    }
}
