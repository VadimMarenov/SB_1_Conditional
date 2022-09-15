package ru.maren.sb_1_conditional.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.maren.sb_1_conditional.profile.DevProfile;
import ru.maren.sb_1_conditional.profile.ProductionProfile;
import ru.maren.sb_1_conditional.profile.SystemProfile;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(
            prefix = "netology.profile",
            name = "dev",
            havingValue = "true",
            matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "netology.profile",
            name = "dev",
            havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
