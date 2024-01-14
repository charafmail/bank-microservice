package org.charaf.accountservice.web;

import org.charaf.accountservice.client.CustomerRestClient;
import org.charaf.accountservice.entities.BankAccount;
import org.charaf.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;


    public AccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){
        this.bankAccountRepository=bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/v1/accounts")
    public List<BankAccount> getAccounts() {

        List<BankAccount> bankAccounts = bankAccountRepository.findAll();

        bankAccounts.forEach(e -> {
            e.setCustomer(customerRestClient.findCustomerById(e.getCustomerId()));
        });

        return bankAccounts;
    }

    @GetMapping("/v1/accounts/{id}")
    public BankAccount getAccountById(@PathVariable String id){

        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow();
        bankAccount.setCustomer(customerRestClient.findCustomerById(bankAccount.getCustomerId()));
        return bankAccount;
    }
}
