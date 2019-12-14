package kakaopay.domain;

import kakaopay.utils.RateParser;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Rate {
    @Column(name = "rate_info")
    private String rateInfo;
    @Column(name = "avg_rate")
    private double averageRate;

    private Rate() {
    }

    public Rate(String rateInfo, double averageRate) {
        this.rateInfo = rateInfo;
        this.averageRate = averageRate;
    }

    public static Rate createRate(String rateInfo) {
        return RateParser.parse(rateInfo);
    }

    public String getRateInfo() {
        return rateInfo;
    }

    public double getAverageRate() {
        return averageRate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rateInfo='" + rateInfo + '\'' +
                ", averageRate=" + averageRate +
                '}';
    }
}
