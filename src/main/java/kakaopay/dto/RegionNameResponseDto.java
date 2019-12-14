package kakaopay.dto;

public class RegionNameResponseDto {
    private String region;

    public RegionNameResponseDto() {
    }

    public RegionNameResponseDto(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
