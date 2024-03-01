package org.charaf.customerservice.mapper;

import org.assertj.core.api.AssertionsForClassTypes;
import org.charaf.customerservice.dto.CustomerDTO;
import org.charaf.customerservice.entities.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

class CustomerMapperTest {
    private CustomerMapper customerMapper = new CustomerMapper();

    @Test
    void shouldMapCustomerToCustomerDTO() {
        //Prepare
        Customer givenCustomer = Customer.builder()
                .id(1L).firstName("Mohamed").lastName("Youssfi").email("med@gmail.com")
                .build();
        CustomerDTO expected = CustomerDTO.builder()
                .id(1L).firstName("Mohamed").lastName("Youssfi").email("med@gmail.com")
                .build();
        //Execute
        CustomerDTO result = customerMapper.fromCustomer(givenCustomer);
        //Check
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldMapCustomerDTOtoCustomer() {
        //Prepare
        CustomerDTO givenCustomerDTO = CustomerDTO.builder()
                .id(1L).firstName("Mohamed").lastName("Youssfi").email("med@gmail.com")
                .build();
        Customer expected = Customer.builder()
                .id(1L).firstName("Mohamed").lastName("Youssfi").email("med@gmail.com")
                .build();
        //Execute
        Customer result = customerMapper.fromCustomerDTO(givenCustomerDTO);
        //Check
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldMapListOfCustomersToListCustomerDTOs() {
        //Prepare
        List<Customer> givenCustomers=List.of(
                Customer.builder().id(1L).firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build() ,
                Customer.builder().id(2L).firstName("Imane").lastName("Ibrahimi").email("ibrahimi@gmail.com").build()
        );
        List<CustomerDTO> expected=List.of(
                CustomerDTO.builder().id(1L).firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build() ,
                CustomerDTO.builder().id(2L).firstName("Imane").lastName("Ibrahimi").email("ibrahimi@gmail.com").build()
        );
        //Execute
        List<CustomerDTO> result = customerMapper.fromListCustomers(givenCustomers);
        //Check
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }
    @Test
    void shouldNotMapNullCustomerToCustomerDTO() {
        AssertionsForClassTypes.assertThatThrownBy(
                ()->customerMapper.fromCustomer(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}