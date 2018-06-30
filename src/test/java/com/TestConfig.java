package com;

import com.Service.ProgramMangerService;
import com.Service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig{

    @Bean
    public UserService ProgramManagerService() {
        return new ProgramMangerService();
    }
}


