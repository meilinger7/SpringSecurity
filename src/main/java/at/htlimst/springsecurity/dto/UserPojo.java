package at.htlimst.springsecurity.dto;

import at.htlimst.springsecurity.model.Role;

import java.util.Collection;

import lombok.*;


@Builder
@Getter
public class UserPojo {

    private String username;
    private String password;
    private Collection<Role> roles;

}
