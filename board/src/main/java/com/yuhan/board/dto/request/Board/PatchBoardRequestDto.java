package com.yuhan.board.dto.request.Board;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchBoardRequestDto {
    @Email
    @NotBlank
    private String userEmail;
    @NotNull //int 는 기본형 타입이라서 null이란 값을 받을 수 없다.
    // 그래서 Integer를 사용한다.
    private Integer boardNumber;  // 필수값으로 받기위해 Integer로  
    @NotBlank
    private String boardTitle;
    @NotBlank
    private String boardContent;
    private String boardImageUrl;
}