package org.charaf.customerservice.web;

import jakarta.validation.Valid;
import org.charaf.customerservice.config.GlobalConfig;
import org.charaf.customerservice.dto.CustomerDTO;
import org.charaf.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {

private CustomerService customerService;
    private GlobalConfig globalConfig;
    public CustomerRestController(CustomerService customerService, GlobalConfig globalConfig) {
        this.customerService = customerService;
        this.globalConfig = globalConfig;
    }
    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CustomerDTO> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CustomerDTO getCustomer(@PathVariable Long id){
        return customerService.findCustomerById(id);
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomers(@RequestParam String keyword){
        return customerService.searchCustomers(keyword);
    }
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        return customerService.saveNewCustomer(customerDTO);
    }
    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(id,customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

    @GetMapping("/testConfig")
    public List<Integer> getCustomerConfig(){
        return List.of(globalConfig.getX(),globalConfig.getY());
    }

    //pour verifier les parametre d'authentication specifiquement les authorities(roles)
    @GetMapping("/auth")
    public Authentication getAuthentication(Authentication authentication){
        return authentication;
    }

}
