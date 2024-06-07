package com.turkcell.customerservice.dataAccess;

import com.turkcell.customerservice.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
    Optional<Contact> findByIdAndStatusTrue(int id);
    Optional<Contact> findByCustomerIdAndStatusTrue(int id);
    List<Contact> findByStatusTrue();
}
