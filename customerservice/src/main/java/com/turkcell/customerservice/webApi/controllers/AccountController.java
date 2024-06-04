package com.turkcell.customerservice.webApi.controllers;

import com.turkcell.customerservice.business.abstracts.AccountService;
import com.turkcell.customerservice.business.dto.requests.account.AddAccountRequest;
import com.turkcell.customerservice.business.dto.requests.account.UpdateAccountRequest;
import com.turkcell.customerservice.business.dto.responses.account.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    
    @PostMapping()
    public ResponseEntity<AddAccountResponse> addAccount(@RequestBody @Valid AddAccountRequest request){
        AddAccountResponse response = accountService.addAccount(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping()
    public UpdateAccountResponse updateAccount(@RequestBody @Valid  UpdateAccountRequest request){
        return accountService.updateAccount(request);
    }

    @DeleteMapping("deleteById/{id}")
    public String deleteByIdAccount(@PathVariable int id){
        return accountService.deleteAccountById(id);
    }

    @GetMapping("{id}")
    public GetAccountByIdResponse getAccountById(@PathVariable int id){
        return accountService.getAccountById(id);
    }

    @GetMapping("getAccountByCustomerId/{id}")
    public List<GetAccountByCustomerIdResponse> getAccountsByCustomerId(@PathVariable int id){
        return accountService.getAccountByCustomerId(id);
    }

    @GetMapping("getAllAccounts")
    public List<GetAllAccountsResponse> getAllAccounts(){
        return accountService.getAllAccounts();
    }
}
