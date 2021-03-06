package com.example.dubodemo.matching;

public class Context {

    private MatchingStrategy strategy;

    public Context(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void changeStrategy(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.execute();
    }
}
