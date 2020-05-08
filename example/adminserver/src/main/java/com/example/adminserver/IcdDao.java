package com.example.adminserver;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface  IcdDao {
    @SelectProvider(type = IcdMapper.class,method = "findIcdByCode")
    IcdModel findIcdByCode(String code);
}
