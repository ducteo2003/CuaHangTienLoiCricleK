package com.example.DAMH.repository;

import com.example.DAMH.model.BINHLUAN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BINHLUANRepository extends JpaRepository<BINHLUAN, Integer> {
    @Query("SELECT b FROM BINHLUAN b WHERE b.trangThai = true")
    List<BINHLUAN> findActiveComments();
}
