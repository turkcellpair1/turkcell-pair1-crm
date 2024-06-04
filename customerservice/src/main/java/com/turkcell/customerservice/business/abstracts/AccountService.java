package com.turkcell.customerservice.business.abstracts;

import com.turkcell.customerservice.business.dto.requests.account.AddAccountRequest;
import com.turkcell.customerservice.business.dto.requests.account.UpdateAccountRequest;
import com.turkcell.customerservice.business.dto.responses.account.*;

import java.util.List;

public interface AccountService {
    AddAccountResponse addAccount(AddAccountRequest request);
    UpdateAccountResponse updateAccount(UpdateAccountRequest request);
    String deleteAccountById(int id);
    GetAccountByIdResponse getAccountById(int id);
    List<GetAccountByCustomerIdResponse> getAccountByCustomerId(int id);
    List<GetAllAccountsResponse> getAllAccounts();
}
