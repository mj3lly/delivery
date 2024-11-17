package com.jel.delivery.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "voucher.api")
public class VoucherApiConfig {

    private String scheme;
    private String host;
    private String path;
    private String key;

}
