package com.example.DAMH.repository;

import com.example.DAMH.model.SANPHAM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SANPHAMRepository extends JpaRepository<SANPHAM, Integer> {
    Optional<SANPHAM> findByBarcode(int barcode);
}