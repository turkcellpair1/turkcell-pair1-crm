package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.SearchService;
import com.turkcell.customerservice.business.dto.requests.search.SearchCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.search.SearchCustomerResponse;
import com.turkcell.customerservice.core.utilities.exceptions.BusinessException;
import com.turkcell.customerservice.dataAccess.AccountRepository;
import com.turkcell.customerservice.dataAccess.ContactRepository;
import com.turkcell.customerservice.dataAccess.CustomerRepository;
import com.turkcell.customerservice.entities.Account;
import com.turkcell.customerservice.entities.Contact;
import com.turkcell.customerservice.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchManager implements SearchService {
    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<SearchCustomerResponse> searchCustomer(SearchCustomerRequest request) {
        List<Customer> customers = new ArrayList<>();

        if (request.getId() > 0) {
            customerRepository.findByIdAndStatusTrue(request.getId()).ifPresent(customers::add);
        }
        else if (request.getNationalityId() != null) {
            customerRepository.findByNationalityIdAndStatusTrue(request.getNationalityId()).ifPresent(customers::add);
        }
        else if (request.getAccountNumber() > 0) {
            accountRepository.findByAccountNumberAndStatusTrue(request.getAccountNumber()).ifPresent(account -> {
                customerRepository.findByIdAndStatusTrue(account.getCustomer().getId()).ifPresent(customers::add);
            });
        }
        else if (request.getGsmNumber() != null) {
            contactRepository.findByMobilePhoneAndStatusTrue(request.getGsmNumber()).ifPresent(contact -> {
                customerRepository.findByIdAndStatusTrue(contact.getCustomer().getId()).ifPresent(customers::add);
            });
        }
        else if (request.getFirstName() != null && request.getLastName() != null) {
            customers.addAll(customerRepository.findByFirstNameIgnoreCaseStartingWithAndLastNameIgnoreCaseStartingWithAndStatusTrue(request.getFirstName(), request.getLastName()));
        }
        else if (request.getFirstName() != null) {
            customers.addAll(customerRepository.findByFirstNameIgnoreCaseStartingWithAndStatusTrue(request.getFirstName()));
        }
        else if (request.getLastName() != null) {
            customers.addAll(customerRepository.findByLastNameIgnoreCaseStartingWithAndStatusTrue(request.getLastName()));
        }

        if (!customers.isEmpty()) {
            return customers.stream()
                    .map(this::mapToSearchCustomerResponse)
                    .collect(Collectors.toList());
        }

        throw new BusinessException("No customer found! Would you like to create the customer?");
    }

    private SearchCustomerResponse mapToSearchCustomerResponse(Customer customer) {
        return new SearchCustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getMiddleName(),
                customer.getLastName(),
                customer.getNationalityId(),
                determineUserRole(customer.getUser_id())
        );
    }

    private String determineUserRole(int userId) {
        // Kullanıcının rolünü belirlemek için gerekli iş mantığı burada yer alacaktır.
        return "CUSTOMER";
    }

}
