package com.example.dubodemo.matching;

import java.io.*;

public class Test {
    public static void main(String[] args) {

        String path = "C:\\Users\\wangxingxiang\\Desktop\\处方诊断修改.csv";

        String savepath = "C:\\Users\\wangxingxiang\\Desktop\\处方诊断修改1.csv";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            File file = new File(savepath);


            //(文件完整路径),编码格式
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, "GBK"));//GBK
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");
                String last = item[item.length-1];


                MatchingStrategy strategy = new ChineseStringMatcherStrtegy(item[1],item[0]);
                Context context = new Context(strategy);
                context.executeStrategy();

                StringBuffer sb = new StringBuffer();
                sb.append(item[0]).append(",").append(item[1]).append(",")
                        .append(((ChineseStringMatcherStrtegy) strategy).sim()).append("\n");
                System.out.print(sb.toString());
                fileOutputStream.write(sb.toString().getBytes());

                //fileOutputStream.write(content.getBytes());
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
