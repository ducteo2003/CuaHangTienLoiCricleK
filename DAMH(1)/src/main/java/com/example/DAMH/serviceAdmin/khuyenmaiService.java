package com.example.DAMH.serviceAdmin;

import com.example.DAMH.model.KHUYENMAI;
import com.example.DAMH.model.SANPHAM;
import com.example.DAMH.repositoryAdmin.hinhthucRepository;
import com.example.DAMH.repositoryAdmin.khuyenmaiRepository;
import com.example.DAMH.repositoryAdmin.sanphamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class khuyenmaiService {
    @Autowired
    private khuyenmaiRepository KMRepository;
    @Autowired
    private sanphamRepository SpRepository;

    public List<KHUYENMAI> Getall()
    {
        return KMRepository.findAll();
    }
    public List<KHUYENMAI> GetallAction()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date dayaction = calendar.getTime();
        return KMRepository.getallKMaction(dayaction);
    }
    public Optional<KHUYENMAI> findbyId(int id)
    {
        return KMRepository.findById(id);
    }
    public void AddKhuyenmai(KHUYENMAI km)
    {
         KMRepository.save(km);
    }
    public void EditKhuyenmai(KHUYENMAI km)
    {
        KHUYENMAI khuyenmai = findbyId(km.getMaKM()).orElseThrow();
        khuyenmai.setHinhthuc(km.getHinhthuc());
        khuyenmai.setThoiHan(km.getThoiHan());
        khuyenmai.setNgayBatDau(km.getNgayBatDau());
        khuyenmai.setNgayKetThuc(km.getNgayKetThuc());

        KMRepository.save(khuyenmai);
    }
    public void DeleteKhuyenmai(int id)
    {
        KHUYENMAI khuyenmai = findbyId(id).orElseThrow();
        KMRepository.delete(khuyenmai);
    }
    public List<SANPHAM> getAllSPinKM(int id)
    {
        return SpRepository.findSanphamByIDKM(id);
    }
}
