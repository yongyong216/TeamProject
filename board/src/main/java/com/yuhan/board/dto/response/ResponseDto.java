package com.yuhan.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto { // 해당 클래스는 무조건 필수이므로 굳이 Notnull을 걸어줄 필요 없다.
    private String code;
    private String message;     
}
