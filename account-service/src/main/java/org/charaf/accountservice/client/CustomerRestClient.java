package org.charaf.accountservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.charaf.accountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/api/v1/customers/{id}")
    @CircuitBreaker(name="customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);


    @GetMapping("api/v1/customers")
    @CircuitBreaker(name="customerService", fallbackMethod = "getDefaultAllCustomer")
    List<Customer> getAllCustomer();


    default Customer getDefaultCustomer(Long id,Exception ex){
        //l ideal c'est verifier si ce customer existe dans la cash local et le retourner
        return Customer.builder().firstName("Not Available").lastName("Not Available").email("Not Available").id(0L).build();
    }

    default List<Customer> getDefaultAllCustomer(Exception ex) {
        //l ideal c'est verifier si ce customer existe dans la cash local et le retourner
        return List.of(
                Customer.builder().firstName("Not Available").lastName("Not Available").email("Not Available").id(1L).build(),
                Customer.builder().firstName("Not Available").lastName("Not Available").email("Not Available").id(2L).build()
        );
    }
}
