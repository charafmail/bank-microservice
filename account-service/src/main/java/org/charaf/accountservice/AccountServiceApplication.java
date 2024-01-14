package org.charaf.accountservice;

import org.charaf.accountservice.client.CustomerRestClient;
import org.charaf.accountservice.entities.BankAccount;
import org.charaf.accountservice.enums.AccountType;
import org.charaf.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        return args -> {

            customerRestClient.getAllCustomer().stream().forEach(e -> {
                List<BankAccount> bankAccounts = List.of(
                        BankAccount.builder()
                                .accountId(String.valueOf(UUID.randomUUID()))
                                .balance((double) 500).currency("DH")
                                .type(AccountType.CURRENT_ACCOUNT)
                                .createdAt(LocalDate.now())
                                .customerId(e.getId())
                                .customer(customerRestClient.findCustomerById(e.getId()))
                                .build(),
                        BankAccount.builder()
                                .accountId(String.valueOf(UUID.randomUUID()))
                                .balance((double) 10).currency("DH")
                                .type(AccountType.SAVING_ACCOUNT)
                                .createdAt(LocalDate.now())
                                .customerId(e.getId())
                                .customer(customerRestClient.findCustomerById(e.getId()))
                                .build());
                bankAccountRepository.saveAll(bankAccounts);

            });
        };

    }
}
