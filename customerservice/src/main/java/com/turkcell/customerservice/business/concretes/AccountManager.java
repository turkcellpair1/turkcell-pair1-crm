package com.turkcell.customerservice.business.concretes;

import com.turkcell.customerservice.business.abstracts.AccountService;
import com.turkcell.customerservice.business.dto.requests.account.AddAccountRequest;
import com.turkcell.customerservice.business.dto.requests.account.UpdateAccountRequest;
import com.turkcell.customerservice.business.dto.responses.account.*;
import com.turkcell.customerservice.core.utilities.exceptions.BusinessException;
import com.turkcell.customerservice.core.utilities.mappers.AccountMapper;
import com.turkcell.customerservice.core.utilities.mappers.NullAwareBeanUtils;
import com.turkcell.customerservice.dataAccess.AccountRepository;
import com.turkcell.customerservice.dataAccess.AddressRepository;
import com.turkcell.customerservice.dataAccess.CustomerRepository;
import com.turkcell.customerservice.entities.Account;
import com.turkcell.customerservice.entities.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountManager implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private static final SecureRandom random = new SecureRandom();

    @Override
    public AddAccountResponse addAccount(AddAccountRequest request) {
        customerRepository.findByIdAndStatusTrue(request.getCustomerId()).orElseThrow(() -> new BusinessException("No customer was found for this id."));
        Address address = addressRepository.findByIdAndStatusTrue(request.getAddressId()).orElseThrow(() -> new BusinessException("No address was found for this id."));
        if(address.getCustomer().getId()!=request.getCustomerId()){
            throw new BusinessException("Customer has not this address. Transaction failed.");
        }

        Account account=AccountMapper.INSTANCE.addRequestToAccount(request);
        /*long timestamp = Instant.now().toEpochMilli();
        int randomSuffix = random.nextInt(1000);

        String uniqueNumber = timestamp + String.format("%03d", randomSuffix);*/
        long timestamp = Instant.now().toEpochMilli();
        int randomSuffix = random.nextInt(1000);
        int truncatedTimestamp = (int) (timestamp % 1000000000L);
        int uniqueNumber = -(truncatedTimestamp * 1000 + randomSuffix);
        account.setAccountNumber(uniqueNumber);
        account.setAccountName(String.valueOf(uniqueNumber));
        account.setAccountStatus("F");
        account.setAccountType("Fatura adresi");
        account.setStatus(true);

        return AccountMapper.INSTANCE.accountToAddResponse(accountRepository.save(account));
    }

    @Override
    public UpdateAccountResponse updateAccount(UpdateAccountRequest request) {


        return accountRepository.findByIdAndStatusTrue(request.getId()).map(account -> {
            NullAwareBeanUtils.copyNonNullProperties(request,account);
            if(request.getAddressId()!=0){
                Address address = addressRepository.findByIdAndStatusTrue(request.getAddressId()).orElseThrow(() -> new BusinessException("No address was found for this id."));
                if(address.getCustomer().getId()!=account.getCustomer().getId()){
                    throw new BusinessException("Customer has not this address. Transaction failed.");
                }
                account.setAddress(address);
            }
            return AccountMapper.INSTANCE.accountToUpdateResponse(accountRepository.save(account));}).orElseThrow(() -> new BusinessException("No account was found for this id. Transaction failed."));
    }

    @Override
    public String deleteAccountById(int id) {
        /*Todo: Kullanıcının, ”Yes” butonuna tıklaması durumunda, sistem ilgili fatura hesabına bağlı bir ürün olup olmadığını sorgulamalıdır. Eğer ilgili fatura hesabına bağlı bir ürün var ise kullanıcıya “There are product/products connected to the billing account“ hata mesajı gösterilmelidir. Bu durumda fatura hesabı için silme işlemi gerçekleştirilmemelidir*/
        Account account= accountRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new BusinessException("No account was found for this id. Transaction failed."));
        account.setStatus(false);
        accountRepository.save(account);
        return "Account deleted.";
    }

    @Override
    public GetAccountByIdResponse getAccountById(int id) {
        return AccountMapper.INSTANCE.accountByIdToGetResponse(accountRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new BusinessException("No account was found for this id.")));
    }

    @Override
    public List<GetAccountByCustomerIdResponse> getAccountByCustomerId(int id) {
        return AccountMapper.INSTANCE.accountByCustomerIdToGetResponse(accountRepository.findByCustomerIdAndStatusTrue(id));
    }

    @Override
    public List<GetAllAccountsResponse> getAllAccounts() {
        return AccountMapper.INSTANCE.accountsToGetResponse(accountRepository.findByStatusTrue());
    }
}
