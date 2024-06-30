package com.example.DAMH.repository;


import com.example.DAMH.model.NHANVIEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface INHANVIENRepository extends JpaRepository<NHANVIEN, String> {
    Optional<NHANVIEN> findByTenDangNhap(String tenDangNhap);

}
