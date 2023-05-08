package com.yuhan.board.dto.request.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

// 실제 프로젝트 시 팀끼리 네임 규칙
public class PostUserRequestDto {
    @NotBlank //공백x 
    @Email  // 이메일형식
    private String userEmail;
    @NotBlank
    // 닉네임 중복 / 이메일 중복은 서비스로 거처서 검증해야한다. => 컨트롤러에서 할 수 없다.
    // 데이터베이스에서 확인을 해야 하기때문
    private String userNickname;
    @NotBlank
    private String userPassword;
    @NotBlank 
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$") // 정규식이 들어가야함
    private String userPhoneNumber; // 전화번호 형식이어야 하며 사용중이 전화번호가 아니어야 한다.
    @NotBlank
    private String userAddress;
    private String userProfileImageUrl;
    
}
