package com.example.DAMH.repository;

import com.example.DAMH.model.CHITIETDATHANG;
import com.example.DAMH.model.DONDATHANG;
import com.example.DAMH.model.PHIEULUUKHO;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PHIEULUUKHORepository extends JpaRepository<PHIEULUUKHO, Integer> {
    boolean existsByChitietdathangAndDondathang(CHITIETDATHANG chitietdathang, DONDATHANG dondathang);
    PHIEULUUKHO findByChitietdathangAndDondathang(CHITIETDATHANG chitietdathang, DONDATHANG dondathang);
}
