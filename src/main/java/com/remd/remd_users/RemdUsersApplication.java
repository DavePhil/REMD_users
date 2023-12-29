package com.remd.remd_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@RefreshScope
@EnableFeignClients("com.remd.remd_users")
public class RemdUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemdUsersApplication.class, args);
    }

}
