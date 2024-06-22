package com.example.DAMH.repository;

import com.example.DAMH.model.SANPHAM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SANPHAMRepository extends JpaRepository<SANPHAM, Integer> {
    List<SANPHAM> findByLoaispMaLoai(Integer maLoai);
    List<SANPHAM> findByTenSPContaining(String tenSP);
    List<SANPHAM> findByBarcode(Integer barcode);


}
