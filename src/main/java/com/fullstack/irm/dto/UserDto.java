package com.fullstack.irm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;

    @NotEmpty(message = "O campo de usuário não pode ficar vazio")
    private String userName;

    @NotEmpty
    private String fullName;

    @NotEmpty(message = "O campo de senha não pode ficar vazio")
    private String password;

	private Boolean accountNonExpired;
	
	private Boolean accountNonLocked;
	
	private Boolean credentialsNonExpired;
	
	private Boolean enabled;

}
