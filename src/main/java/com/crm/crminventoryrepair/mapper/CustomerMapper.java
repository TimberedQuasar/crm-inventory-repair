package com.crm.crminventoryrepair.mapper;

import com.crm.crminventoryrepair.dto.CustomerCreateDto;
import com.crm.crminventoryrepair.dto.CustomerDto;
import com.crm.crminventoryrepair.entity.Customer;
import org.springframework.stereotype.Component;

/**
 * Ręczny mapper DTO <-> Encja dla Customer.
 *
 * Umożliwia konwersję danych wejściowych na encję i odwrotnie,
 * bez użycia zewnętrznych bibliotek (np. MapStruct).
 */
@Component
public class CustomerMapper {

    /**
     * Mapuje CustomerCreateDto na encję Customer.
     * Pola czasu tworzenia/aktualizacji są ustawiane przez lifecycle metody w encji.
     *
     * @param dto dane wejściowe klienta
     * @return nowa encja Customer
     */
    public Customer toEntity(CustomerCreateDto dto) {
        if (dto == null) return null;
        Customer c = new Customer();
        c.setCompanyName(dto.getCompanyName());
        // c.setContactPerson(dto.getContactPerson()); // jeśli pole istnieje w DTO
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        c.setAddress(dto.getAddress());
        c.setCity(dto.getCity());
        c.setState(dto.getState());
        c.setZip(dto.getZip());
        return c;
    }

    /**
     * Mapuje encję Customer na CustomerDto do zwracania w API.
     *
     * @param entity encja klienta
     * @return DTO z danymi do odpowiedzi
     */
    public CustomerDto toDto(Customer entity) {
        if (entity == null) return null;
        CustomerDto d = new CustomerDto();
        d.setId(entity.getId());
        d.setCompanyName(entity.getCompanyName());
        d.setContactPerson(entity.getContactPerson());
        d.setEmail(entity.getEmail());
        d.setPhone(entity.getPhone());
        d.setAddress(entity.getAddress());
        d.setCity(entity.getCity());
        d.setState(entity.getState());
        d.setZip(entity.getZip());
        d.setCreatedAt(entity.getCreatedAt());
        d.setUpdatedAt(entity.getUpdatedAt());
        return d;
    }

    /**
     * Częściowa aktualizacja encji polami z DTO (ignoruje id, createdAt, updatedAt).
     * Można użyć dla operacji PATCH/PUT.
     *
     * @param dto dane wejściowe (tylko nie-null zaktualizują encję)
     * @param entity encja do modyfikacji
     */
    public void updateFromDto(CustomerCreateDto dto, Customer entity) {
        if (dto == null || entity == null) return;
        if (dto.getCompanyName() != null) entity.setCompanyName(dto.getCompanyName());
        // if (dto.getContactPerson() != null) entity.setContactPerson(dto.getContactPerson());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getPhone() != null) entity.setPhone(dto.getPhone());
        if (dto.getAddress() != null) entity.setAddress(dto.getAddress());
        if (dto.getCity() != null) entity.setCity(dto.getCity());
        if (dto.getState() != null) entity.setState(dto.getState());
        if (dto.getZip() != null) entity.setZip(dto.getZip());
    }
}