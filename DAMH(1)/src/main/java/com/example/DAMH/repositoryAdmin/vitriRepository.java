package com.example.DAMH.repositoryAdmin;

import com.example.DAMH.model.VITRI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface vitriRepository extends JpaRepository<VITRI, Integer> {
}
