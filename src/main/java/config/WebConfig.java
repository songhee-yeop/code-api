package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") //.allowedOrigins("http://localhost:8080", "http://localhost:8081");
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") //.allowedMethods("GET", "POST", "PUT", "DELETE");
                .allowedHeaders("*") //.allowedHeaders(Access-Control-Allow-Headers", "Origin, X-Requested-With");
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }
}
