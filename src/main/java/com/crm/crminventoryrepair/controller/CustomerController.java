package com.crm.crminventoryrepair.controller;

import com.crm.crminventoryrepair.dto.CustomerCreateDto;
import com.crm.crminventoryrepair.dto.CustomerDto;
import com.crm.crminventoryrepair.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Kontroler REST obsługujący operacje na zasobie "Customer".
 *
 * Bazowy adres dla wszystkich endpointów: /api/customers (zdefiniowane przez server.servlet.context-path=/api
 * oraz @RequestMapping("/customers")).
 *
 * Udostępnia operacje:
 * - pobranie listy klientów (GET /api/customers),
 * - pobranie konkretnego klienta po ID (GET /api/customers/{id}),
 * - utworzenie nowego klienta (POST /api/customers).
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    /**
     * Warstwa serwisowa odpowiedzialna za logikę biznesową klientów.
     */
    private final CustomerService service;

    /**
     * Tworzy kontroler klientów wstrzykując zależność serwisu.
     *
     * @param service serwis klientów
     */
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    /**
     * Zwraca listę wszystkich klientów.
     *
     * Endpoint: GET /api/customers
     *
     * @return lista obiektów CustomerDto reprezentujących klientów
     */
    @GetMapping
    public List<CustomerDto> list() {
        return service.findAll();
    }

    /**
     * Zwraca szczegóły klienta o podanym identyfikatorze.
     *
     * Endpoint: GET /api/customers/{id}
     *
     * @param id identyfikator klienta (Integer)
     * @return odpowiedź HTTP 200 OK z CustomerDto w body; jeśli klient nie istnieje, serwis rzuci wyjątek 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable Integer id) {
        CustomerDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Tworzy nowego klienta na podstawie danych wejściowych.
     *
     * Endpoint: POST /api/customers
     * Walidacja: dane wejściowe są walidowane adnotacjami z CustomerCreateDto (np. email, telefon, zip).
     *
     * @param dto dane wejściowe klienta (CustomerCreateDto), walidowane przez @Valid
     * @return odpowiedź HTTP 201 Created, Location: /customers/{id} oraz CustomerDto w body
     */
    @PostMapping
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerCreateDto dto) {
        CustomerDto saved = service.create(dto);
        return ResponseEntity.created(URI.create("/customers/" + saved.getId())).body(saved);
    }
}