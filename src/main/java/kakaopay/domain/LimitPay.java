package kakaopay.domain;

import kakaopay.utils.LimitParser;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LimitPay {
    @Column(name = "limit_support")
    private String limit;

    @Column(name = "limit_pay")
    private Long pay;

    private LimitPay() {
    }

    private LimitPay(String limitPay) {
        this.limit = limitPay;
        this.pay = LimitParser.parse(limitPay);
    }

    public static LimitPay createLimitPay(String limitPay) {
        return new LimitPay(limitPay);
    }

    public String getLimit() {
        return limit;
    }

    public Long getPay() {
        return pay;
    }

    @Override
    public String toString() {
        return "LimitPay{" +
                "limit='" + limit + '\'' +
                ", pay=" + pay +
                '}';
    }
}
