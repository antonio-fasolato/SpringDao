package it.tony.springdao.controllers;

import it.tony.springdao.model.Customer;
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
            customerRepository.save(new CustomerTable(new Customer("Antonio", 35)));
            Iterable<CustomerTable> all = customerRepository.findAll();
            for (CustomerTable t : all) {
                System.out.println(t.getEntity());
            }

            CustomerTable t = customerRepository.findOne(7);
            System.out.println(t.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Test";
    }
}
