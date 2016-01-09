package it.tony.springdao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringDaoApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringDaoApplication.class, args);
    }
}
