package com.yuhan.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yuhan.board.entity.BoardEntity;
import com.yuhan.board.entity.resultSet.BoardListResultSet;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer > {

    public BoardEntity findByBoardNumber(int boardNumber); 
    //유니크라서 하나만 반환되기때매 리스트로 지정할 필요 없음

    @Query(
        value = 
        "select "+
        "B.board_number As boardNumber," +
        "B.title AS boardTitle," +
        "B.content As boardContent," +
        "B.board_image_url AS boardImageUrl," +
        "B.write_datetime As boardWriteDatetime," +
        "B.view_count As viewCount," +
        "U.email As boardWriterEmail," +
        "U.nickname AS boardWriterNickname," +
        "U.profile_image_url AS boardWriterProfileImageUrl," +
        "count(DISTINCT C.comment_number) As commentCount,"+
        "count(DISTINCT L.user_email) AS likeCount " +
        "from Board B, Comment C, Liky L, User U " +
        "where B.board_number = C.board_number " +
        "AND B.board_number = L.board_number " +
        "AND B.writer_email = U.email " +
        "GROUP BY B.board_number " +
        "ORDER BY B.write_datetime DESC; ", 
        nativeQuery =  true
        )
    public List<BoardListResultSet> getList();

    @Query(
        value = 
        "select "+
        "B.board_number As boardNumber," +
        "B.title AS boardTitle," +
        "B.content As boardContent," +
        "B.board_image_url AS boardImageUrl," +
        "B.write_datetime As boardWriteDatetime," +
        "B.view_count As viewCount," +
        "U.email As boardWriterEmail," +
        "U.nickname AS boardWriterNickname," +
        "U.profile_image_url AS boardWriterProfileImageUrl," +
        "count(DISTINCT C.comment_number) As commentCount,"+
        "count(DISTINCT L.user_email) AS likeCount " +
        "from Board B, Comment C, Liky L, User U " +
        "where B.board_number = C.board_number " +
        "AND B.board_number = L.board_number " +
        "AND B.writer_email = U.email " +
        "GROUP BY B.board_number " +
        "ORDER BY likeCount DESC " + 
        "LIMIT 3",
        nativeQuery =  true
        )
    public List<BoardListResultSet> getTop3List();

}
