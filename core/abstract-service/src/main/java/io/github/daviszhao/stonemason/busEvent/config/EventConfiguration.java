package io.github.daviszhao.stonemason.busEvent.config;

import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@RemoteApplicationEventScan("io.github.daviszhao.stonemason.busEvent")
public class EventConfiguration {
}
