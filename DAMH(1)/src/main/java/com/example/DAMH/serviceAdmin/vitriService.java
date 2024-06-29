package com.example.DAMH.serviceAdmin;

import com.example.DAMH.model.VITRI;
import com.example.DAMH.repositoryAdmin.vitriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class vitriService {
    @Autowired
    private vitriRepository VTRepository;
    public List<VITRI> Getall()
    {
        return VTRepository.findAll();
    }
}
