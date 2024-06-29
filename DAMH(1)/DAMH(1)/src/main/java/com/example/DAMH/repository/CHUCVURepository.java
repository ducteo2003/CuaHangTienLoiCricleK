package com.example.DAMH.repository;

import com.example.DAMH.model.CHUCVU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CHUCVURepository extends JpaRepository<CHUCVU, Long> {
    CHUCVU findRoleByTenChucVu(String tenChucVu);
}
