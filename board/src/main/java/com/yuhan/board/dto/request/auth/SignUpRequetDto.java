package com.yuhan.board.dto.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class SignUpRequetDto {
    @NotBlank  
    @Email  
    private String userEmail;
    @NotBlank
  
    private String userNickname;
    @NotBlank
    private String userPassword;
    @NotBlank 
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$") // 
    private String userPhoneNumber; 
    @NotBlank
    private String userAddress;
    private String userProfileImageUrl;
    
}
