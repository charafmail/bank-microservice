package org.charaf.customerservice.repository;

import org.charaf.customerservice.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryWithContainerTest {

    @Container
    @ServiceConnection
    private static PostgreSQLContainer postgreSQLContainer=new PostgreSQLContainer("postgres:16");

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp(){
        List<Customer> customers = List.of(
                Customer.builder()
                        .firstName("charaf")
                        .lastName("benichou")
                        .email("charaf@gmail.com")
                        .build(),
                Customer.builder()
                        .firstName("samir")
                        .lastName("omari")
                        .email("samir@gmail.com")
                        .build()
        );

        customerRepository.saveAll(customers);
    }

    @Test
    public void connectionEstablishedTest(){
        assertTrue(postgreSQLContainer.isCreated());
        assertTrue(postgreSQLContainer.isCreated());
    }

    @Test
    public void shouldFindCustomersByFirstName(){

        //Prepare
        List<Customer> customer =List.of(Customer.builder()
                .firstName("charaf")
                .lastName("benichou")
                .email("charaf@gmail.com")
                .build());
        //Execute
        List<Customer> result = customerRepository.findByFirstNameContainingIgnoreCase("ch");
        //Check
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(customer);
    }



}
