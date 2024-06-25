package com.example.DAMH.repository;

import com.example.DAMH.model.KHO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KHORepository extends JpaRepository<KHO,Integer> {
    KHO findById(int id); // Đảm bảo phương thức này khớp với kiểu dữ liệu của khóa chính của bảng KHO
}
