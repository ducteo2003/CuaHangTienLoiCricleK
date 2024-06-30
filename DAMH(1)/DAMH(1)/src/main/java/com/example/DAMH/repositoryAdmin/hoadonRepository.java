package com.example.DAMH.repositoryAdmin;

import com.example.DAMH.model.HOADON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface hoadonRepository extends JpaRepository<HOADON, Integer> {
}
