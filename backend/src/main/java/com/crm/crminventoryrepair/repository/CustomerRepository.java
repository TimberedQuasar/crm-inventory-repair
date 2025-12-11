package com.crm.crminventoryrepair.repository;

import com.crm.crminventoryrepair.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repozytorium JPA dla encji Customer.
 *
 * Udostępnia standardowe operacje CRUD oraz dodatkowe metody
 * wyszukiwania po unikatowej nazwie firmy.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Znajdź klienta po nazwie firmy.
     *
     * @param companyName nazwa firmy (unikatowa)
     * @return Optional z klientem, jeśli istnieje
     */
    Optional<Customer> findByCompanyName(String companyName);

    /**
     * Sprawdź, czy istnieje klient o podanej nazwie firmy.
     *
     * @param companyName nazwa firmy (unikatowa)
     * @return true jeśli istnieje
     */
    boolean existsByCompanyName(String companyName);
}