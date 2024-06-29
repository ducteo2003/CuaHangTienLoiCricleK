package com.example.DAMH.Service;

import com.example.DAMH.model.CHITIETDATHANG;
import com.example.DAMH.repository.CHITIETDATHANGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CHITIETDATHANGService {

    @Autowired
    private CHITIETDATHANGRepository chitietdathangRepository;

    public List<CHITIETDATHANG> getAllChiTietDatHang() {
        return chitietdathangRepository.findAll();
    }
    public List<CHITIETDATHANG> getChiTietDatHangByDateRange(LocalDate startDate, LocalDate endDate) {
        return chitietdathangRepository.findByNgayGiaoDuKienBetween(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
    }
}