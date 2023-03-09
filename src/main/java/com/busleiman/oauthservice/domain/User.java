package com.busleiman.oauthservice.domain;

import lombok.*;

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

    private String name;

    private String lastName;

    private String email;

    private Integer intents;

    private boolean isEnabled;

    private List<Role> roles;
}
