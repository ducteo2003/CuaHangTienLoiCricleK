package com.example.DAMH.repository;

import com.example.DAMH.model.CHITIETHOADON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface CHITIETHOADONRepository extends JpaRepository<CHITIETHOADON, Integer> {

    @Query("SELECT FUNCTION('YEAR', d.hoadon.ngayLap), FUNCTION('WEEK', d.hoadon.ngayLap), SUM(d.soLuongMua), SUM(d.soLuongMua * d.giaMua) " +
            "FROM CHITIETHOADON d " +
            "GROUP BY FUNCTION('YEAR', d.hoadon.ngayLap), FUNCTION('WEEK', d.hoadon.ngayLap)")
    List<Object[]> findWeeklySales();

    @Query("SELECT FUNCTION('YEAR', d.hoadon.ngayLap), FUNCTION('MONTH', d.hoadon.ngayLap), SUM(d.soLuongMua), SUM(d.soLuongMua * d.giaMua) " +
            "FROM CHITIETHOADON d " +
            "GROUP BY FUNCTION('YEAR', d.hoadon.ngayLap), FUNCTION('MONTH', d.hoadon.ngayLap)")
    List<Object[]> findMonthlySales();
}
