package com.esi.auth.user.dto;

import jakarta.persistence.Id;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Id
    private String name;
    private String password;
}
