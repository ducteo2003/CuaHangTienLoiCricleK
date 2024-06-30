package com.example.DAMH.serviceAdmin;

import com.example.DAMH.model.HINHTHUC;
import com.example.DAMH.model.KHUYENMAI;
import com.example.DAMH.repositoryAdmin.hinhthucRepository;
import com.example.DAMH.repositoryAdmin.khuyenmaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class hinhthucService {
    @Autowired
    private hinhthucRepository HTRepository;

    @Autowired
    private khuyenmaiRepository KMRepository;
    public List<HINHTHUC> Getall()
    {
        return HTRepository.findAll();
    }
    public void addHinhthuc(HINHTHUC ht)
    {
        HTRepository.save(ht);
    }
    public Optional<HINHTHUC> findByID(int id)
    {
        return HTRepository.findById(id);
    }
    public void deleteHinhthuc (int id)
    {
        HINHTHUC ht = findByID(id).orElseThrow();
        List<KHUYENMAI> km = KMRepository.getallKMbyIdHT(id);

        for(KHUYENMAI k : km)
        {
            k.setHinhthuc(null);
        }
        KMRepository.saveAll(km);
        HTRepository.delete(ht);
    }
}
