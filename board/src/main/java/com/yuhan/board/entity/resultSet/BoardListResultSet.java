package com.yuhan.board.entity.resultSet;

public interface BoardListResultSet {
    
    public int getBoardNumber();
    public String getBoardTitle();
    public String getBoardContent();
    public String getBoardimageUrl();
    public String getBoardWrtieDatetime();
    public int getViewCount();
    public String getBoardWriterEmail();
    public String getBoardWriterNickname();
    public String getBoardWriterProfileImageUrl();
    public int getCommentCount();
    public int getLikeCount();
    
    
}
