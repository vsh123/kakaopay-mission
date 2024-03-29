package kakaopay.dto;

public class RegionSupInfoUpdateRequestDto {
    private String region;
    private String target;
    private String usage;
    private String limit;
    private String rate;
    private String institute;
    private String mgmt;
    private String reception;

    private RegionSupInfoUpdateRequestDto() {
    }

    public RegionSupInfoUpdateRequestDto(String region, String target, String usage, String limit, String rate, String institute, String mgmt, String reception) {
        this.region = region;
        this.target = target;
        this.usage = usage;
        this.limit = limit;
        this.rate = rate;
        this.institute = institute;
        this.mgmt = mgmt;
        this.reception = reception;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getMgmt() {
        return mgmt;
    }

    public void setMgmt(String mgmt) {
        this.mgmt = mgmt;
    }

    public String getReception() {
        return reception;
    }

    public void setReception(String reception) {
        this.reception = reception;
    }
}
