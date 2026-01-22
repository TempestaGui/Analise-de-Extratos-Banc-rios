package org.aplicacao.BankTransactionalAnalyzer.entities;

public class SummaryStatistics {

    private final double sum;
    private final double max;
    private final double min;
    private double average;

    public SummaryStatistics(double sum, double max, double min, double average) {
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.average = average;
    }

    public SummaryStatistics(double sum, double max, double min){
        this.sum = sum;
        this.max = max;
        this.min = min;
    }

    public double getSum() {return sum;}

    public double getMax() {return max;}

    public double getMin() {return min;}

    public double getAverage() {return average;}
}
