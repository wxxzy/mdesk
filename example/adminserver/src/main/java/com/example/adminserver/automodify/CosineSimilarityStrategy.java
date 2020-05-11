package com.example.adminserver.automodify;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 余弦计算相似度度量
 * 余弦相似度用向量空间中两个向量夹角的余弦值作为衡量两个个体间差异的大小。
 * 余弦值越接近1，就表明夹角越接近0度，也就是两个向量越相似，即"余弦相似性"。
 */
public class CosineSimilarityStrategy implements MatchingStrategy {

    Map<Character, int[]> vectorMap = new HashMap<Character, int[]>();
    int[] tempArray = null;
    private String originString;
    private String matchString;

    public CosineSimilarityStrategy(String orignText, String matchText) {
        super();

        this.originString = orignText;
        this.matchString = matchText;

    }

    public String getOriginString() {
        return originString;
    }

    public void setOriginString(String originString) {
        this.originString = originString;
    }

    public String getMatchString() {
        return matchString;
    }

    public void setMatchString(String matchString) {
        this.matchString = matchString;
    }

    @Override
    public void execute() {
        for(Character sch:originString.toCharArray()){

            if(vectorMap.containsKey(sch)){

                vectorMap.get(sch)[0]++;
            }
            //用将字符转化为向量
            else{

                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(sch, tempArray);
            }

        }


        for(Character tch:matchString.toCharArray()){

            if(vectorMap.containsKey(tch)){

                vectorMap.get(tch)[1]++;
            }
            //用将字符转化为向量
            else{

                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(tch, tempArray);
            }

        }
    }

    // 求余弦相似度
    public double sim() {
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }


    // 求平方和
    private double squares(Map<Character, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }

    // 点乘法
    private double pointMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }

    private double sqrtMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }
}
