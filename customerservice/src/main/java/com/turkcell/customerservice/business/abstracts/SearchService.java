package com.turkcell.customerservice.business.abstracts;

import com.turkcell.customerservice.business.dto.requests.search.SearchCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.search.SearchCustomerResponse;

import java.util.List;

public interface SearchService {
    List<SearchCustomerResponse> searchCustomer(SearchCustomerRequest request);
}
