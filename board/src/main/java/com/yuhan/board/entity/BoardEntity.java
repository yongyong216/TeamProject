package com.yuhan.board.entity;




import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yuhan.board.dto.request.Board.PostBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Board")
@Table(name="Board")
public class BoardEntity {
    
    @Id //boardNumber가 pk 그리고 AutoIncrease가 있기에 GeneratedValue생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNumber;
    private String writerEmail;
    private String title;
    private String content;
    private String boardImageUrl;
    private String writeDatetime;
    private int viewCount;



    public BoardEntity(PostBoardRequestDto dto){
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = 
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        //boardNumber는 넣지 않을것이다 왜냐면 GeneratedValue를 걸어줘서 
        this.writerEmail = dto.getBoardWriterEmail();
        this.title = dto.getBoardTitle();
        this.content = dto.getBoardContent();
        this.boardImageUrl = dto.getBoardImageUrl();
        this.writeDatetime = writeDatetime;
        this.viewCount = 0;
    }
}
