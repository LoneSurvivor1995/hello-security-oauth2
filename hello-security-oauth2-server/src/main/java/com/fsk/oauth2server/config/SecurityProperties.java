package com.fsk.oauth2server.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "validate")
@Data
public class SecurityProperties {
    private boolean codeSwitch;
    private boolean smsSwitch;
}
