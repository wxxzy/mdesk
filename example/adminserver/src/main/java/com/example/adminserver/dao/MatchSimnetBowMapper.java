package com.example.adminserver.dao;

import org.apache.ibatis.jdbc.SQL;

public class MatchSimnetBowMapper {

    public String findAll(){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("t_match_simnet_bow");
        return sql.toString();
    }
}
