package com.example.dubodemo.matching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Match {
    private static final Logger LOGGER = LoggerFactory.getLogger(Match.class);
    public static void main(String[] args) {
        LOGGER.info("开始匹配");

        MatchingStrategy matchingStrategy1 = new ChineseStringMatcherStrtegy("\"抑郁症;焦虑状态;帕金森病,精神分裂症\"","精神分裂症");
        //MatchingStrategy matchingStrategy1 = new ChineseStringMatcherStrtegy("精神分裂症","\"抑郁症;焦虑状态;帕金森病,精神分裂症\"");
        Context context = new Context(matchingStrategy1);
        context.executeStrategy();

        System.out.println(((ChineseStringMatcherStrtegy) matchingStrategy1).sim());

        MatchingStrategy matchingStrategy = new SimilarityStrategy("支","支气管炎");

        context.changeStrategy(matchingStrategy);
        context.executeStrategy();

        MatchingStrategy strategy = new CosineSimilarityStrategy("支","支气管炎");
        context.changeStrategy(strategy);
        context.executeStrategy();
        System.out.print(((CosineSimilarityStrategy) strategy).sim());
    }
}
