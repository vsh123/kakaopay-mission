package kakaopay.domain;

import kakaopay.utils.RateParser;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Rate {
    @Column(name = "rate_info")
    private String rateInfo;
    @Column(name = "min_sup_rate")
    private double minRate;
    @Column(name = "max_sup_rate")
    private double maxRate;

    private Rate() {
    }

    public Rate(String rateInfo, double minRate, double maxRate) {
        this.rateInfo = rateInfo;
        this.minRate = minRate;
        this.maxRate = maxRate;
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

    @Override
    public String toString() {
        return "Rate{" +
                "rateInfo='" + rateInfo + '\'' +
                ", minRate=" + minRate +
                ", maxRate=" + maxRate +
                '}';
    }
}
