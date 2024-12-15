package com.hk.learningspringboot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UserDTO {
    private int userId;
    private String username;
    private String userEmail;
    private String userPhoneNo;
    private String userAvater;
    private String role;

    public UserDTO(int userId, String username, String userEmail, String userPhoneNo, String userAvater, String role) {
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.userPhoneNo = userPhoneNo;
        this.userAvater = userAvater;
        this.role = role;
    }
}
