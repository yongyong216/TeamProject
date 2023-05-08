package com.yuhan.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.yuhan.board.dto.response.ResponseDto;
import com.yuhan.board.entity.resultSet.BoardListResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetBoardListResponseDto extends ResponseDto{
    private List<BoardSummary> boardList;

    public GetBoardListResponseDto(List<BoardListResultSet> resultSet){
        super("SU","Success");

        List<BoardSummary> boardList = new ArrayList<>();
        
        for(BoardListResultSet result : resultSet) {
            BoardSummary boardSummary = new BoardSummary(result);
            boardList.add(boardSummary);
        }
        this.boardList = boardList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BoardSummary {
   
    private Integer boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardImageUrl;
    private String boardWriteDatetime;
    private int viewCount;
    private String boardWriterEmail;
    private String boardWriterprofileImageUrl;
    private int commentCount;
    private int likeCount;

    public BoardSummary(BoardListResultSet resultSet){
    this.boardNumber = resultSet.getBoardNumber();
    this.boardTitle =resultSet.getBoardTitle();
    this.boardContent=resultSet.getBoardContent();
    this.boardImageUrl=resultSet.getBoardimageUrl();
    this.boardWriteDatetime = resultSet.getBoardWrtieDatetime();
    this.viewCount = resultSet.getViewCount();
    this.boardWriterEmail = resultSet.getBoardWriterEmail();
    this.boardWriterprofileImageUrl = resultSet.getBoardWriterProfileImageUrl();
    this.commentCount = resultSet.getCommentCount();
    this.likeCount = resultSet.getLikeCount();

    }

}
