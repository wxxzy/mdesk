package com.example.adminserver.automodify;

public class ChineseStringMatcherStrtegy implements MatchingStrategy {

    public static final char MATCH = '十';
    public static final char MISMATCH = '口';

    private static final char[] cnSignArr = "。？！，、；：“” ‘’「」『』（）〔〕【】—﹏…～·《》〈〉".toCharArray();
    private static final char[] enSignArr = "`!@#$%^&*()_+~-=[]\\;',./{}|:\"<>?".toCharArray();

    private String originString;
    private String matchString;

    public void setOriginString(String originString) {
        this.originString = originString;
    }

    public void setMatchString(String matchString) {
        this.matchString = matchString;
    }

    private String orgWords;
    private String mtcWords;

    private char[] org;
    private char[] mtc;
    private int[] indicator;

    private double matchingRate;
    private double accuracyRate;
    private int absent;
    private int excess;


    public ChineseStringMatcherStrtegy(String orignText, String matchText) {
        super();

        this.originString = orignText;
        this.matchString = matchText;

        //init variables
        initVariables();

        //do process
        doProcess();
    }

    private void initVariables() {
        //init char array

        orgWords = clearNonWordChar(originString);
        mtcWords = clearNonWordChar(matchString);

        org = orgWords.toCharArray();
        mtc = mtcWords.toCharArray();

        //init indicator
        int i=0;
        indicator = new int[mtc.length]; // init with 0

    }


    public int[] getIndicator() {
        return indicator;
    }


    public double getMatchingRate() {
        return matchingRate;
    }

    public double getAccuracyRate()
    {
        return accuracyRate;
    }


    public int getAbsent() {
        return absent;
    }


    public int getExcess() {
        return excess;
    }

    public String getOriginString() {
        return originString;
    }


    public String getMatchString() {
        return matchString;
    }


    public char[] getOrg() {
        return org;
    }


    public char[] getMtc() {
        return mtc;
    }

    private String clearNonWordChar(String str) {
        for(int i=0;i<cnSignArr.length;i++)
            str = str.replace(""+cnSignArr[i], "");
        for(int i=0;i<enSignArr.length;i++)
            str = str.replace(""+enSignArr[i], "");
        return str;
    }

    public void doProcess() {
        int index = 0;
        double matchCount = 0;
        for(int i=0;i<mtc.length;i++)
        {
            for (int j = index; j < org.length; j++)
            {
                if(mtc[i] == org[j])
                {
                    index++;
                    indicator[i] = 1;
                    matchCount++;
                    break;
                }
            }
        }

        matchingRate = matchCount/orgWords.length()*100;
        accuracyRate = matchingRate * matchCount / mtcWords.length();

        if(orgWords.length() > matchCount)
            absent = orgWords.length() - (int)matchCount;
        if(mtcWords.length() - (int)matchCount != 0)
            excess = mtcWords.length() - (int)matchCount;

    }

    public double sim() {
        return matchingRate;
    }

    @Override
    public void execute() {
        System.out.println(" - - - - Start - - - - ");
        System.out.printf("%-8s%8s\n","原串：",originString);
        System.out.printf("%-8s%8s\n","目标串：",matchString);

        System.out.println();
        System.out.printf("%-16s\n","匹配结果："
                + "");
        for(int i=0;i<mtc.length;i++)
        {
            System.out.printf("%2c",mtc[i]);
        }
        System.out.println();
        for(int i=0;i<mtc.length;i++) {
            System.out.printf("%2c",indicator[i]==0?MATCH:MISMATCH);
        }
        System.out.println();

        System.out.printf("匹配率：%.2f%%\n",matchingRate);
        System.out.printf("准确率：%.2f%%\n",accuracyRate);
        System.out.println();
        if(absent != 0)
            System.out.printf("\n比原串少%d个字\n",absent);
        if(excess != 0)
            System.out.printf("\n比原串多%d个字\n",excess);
        System.out.println(" - - - - Start - - - - ");

    }
}
