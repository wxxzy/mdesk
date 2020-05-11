package com.example.adminserver.dao;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class MatchMapper {

    public String findAll(){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("t_match");
        return sql.toString();
    }
}
