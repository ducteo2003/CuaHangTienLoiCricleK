package com.example.DAMH.repository;

import com.example.DAMH.model.CHITIETDATHANG;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CHITIETDATHANGRepository extends JpaRepository <CHITIETDATHANG, Integer>{
    List<CHITIETDATHANG> findByNgayGiaoDuKienBetween(Date startDate, Date endDate);
}
