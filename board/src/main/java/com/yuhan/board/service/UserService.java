package com.yuhan.board.service;

import org.springframework.http.ResponseEntity;

import com.yuhan.board.dto.request.User.PostUserRequestDto;
import com.yuhan.board.dto.response.ResponseDto;

public interface UserService {
    public ResponseEntity<ResponseDto> postUser(PostUserRequestDto dto);
}
