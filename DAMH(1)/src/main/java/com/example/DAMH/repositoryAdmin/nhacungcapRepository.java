package com.example.DAMH.repositoryAdmin;

import com.example.DAMH.model.NHACUNGCAP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface nhacungcapRepository extends JpaRepository<NHACUNGCAP, Integer> {
}
