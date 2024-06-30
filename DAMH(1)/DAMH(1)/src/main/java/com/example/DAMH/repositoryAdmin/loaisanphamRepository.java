package com.example.DAMH.repositoryAdmin;

import com.example.DAMH.model.LOAISP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface loaisanphamRepository extends JpaRepository<LOAISP, Integer> {
}
