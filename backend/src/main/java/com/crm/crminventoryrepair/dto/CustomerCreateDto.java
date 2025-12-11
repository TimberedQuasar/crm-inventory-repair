package com.crm.crminventoryrepair.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateDto {

    @NotBlank(message = "Nazwa firmy jest wymagana")
    @Size(max = 100, message = "Nazwa firmy nie może przekraczać 100 znaków")
    private String companyName;

    @Size(max = 100, message = "Imię i nazwisko nie może przekraczać 100 znaków")
    private String contactPerson;

    @Email(message = "Nieprawidłowy format email")
    @Size(max = 100, message = "Email nie może przekraczać 100 znaków")
    private String email;

    @Pattern(regexp = "^\\+?[0-9\\s\\-\\(\\)]{9,20}$", message = "Nieprawidłowy numer telefonu")
    private String phone;

    @Size(max = 200, message = "Adres nie może przekraczać 200 znaków")
    private String address;

    @Size(max = 50, message = "Miasto nie może przekraczać 50 znaków")
    private String city;

    @Size(max = 50, message = "Województwo nie może przekraczać 50 znaków")
    private String state;

    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$", message = "Kod pocztowy musi być w formacie XX-XXX")
    private String zip;
}