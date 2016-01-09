package it.tony.springdao.repositories;

import it.tony.springdao.tables.CustomerTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICustomerRepository extends CrudRepository<CustomerTable, Integer> {
    @Query("SELECT c FROM customer c ORDER BY created_at DESC")
    public List<CustomerTable> findAllOrdered();
}
