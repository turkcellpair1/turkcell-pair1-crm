package com.turkcell.customerservice.dataAccess;

import com.turkcell.customerservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    Optional<Address> findByIdAndStatusTrue(int id);
    List<Address> findByCustomerIdAndStatusTrue(int id);
    List<Address> findByStatusTrue();
    List<Address> findByCustomerIdAndDefaultAddressTrue(int id);
}
