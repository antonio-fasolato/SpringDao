package it.tony.springdao.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {
    @Bean
    public static CustomerService customerService() {
        return new CustomerService();
    }
}
