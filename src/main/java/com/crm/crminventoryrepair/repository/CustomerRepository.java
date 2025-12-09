package com.crm.crminventoryrepair.repository;

import com.crm.crminventoryrepair.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // dodatkowe zapytania: findByCompanyName, findByEmail itp.
}