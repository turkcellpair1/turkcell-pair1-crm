package com.turkcell.customerservice.business.dto.requests.customer;

import com.turkcell.customerservice.business.rules.validations.ValidBirthDate;
import com.turkcell.customerservice.business.rules.validations.ValidNationalityId;
import com.turkcell.customerservice.entities.Account;
import com.turkcell.customerservice.entities.Contact;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCustomerRequest {
    @Size(max = 7, message = "Customer id must be max 7 characters")
    private int id;
    @Size(max = 50, message = "First name must be between 3 and 50 characters")
    @Pattern(regexp = "^(?![0-9]+$)[a-zA-ZçÇğĞıİöÖşŞüÜ0-9\\s]*$",
            message = "First name must contain only letters or both letters and numbers and cannot contain only numbers or special characters")
    private String firstName;
    @Size(max = 50, message = "Last name must be between 3 and 50 characters")
    /*@Pattern(regexp = "^(?!\\d+$)[a-zA-Z0-9]+$",
            message = "Last name must contain only letters or both letters and numbers and cannot contain only numbers or special characters")*/
    @Pattern(regexp = "^(?![0-9]+$)[a-zA-ZçÇğĞıİöÖşŞüÜ0-9\\s]*$",
            message = "Last name must contain only letters or both letters and numbers and cannot contain only numbers or special characters")
    private String lastName;
    @ValidNationalityId
    private String nationalityId;

    private Account account;
    private Contact contact;
    //Todo: Contact tablosu tamamlandıktan sonra devam edilecek
    //Todo: Order servis ile de bağlantısı gerek
    private int orderNumer;
}
