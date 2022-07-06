package nts.sixblack.hexa;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class HexaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HexaApplication.class, args);
    }

}
