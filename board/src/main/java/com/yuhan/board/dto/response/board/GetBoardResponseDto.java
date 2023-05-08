package com.yuhan.board.dto.response.board;
import java.util.ArrayList;
import java.util.List;

import com.yuhan.board.dto.response.ResponseDto;
import com.yuhan.board.entity.BoardEntity;
import com.yuhan.board.entity.CommentEntity;
import com.yuhan.board.entity.LikyEntity;
import com.yuhan.board.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GetBoardResponseDto  extends ResponseDto{
    
    private int boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardImageUrl;
    private String boardWriteDatetime;
    private int viewCount;
    private String boardWriterEmail;
    private String boardWriterNickname;
    private String boardWriterProfileImageUrl;
    private List<Comment> commentList;
    private List<Liky> likeList;

    public GetBoardResponseDto(
        BoardEntity boardEntity, UserEntity userEntity,
        List<CommentEntity> commentEntities, List<LikyEntity> likyEntities
      ){
          super("SU","Success");
   
     
       this.boardNumber = boardEntity.getBoardNumber();
       this.boardTitle = boardEntity.getTitle();
       this.boardContent = boardEntity.getContent();
       this.boardImageUrl = boardEntity.getBoardImageUrl();
       this.boardWriteDatetime = boardEntity.getWriteDatetime();
       this.viewCount = boardEntity.getViewCount();
       this.boardWriterEmail = userEntity.getEmail();
       this.boardWriterProfileImageUrl = userEntity.getProfileImageUrl();
       this.boardWriterNickname = userEntity.getNickname();
        
    }
}
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
   
class Comment {
    private int commentNumber;
    private int boardNumber;
    private String commentWriterEmail;
    private String commentContent;
    private String commentWriterNickname;
    private String commentWriterProfileImageUrl;
    private String commentWriteDatetime;

    Comment(CommentEntity commentEntity) {
        this.commentNumber = commentEntity.getCommentNumber();
        this.boardNumber = commentEntity.getBoardNumber();
        this.commentWriterEmail = commentEntity.getUserEmail();
        this.commentContent = commentEntity.getCommentContent();
        this.commentWriterNickname = commentEntity.getUserNickname();
        this.commentWriterProfileImageUrl = commentEntity.getUserProfileImageUrl();
        this.commentWriteDatetime = commentEntity.getWriteDatetime();
    }

    static List<Comment> createList(List<CommentEntity> commentEntities){
            List<Comment> commentList = new ArrayList<>();
            for(CommentEntity commentEntity : commentEntities){
                    Comment comment = new Comment(commentEntity);
                    commentList.add(comment);
            }
            return commentList;
        }

    }
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    
class Liky{
    private int boardNumber;
    private String userEmail;
    private String userNickname;
    private String userProfileImageUrl;

    Liky(LikyEntity likyEntity) {
        this.boardNumber = likyEntity.getBoardNumber();
        this.userEmail = likyEntity.getUserEmail();
        this.userNickname = likyEntity.getUserNickname();
        this.userProfileImageUrl = likyEntity.getUserProfileImageUrl();
    }

    static List<Liky> createList(List<LikyEntity> likyEntities) {
        List<Liky> likeList = new ArrayList<>();
        for (LikyEntity likyEntity: likyEntities) {
            Liky liky = new Liky(likyEntity);
            likeList.add(liky);
        }
        return likeList;
    }
}