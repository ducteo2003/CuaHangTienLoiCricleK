package com.example.DAMH.Service;

import com.example.DAMH.model.CHITIETDATHANG;
import com.example.DAMH.repository.CHITIETDATHANGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CHITIETDATHANGService {

    @Autowired
    private CHITIETDATHANGRepository chitietdathangRepository;

    public List<CHITIETDATHANG> getAllChiTietDatHang() {
        return chitietdathangRepository.findAll();
    }
}