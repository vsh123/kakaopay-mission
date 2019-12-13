package kakaopay.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RegionSupportInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Region region;
    private String target;
    private String usage;
    private String limitPay;
    private String rate;
    private String institute;
    private String mgmt;
    private String reception;

    private RegionSupportInformation() {
    }

    public Long getId() {
        return id;
    }

    public Region getRegion() {
        return region;
    }

    public String getTarget() {
        return target;
    }

    public String getUsage() {
        return usage;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public String getRate() {
        return rate;
    }

    public String getInstitute() {
        return institute;
    }

    public String getMgmt() {
        return mgmt;
    }

    public String getReception() {
        return reception;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionSupportInformation that = (RegionSupportInformation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RegionSupportInformation{" +
                "id=" + id +
                ", region=" + region +
                ", target='" + target + '\'' +
                ", usage='" + usage + '\'' +
                ", limitPay='" + limitPay + '\'' +
                ", rate='" + rate + '\'' +
                ", institute='" + institute + '\'' +
                ", mgmt='" + mgmt + '\'' +
                ", reception='" + reception + '\'' +
                '}';
    }

    public static class Builder {
        private Region region;
        private String target;
        private String usage;
        private String limitPay;
        private String rate;
        private String institute;
        private String mgmt;
        private String reception;

        public Builder region(Region region) {
            this.region = region;
            return this;
        }

        public Builder target(String target) {
            this.target = target;
            return this;
        }

        public Builder usage(String usage) {
            this.usage = usage;
            return this;
        }

        public Builder limitPay(String limitPay) {
            this.limitPay = limitPay;
            return this;
        }

        public Builder rate(String rate) {
            this.rate = rate;
            return this;
        }

        public Builder institute(String institute) {
            this.institute = institute;
            return this;
        }

        public Builder mgmt(String mgmt) {
            this.mgmt = mgmt;
            return this;
        }

        public Builder reception(String reception) {
            this.reception = reception;
            return this;
        }

        public RegionSupportInformation build() {
            return new RegionSupportInformation(this);
        }
    }

    private RegionSupportInformation(Builder builder) {
        this.region = builder.region;
        this.target = builder.target;
        this.usage = builder.usage;
        this.limitPay = builder.limitPay;
        this.rate = builder.rate;
        this.institute = builder.institute;
        this.mgmt = builder.mgmt;
        this.reception = builder.reception;
    }

}
