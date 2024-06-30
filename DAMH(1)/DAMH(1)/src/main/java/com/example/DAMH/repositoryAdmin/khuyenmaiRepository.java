package com.example.DAMH.repositoryAdmin;

import com.example.DAMH.model.KHUYENMAI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface khuyenmaiRepository extends JpaRepository<KHUYENMAI, Integer> {
    @Query(value = "SELECT * FROM KHUYENMAI WHERE ngay_ket_thuc >= :dayaction AND ma_hinh_thuc IS NOT NULL", nativeQuery = true)
    List<KHUYENMAI> getallKMaction(@Param("dayaction") Date dayaction);
    @Query(value = "SELECT * FROM KHUYENMAI WHERE ma_hinh_thuc = :maHinhThuc", nativeQuery = true)
    List<KHUYENMAI> getallKMbyIdHT(@Param("maHinhThuc") int maHinhThuc);

}
