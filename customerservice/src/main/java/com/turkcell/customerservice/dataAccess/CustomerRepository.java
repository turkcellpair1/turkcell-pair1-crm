package com.turkcell.customerservice.dataAccess;

import com.turkcell.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByIdAndStatusTrue(int id);
    Optional<Customer> findByNationalityIdAndStatusTrue(String nationalityId);
    List<Customer> findByStatusTrue();
    boolean existsByNationalityId(String id);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.user_id = :user_id")
    boolean existsByUserId(@Param("user_id") int user_id);
    Collection<? extends Customer> findByFirstNameIgnoreCaseStartingWithAndLastNameIgnoreCaseStartingWithAndStatusTrue(String firstName, String lastName);
    Collection<? extends Customer> findByFirstNameIgnoreCaseStartingWithAndStatusTrue(String firstName);
    Collection<? extends Customer> findByLastNameIgnoreCaseStartingWithAndStatusTrue(String lastName);
}
