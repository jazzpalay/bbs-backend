package com.ryoga.bbs.scenario.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    private final String s3ClientRegion;

    public S3Config(
            @Value("${app.s3.client.region}")
            String s3ClientRegion
    ) {
        this.s3ClientRegion = s3ClientRegion;
    }

    @Bean
    public S3Client s3Client() {

        return S3Client.builder()
                .region(Region.of(s3ClientRegion))
                .build();
    }


}
