package com.turkcell.customerservice.dataAccess;

import com.turkcell.customerservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
