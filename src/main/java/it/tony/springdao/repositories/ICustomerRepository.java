package it.tony.springdao.repositories;

import it.tony.springdao.tables.CustomerTable;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<CustomerTable, Integer> {
}
