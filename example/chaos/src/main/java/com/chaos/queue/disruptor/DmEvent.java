package com.chaos.queue.disruptor;

public class DmEvent {
    private String indicatorCode;
    private long indicatorValue;

    public DmEvent() {
    }

    public DmEvent(String indicatorCode, long indicatorValue) {
        this.indicatorCode = indicatorCode;
        this.indicatorValue = indicatorValue;
    }

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    public long getIndicatorValue() {
        return indicatorValue;
    }

    public void setIndicatorValue(long indicatorValue) {
        this.indicatorValue = indicatorValue;
    }

    @Override
    public String toString() {
        return "DmEvent{" +
                "indicatorCode='" + indicatorCode + '\'' +
                ", indicatorValue=" + indicatorValue +
                '}';
    }
}
