package com.example.DAMH.Service;

import com.example.DAMH.model.SANPHAM;
import com.example.DAMH.repository.SANPHAMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SANPHAMService {

    @Autowired
    private SANPHAMRepository sanphamRepository;

    public List<SANPHAM> findAll() {
        return sanphamRepository.findAll();
    }

    public Optional<SANPHAM> findById(int barcode) {
        return sanphamRepository.findById(barcode);
    }

    public SANPHAM save(SANPHAM sanpham) {
        return sanphamRepository.save(sanpham);
    }

    public void deleteById(int barcode) {
        sanphamRepository.deleteById(barcode);
    }
}