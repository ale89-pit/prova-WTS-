package it.pit.security.authservice.security.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigEnv {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().ignoreIfMalformed().ignoreIfMissing().directory("CLASSPATH").load();
    }
}
