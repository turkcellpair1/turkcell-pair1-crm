package com.turkcell.customerservice.dataAccess;

import com.turkcell.customerservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
