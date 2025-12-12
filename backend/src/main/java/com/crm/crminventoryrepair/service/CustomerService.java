package com.crm.crminventoryrepair.service;

import com.crm.crminventoryrepair.dto.CustomerCreateDto;
import com.crm.crminventoryrepair.dto.CustomerDto;
import com.crm.crminventoryrepair.entity.Customer;
import com.crm.crminventoryrepair.mapper.CustomerMapper;
import com.crm.crminventoryrepair.repository.CustomerRepository;
import com.crm.crminventoryrepair.service.exception.DuplicateResourceException;
import com.crm.crminventoryrepair.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Warstwa serwisowa dla operacji na klientach.
 *
 * Odpowiada za logikę biznesową, walidację duplikatów,
 * mapowanie między DTO i encją oraz pracę w transakcjach.
 */
@Service
public class CustomerService {

    private final CustomerRepository repo;
    private final CustomerMapper mapper;

    /**
     * Konstruktor wstrzykujący repozytorium i mapper.
     */
    public CustomerService(CustomerRepository repo, CustomerMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    /**
     * Znajdź wszystkich klientów.
     * Transakcyjnie tylko do odczytu.
     *
     * @return lista CustomerDto
     */
    @Transactional(readOnly = true)
    public List<CustomerDto> findAll() {
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    /**
     * Znajdź klienta po ID.
     * Rzuca ResourceNotFoundException, jeśli klient nie istnieje.
     *
     * @param id identyfikator klienta
     * @return CustomerDto
     */
    @Transactional(readOnly = true)
    public CustomerDto findById(Integer id) {
        Customer c = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
        return mapper.toDto(c);
    }

    /**
     * Utwórz nowego klienta.
     * - Waliduje duplikaty po companyName.
     * - Mapuje DTO na encję i zapisuje w repozytorium.
     * - Zwraca CustomerDto zapisanej encji.
     *
     * @param dto dane wejściowe klienta
     * @return CustomerDto utworzonego klienta
     */
    @Transactional
    public CustomerDto create(CustomerCreateDto dto) {
        if (dto.getCompanyName() != null && repo.existsByCompanyName(dto.getCompanyName())) {
            throw new DuplicateResourceException("Customer with companyName already exists: " + dto.getCompanyName());
        }
        Customer ent = mapper.toEntity(dto);
        Customer saved = repo.save(ent);
        return mapper.toDto(saved);
    }
}