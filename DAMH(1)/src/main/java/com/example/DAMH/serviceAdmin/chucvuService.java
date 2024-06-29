package com.example.DAMH.serviceAdmin;


import com.example.DAMH.model.CHUCVU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DAMH.repositoryAdmin.chucvuRepository;

import java.util.List;

@Service
public class chucvuService {
    @Autowired
    private chucvuRepository cvRepository;

    public List<CHUCVU>GetallChucvu()
    {
        return cvRepository.findAll();
    }
}
