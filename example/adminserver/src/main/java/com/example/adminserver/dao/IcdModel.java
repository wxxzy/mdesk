package com.example.adminserver.dao;

import lombok.Data;

import java.io.Serializable;

@Data
public class IcdModel implements Serializable {
    private String code;
    private String codeext;
    private String desc;
    private String level;

    @Override
    public String toString() {
        return "IcdModel{" +
                "code='" + code + '\'' +
                ", codeext='" + codeext + '\'' +
                ", desc='" + desc + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
