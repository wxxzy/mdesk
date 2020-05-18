package com.example.adminserver.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MatchSimnetBowDao {

    @SelectProvider(type = MatchSimnetBowMapper.class,method = "findAll")
    List<MatchSimnetBowModel> findAll();

    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO t_match_simnet_bow(old_diagnosis,new_diagnosis  ,icd10,score,manual,manual_score) " +
            "VALUES(#{oldDiagnosis}, #{newDiagnosis}, #{icd10}, #{score},#{manual},#{manualScore})")
    int insert(MatchSimnetBowModel model);
}
