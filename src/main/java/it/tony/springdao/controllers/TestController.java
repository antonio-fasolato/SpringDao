package it.tony.springdao.controllers;

import it.tony.springdao.repositories.ICustomerRepository;
import it.tony.springdao.tables.CustomerTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ICustomerRepository customerRepository;

    @RequestMapping("/")
    public String test() {
        try {
            customerRepository.save(new CustomerTable("Antonio Fasolato", 35));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Test";
    }
}
