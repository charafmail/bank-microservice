package org.charaf.customerservice.service;

import org.assertj.core.api.AssertionsForClassTypes;
import org.charaf.customerservice.dto.CustomerDTO;
import org.charaf.customerservice.entities.Customer;
import org.charaf.customerservice.exceptions.CustomerNotFoundException;
import org.charaf.customerservice.exceptions.EmailAlreadyExistException;
import org.charaf.customerservice.mapper.CustomerMapper;
import org.charaf.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerServiceImpl underTest;
    @Test
    void shouldSaveNewCustomer() {
        //Prepare
        CustomerDTO customerDTO= CustomerDTO.builder()
                .firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Customer customer= Customer.builder()
                .firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Customer savedCustomer= Customer.builder()
                .id(1L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        CustomerDTO expected= CustomerDTO.builder()
                .id(1L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Mockito.when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(Optional.empty());
        Mockito.when(customerMapper.fromCustomerDTO(customerDTO)).thenReturn(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(savedCustomer);
        Mockito.when(customerMapper.fromCustomer(savedCustomer)).thenReturn(expected);

        //Execute
        CustomerDTO result = underTest.saveNewCustomer(customerDTO);

        //Check
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldNotSaveNewCustomerWhenEmailExist() {
        //Prepare
        CustomerDTO customerDTO= CustomerDTO.builder()
                .firstName("Ismail").lastName("Matar").email("xxxxx@gmail.com").build();
        Customer customer= Customer.builder()
                .id(5L).firstName("Ismail").lastName("Matar").email("xxxxx@gmail.com").build();
        Mockito.when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(Optional.of(customer));
        //Execute and Check
        AssertionsForClassTypes.assertThatThrownBy(()->underTest.saveNewCustomer(customerDTO))
                .isInstanceOf(EmailAlreadyExistException.class);
    }

    @Test
    void shouldGetAllCustomers() {
        //Prepare
        List<Customer> customers = List.of(
                Customer.builder().firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build(),
                Customer.builder().firstName("Ahmed").lastName("Yassine").email("ahmed@gmail.com").build()
        );
        List<CustomerDTO> expected = List.of(
                CustomerDTO.builder().firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build(),
                CustomerDTO.builder().firstName("Ahmed").lastName("Yassine").email("ahmed@gmail.com").build()
        );
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        Mockito.when(customerMapper.fromListCustomers(customers)).thenReturn(expected);
        //Execute
        List<CustomerDTO> result = underTest.getAllCustomers();
        //Check
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldFindCustomerById() {
        //Prepare
        Long customerId = 1L;
        Customer customer=Customer.builder().id(1L).firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build();
        CustomerDTO expected=CustomerDTO.builder().id(1L).firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build();
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Mockito.when(customerMapper.fromCustomer(customer)).thenReturn(expected);
        //Execute
        CustomerDTO result = underTest.findCustomerById(customerId);
        //Check
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }
    @Test
    void shouldNotFindCustomerById() {
        //Prepare
        Long customerId = 8L;
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        //Execute and Check
        AssertionsForClassTypes.assertThatThrownBy(()->underTest.findCustomerById(customerId))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage(null);
    }

    @Test
    void shouldSearchCustomers() {
        //Prepare
        String keyword="m";
        List<Customer> customers = List.of(
                Customer.builder().firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build(),
                Customer.builder().firstName("Ahmed").lastName("Yassine").email("ahmed@gmail.com").build()
        );
        List<CustomerDTO> expected = List.of(
                CustomerDTO.builder().firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build(),
                CustomerDTO.builder().firstName("Ahmed").lastName("Yassine").email("ahmed@gmail.com").build()
        );
        Mockito.when(customerRepository.findByFirstNameContainingIgnoreCase(keyword)).thenReturn(customers);
        Mockito.when(customerMapper.fromListCustomers(customers)).thenReturn(expected);

        //Execute
        List<CustomerDTO> result = underTest.searchCustomers(keyword);

        //Check
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void updateCustomer() {
        //Prepare
        Long customerId= 6L;
        CustomerDTO customerDTO= CustomerDTO.builder()
                .id(6L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Customer customer= Customer.builder()
                .id(6L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Customer updatedCustomer= Customer.builder()
                .id(6L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        CustomerDTO expected= CustomerDTO.builder()
                .id(6L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Mockito.when(customerMapper.fromCustomerDTO(customerDTO)).thenReturn(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(updatedCustomer);
        Mockito.when(customerMapper.fromCustomer(updatedCustomer)).thenReturn(expected);

        //Execute
        CustomerDTO result = underTest.updateCustomer(customerId,customerDTO);

        //Check
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldDeleteCustomer() {
        //Prepare
        Long customerId =1L;
        Customer customer= Customer.builder()
                .id(6L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        //Execute
        underTest.deleteCustomer(customerId);

        //Check
        Mockito.verify(customerRepository).deleteById(customerId);
    }
    @Test
    void shouldNotDeleteCustomerIfNotExist() {
        //Prepare
        Long customerId =9L;
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        //Execute and Check
        AssertionsForClassTypes.assertThatThrownBy(()->underTest.deleteCustomer(customerId))
                .isInstanceOf(CustomerNotFoundException.class);
    }
}