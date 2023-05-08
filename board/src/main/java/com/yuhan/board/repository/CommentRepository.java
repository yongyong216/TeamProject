package com.yuhan.board.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuhan.board.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer>{
    List<CommentEntity> findByBoardNumber(int boardNumber);
    @Transactional
     void deleteByBoardNumber (int boardNumber);
}
