package com.yuhan.board.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yuhan.board.common.util.CustomResponse;
import com.yuhan.board.dto.request.auth.SignInRequestDto;
import com.yuhan.board.dto.request.auth.SignUpRequetDto;
import com.yuhan.board.dto.response.ResponseDto;
import com.yuhan.board.dto.response.auth.SignInResponseDto;
import com.yuhan.board.entity.UserEntity;
import com.yuhan.board.provider.JwtProvider;
import com.yuhan.board.repository.UserRepository;
import com.yuhan.board.service.AuthService;

@Service
public class AuthServiceImplement implements AuthService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired // userRepository 만 IoC 해줌
    public AuthServiceImplement(
        UserRepository userRepository,
        JwtProvider jwtProvider
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtProvider = jwtProvider;
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequetDto dto) {
        String email = dto.getUserEmail();
        String nickname = dto.getUserNickname();
        String phoneNumber = dto.getUserPhoneNumber();
        String password = dto.getUserPassword();
        try {
            // TODO 존재하는 유저 이메일
            boolean existedUserEmail = userRepository.existsByEmail(email);
            if (existedUserEmail)
                return CustomResponse.existUserEmail();

            // TODO 존재하는 유저 닉네임
            boolean existedUserNickname = userRepository.existsByNickname(nickname);
            if (existedUserNickname)
                return CustomResponse.existUserNickname();

            // TODO 존재하는 유저 휴대전화 번호
            boolean existedUserPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
            if (existedUserPhoneNumber)
                return CustomResponse.existUserPhoneNumber();

            String encodedPassword = passwordEncoder.encode(password); // 유저 계정 생성 및 암호화 작업
            dto.setUserPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();

        }
        return CustomResponse.successs();

    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        SignInResponseDto body = null;
        String email = dto.getUserEmail();
        String password = dto.getUserPassword();

        try {
            // TODO 로그인 실패 (이메일 X)
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return CustomResponse.signInFailed();
            // TODO 로그인 실패 (패스워드 x)
            String encordedPassword = userEntity.getPassword();
            boolean equaledPassword = 
            passwordEncoder.matches(password, encordedPassword);// 사용자가 입력한 password , 암호화된password
            if (!equaledPassword) return CustomResponse.signInFailed();
            String jwt = jwtProvider.create(email);

            body = new SignInResponseDto(jwt);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

}
