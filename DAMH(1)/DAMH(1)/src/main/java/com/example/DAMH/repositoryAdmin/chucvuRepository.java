package com.example.DAMH.repositoryAdmin;

import com.example.DAMH.model.CHUCVU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface chucvuRepository extends JpaRepository<CHUCVU, Integer> {
}
