package com.busleiman.oauthservice.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {


    private Long id;
    private String username;
    private String password;
    private String identificationNr;
    private LocalDate birthDate;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String areaPhoneNumber;
    private boolean isEnabled;
    private Integer intents;
    private List<Role> roles;
}
