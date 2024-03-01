package org.charaf.customerservice.repository;

import org.assertj.core.api.AssertionsForClassTypes;
import org.charaf.customerservice.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class CustomerRepositoryTest {
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
    public void shouldFindCustomerByEmail(){

        //Prepare

        //Execute
        Optional<Customer> customer = customerRepository.findByEmail("charaf@gmail.com");
        //Check
        assertEquals("charaf@gmail.com",customer.get().getEmail());
        //or
        AssertionsForClassTypes.assertThat(customer).isPresent();
    }

    @Test
    public void shouldNotFindCustomerByEmail(){

        //Prepare

        //Execute
        Optional<Customer> customer = customerRepository.findByEmail("karim@gmail.com");
        //Check
        assertFalse(customer.isPresent());
        //or
        AssertionsForClassTypes.assertThat(customer).isEmpty();

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