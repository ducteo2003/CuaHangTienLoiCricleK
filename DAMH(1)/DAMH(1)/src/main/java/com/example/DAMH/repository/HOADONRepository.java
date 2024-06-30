package com.example.DAMH.repository;

import com.example.DAMH.model.HOADON;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HOADONRepository extends JpaRepository<HOADON,Integer> {
    Optional<HOADON> findByTxnRef(String txnRef);
}
