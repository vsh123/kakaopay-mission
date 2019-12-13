package kakaopay.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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
}
