package com.example.adminserver.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MatchDao {

    @SelectProvider(type = MatchMapper.class,method = "findAll")
    List<MatchModel> findAll();

    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO t_match(old_diagnosis,new_diagnosis  ,icd10,score) VALUES(#{oldDiagnosis}, #{newDiagnosis}, #{icd10}, #{score})")
    int insert(MatchModel model);
}
