package nts.sixblack.hexa.config;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.*;

@Configuration
public class CloudDinaryConfig {

    @Value("${cloud.dinary.name}")
    private String cloudName;

    @Value("${cloud.dinary.api_key}")
    private String apiKey;

    @Value("${cloud.dinary.api_secret}")
    private String apiSecret;

    @Value("${cloud.dinary.api_environment}")
    private String apiEnvironment;

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true
        ));
        return cloudinary;
    }
}
