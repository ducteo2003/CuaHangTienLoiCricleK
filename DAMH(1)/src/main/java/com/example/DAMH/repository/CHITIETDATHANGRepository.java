package com.example.DAMH.repository;

import com.example.DAMH.model.CHITIETDATHANG;
import com.example.DAMH.model.SANPHAM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CHITIETDATHANGRepository extends JpaRepository <CHITIETDATHANG, Integer>{
    List<CHITIETDATHANG> findBySanpham(SANPHAM sanpham);
}
