package io.github.daviszhao.stonemason.jdjr.api.service.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JDJRConfig.class)
public class JDJRConfiguration {
}
