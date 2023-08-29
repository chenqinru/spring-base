package com.eztech.springbase.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Security忽略url
 *
 * @author chenqinru
 * @date 2023/07/26
 */
@ConfigurationProperties(prefix = "security.ignore")
@Component
public class SecurityIgnoreUrl {
    private String[] urls;

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }
}

