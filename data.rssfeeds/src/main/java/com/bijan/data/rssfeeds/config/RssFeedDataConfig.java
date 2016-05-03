package com.bijan.data.rssfeeds.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@Configuration
@EnableJpaRepositories(basePackages = "com.bijan.data.rssfeeds")
@ComponentScan({ "com.bijan.data.rssfeeds", "com.bijan.data.rssfeeds.service","com.bijan.data.rssfeeds.converter" })
@EntityScan("com.bijan.data.rssfeeds.model")
public class RssFeedDataConfig {

}
