package org.charaf.cutomerservice.web;

import org.charaf.customerservice.config.GlobalConfig;
import org.charaf.cutomerservice.entities.Customer;
import org.charaf.cutomerservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {

private CustomerRepository customerRepository;
    private GlobalConfig globalConfig;
    public CustomerRestController(CustomerRepository customerRepository, GlobalConfig globalConfig) {
        this.customerRepository = customerRepository;
        this.globalConfig = globalConfig;
    }
    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerRepository.findById(id).orElseThrow();
    }

    @GetMapping("/testConfig")
    public List<Integer> getCustomerConfig(){
        return List.of(globalConfig.getX(),globalConfig.getY());
    }

}
