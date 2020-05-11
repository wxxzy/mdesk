package com.example.dubodemo.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/9/30 14:58
 */
public class InfluxDBDemo {
    public static void insert(int num){
        InfluxDB db = InfluxDBFactory.connect("http://127.0.0.1:8086", "wangxx", "123456");
        // 设置数据库
        db.setDatabase("mdesk");
        // 创建Builder，设置表名
        Point.Builder builder = Point.measurement("test_measurement");
        // 添加Field
        builder.addField("count",num);
        // 添加Tag
        builder.tag("TAG_CODE","TAG_VALUE_" + num);
        Point point = builder.build();
        db.write(point);
    }
}
