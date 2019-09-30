package com.example.neo4j.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/2/13 17:28
 */
@NodeEntity
public class Disease {
    @Id
    @GeneratedValue
    private Long id;

    private String prevent;
    private String name;
    private String cureLasttime;
    private String curedProb;
    private String cause;
    private String desc;
    private String easyGet;

    private List<String> cureDepartment;
    private List<String> cureWay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrevent() {
        return prevent;
    }

    public void setPrevent(String prevent) {
        this.prevent = prevent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCureLasttime() {
        return cureLasttime;
    }

    public void setCureLasttime(String cureLasttime) {
        this.cureLasttime = cureLasttime;
    }

    public String getCuredProb() {
        return curedProb;
    }

    public void setCuredProb(String curedProb) {
        this.curedProb = curedProb;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEasyGet() {
        return easyGet;
    }

    public void setEasyGet(String easyGet) {
        this.easyGet = easyGet;
    }

    public List<String> getCureDepartment() {
        return cureDepartment;
    }

    public void setCureDepartment(List<String> cureDepartment) {
        this.cureDepartment = cureDepartment;
    }

    public List<String> getCureWay() {
        return cureWay;
    }

    public void setCureWay(List<String> cureWay) {
        this.cureWay = cureWay;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", prevent='" + prevent + '\'' +
                ", name='" + name + '\'' +
                ", cureLasttime='" + cureLasttime + '\'' +
                ", curedProb='" + curedProb + '\'' +
                ", cause='" + cause + '\'' +
                ", desc='" + desc + '\'' +
                ", easyGet='" + easyGet + '\'' +
                ", cureDepartment=" + cureDepartment +
                ", cureWay=" + cureWay +
                '}';
    }
}
