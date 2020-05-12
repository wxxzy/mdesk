package com.example.adminserver.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ManualDiagnoseDao {

    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO t_manual_diagnose(diagnose) VALUES(#{diagnose})")
    int insert(ManualDiagnoseModel model);
}
