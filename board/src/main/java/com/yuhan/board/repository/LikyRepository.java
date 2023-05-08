package com.yuhan.board.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuhan.board.entity.LikyEntity;
import com.yuhan.board.entity.primaryKey.LikyPk;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity , LikyPk> {
    // 복합키 경우엔 pk를 따로 다시 만들어줘야한다 . 두개를 적을 수가 없다.
    List<LikyEntity> findByBoardNumber(int boardNumber);
    @Transactional
    void deleteByBoardNumber(int boardNumber);
}
