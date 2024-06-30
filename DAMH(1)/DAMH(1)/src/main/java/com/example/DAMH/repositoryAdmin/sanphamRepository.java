package com.example.DAMH.repositoryAdmin;

import com.example.DAMH.model.SANPHAM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface sanphamRepository extends JpaRepository<SANPHAM, Integer> {
    @Query(value = "SELECT * FROM SANPHAM WHERE tenSP LIKE %:tenSP% OR barcode LIKE %:tenSP%", nativeQuery = true)
    List<SANPHAM> findSanphamByName(@Param("tenSP") String tenSP);

    @Query(value = "SELECT * FROM SANPHAM WHERE tenSP = :tenSP AND ma_Loai = :maLoai AND maKM = :maKM", nativeQuery = true)
    Optional<SANPHAM> findSanpham(@Param("tenSP") String tenSP, @Param("maLoai") int maLoai, @Param("maKM") int maKM);

    @Query(value = "SELECT * FROM SANPHAM WHERE mancc = :NCC", nativeQuery = true)
    List<SANPHAM> findSanphamByIDNCC( @Param("NCC") int NCC);
    @Query(value = "SELECT * FROM SANPHAM WHERE maKM = :maKM", nativeQuery = true)
    List<SANPHAM> findSanphamByIDKM( @Param("maKM") int maKM);

    @Query(value = "SELECT * FROM SANPHAM WHERE ma_loai = :maLoai", nativeQuery = true)
    List<SANPHAM> findSanphamByIDLoai( @Param("maLoai") int maLoai);
}
