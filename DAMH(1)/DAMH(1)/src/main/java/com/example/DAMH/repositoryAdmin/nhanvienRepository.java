package com.example.DAMH.repositoryAdmin;

import com.example.DAMH.model.NHANVIEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface nhanvienRepository extends JpaRepository<NHANVIEN, Integer> {
    @Query("SELECT nv FROM NHANVIEN nv WHERE " +
            "nv.tenNV LIKE %:text% OR " +
            "EXISTS (SELECT 1 FROM nv.roles r WHERE r.tenChucVu LIKE %:text%) OR " +
            "(CASE WHEN nv.gioiTinh = true THEN 'Nam' ELSE 'Nữ' END) LIKE %:text% OR " +
            "CAST(nv.namSinh AS string) LIKE %:text%")
    List<NHANVIEN> FindNhanvienByText(@Param("text") String text);

}
