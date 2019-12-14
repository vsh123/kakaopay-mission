package kakaopay;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KakaopayApplication {
    private static final String PROPERTIES = "spring.config.location=classpath:/jwt.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(KakaopayApplication.class)
                .properties(PROPERTIES)
                .run(args);
    }
}
