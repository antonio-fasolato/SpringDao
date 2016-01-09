package it.tony.springdao.controllers;

import it.tony.springdao.model.Customer;
import it.tony.springdao.repositories.ICustomerRepository;
import it.tony.springdao.services.CustomerService;
import it.tony.springdao.services.ServiceException;
import it.tony.springdao.tables.CustomerTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/")
    @Transactional(rollbackFor = {ServiceException.class})
    public String test() throws ServiceException {
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

            Iterable<Customer> l = customerService.getAllOrderByInsertDate();
            Customer last = null;
            for(Customer c : l) {
                last = c;
                System.out.println(c.getId());
//                System.out.println(c);
            }
            customerService.delete(last);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return "Test";
    }
}
