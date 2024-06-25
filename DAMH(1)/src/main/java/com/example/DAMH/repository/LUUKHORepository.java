package com.example.DAMH.repository;

import com.example.DAMH.model.LUUKHO;
import com.example.DAMH.model.PHIEULUUKHO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LUUKHORepository extends JpaRepository<LUUKHO,Integer> {
    List<LUUKHO> findByPhieuluukho(PHIEULUUKHO phieuluukho);

}
