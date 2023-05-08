package com.yuhan.board.service;

import org.springframework.http.ResponseEntity;

import com.yuhan.board.dto.request.auth.SignInRequestDto;
import com.yuhan.board.dto.request.auth.SignUpRequetDto;
import com.yuhan.board.dto.response.ResponseDto;
import com.yuhan.board.dto.response.auth.SignInResponseDto;


public interface AuthService {
    
    public ResponseEntity<ResponseDto> signUp(SignUpRequetDto dto);
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

}
