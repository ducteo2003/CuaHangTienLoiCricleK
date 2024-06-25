package com.example.DAMH.service;

import com.example.DAMH.model.KHO;
import com.example.DAMH.repository.KHORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KHOService {

    @Autowired
    private KHORepository khoRepository;

    public List<KHO> getAllKhos() {
        return khoRepository.findAll();
    }
}
