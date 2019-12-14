package kakaopay.domain;

import kakaopay.utils.RateParser;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Rate {
    @Column(name = "rate_info")
    private String rateInfo;
    @Column(name = "min_rate")
    private double minRate;
    @Column(name = "max_rate")
    private double maxRate;
    @Column(name = "avg_rate")
    private double averageRate;

    private Rate() {
    }

    public Rate(String rateInfo, double minRate, double maxRate, double averageRate) {
        this.rateInfo = rateInfo;
        this.minRate = minRate;
        this.maxRate = maxRate;
        this.averageRate = averageRate;
    }

    public static Rate createRate(String rateInfo) {
        return RateParser.parse(rateInfo);
    }

    public String getRateInfo() {
        return rateInfo;
    }

    public double getMinRate() {
        return minRate;
    }

    public double getMaxRate() {
        return maxRate;
    }

    public double getAverageRate() {
        return averageRate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rateInfo='" + rateInfo + '\'' +
                ", minRate=" + minRate +
                ", maxRate=" + maxRate +
                ", averageRate=" + averageRate +
                '}';
    }
}
