package org.charaf.accountservice.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
