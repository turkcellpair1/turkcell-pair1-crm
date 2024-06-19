package com.turkcell.customerservice.webApi.controllers;

import com.turkcell.customerservice.business.abstracts.SearchService;
import com.turkcell.customerservice.business.dto.requests.search.SearchCustomerRequest;
import com.turkcell.customerservice.business.dto.responses.search.SearchCustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/searchs")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @PostMapping()
    public List<SearchCustomerResponse> searchCustomer(@RequestBody @Valid SearchCustomerRequest request){
        return searchService.searchCustomer(request);
    }
}
