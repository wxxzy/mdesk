package com.mungo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/11/19 15:04
 */
public class ReflectObj {
    private String field01;
    public String getField01() {
        return this.field01;
    }
    public void setField01(String field01) {
        this.field01 = field01;
    }

    public static void main(String[] args) {
        createObj();
        reflectObject();
    }

    private static void reflectObject(){
        try {
            // 直接获取对象实例
            ReflectObj obj = ReflectObj.class.newInstance();
            // 直接访问Field
            Field field = ReflectObj.class.getField("field01");
            field.setAccessible(true);
            field.set(obj, "value01");
            // 调用对象的public函数
            Method method = ReflectObj.class.getMethod("getField01");
            System.out.println((String) method.invoke(obj));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void createObj(){
        ReflectObj obj = new ReflectObj();
        obj.setField01("value01");
        System.out.println(obj.getField01());
    }
}
