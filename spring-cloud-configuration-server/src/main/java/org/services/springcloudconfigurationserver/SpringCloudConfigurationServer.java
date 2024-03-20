package org.services.springcloudconfigurationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigurationServer {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigurationServer.class, args);
    }

}
