package com.crm.crminventoryrepair.repository;

import com.crm.crminventoryrepair.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
