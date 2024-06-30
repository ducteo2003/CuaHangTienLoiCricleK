package com.example.DAMH.repositoryAdmin;

import com.example.DAMH.model.HINHTHUC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface hinhthucRepository extends JpaRepository<HINHTHUC, Integer> {
}
