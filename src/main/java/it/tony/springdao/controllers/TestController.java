package it.tony.springdao.controllers;

import it.tony.springdao.model.Customer;
import it.tony.springdao.repositories.ICustomerRepository;
import it.tony.springdao.services.CustomerService;
import it.tony.springdao.tables.CustomerTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/")
    public String test() {
        try {
            customerService.save(new Customer("Antonio", 35));
//            Iterable<CustomerTable> all = customerRepository.findAll();
//            for (CustomerTable t : all) {
//                System.out.println(t.getEntity());
//            }

            CustomerTable t = customerRepository.findOne(7);
            System.out.println(t.getEntity());
            t.getEntity().setName("Modified");
            customerService.save(t.getEntity());

            Iterable<Customer> l = customerService.getAll();
            Customer last = null;
            for(Customer c : l) {
                last = c;
                System.out.println(c.getId());
            }
            customerService.delete(last);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Test";
    }
}
