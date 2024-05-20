package com.turkcell.customerservice.dataAccess;

import com.turkcell.customerservice.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
