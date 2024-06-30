package com.example.DAMH.serviceAdmin;

import com.example.DAMH.model.BINHLUAN;
import com.example.DAMH.model.HOADON;
import com.example.DAMH.repositoryAdmin.binhluanRepository;
import com.example.DAMH.repositoryAdmin.hoadonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class binhluanService {
    @Autowired
    private binhluanRepository BLRepository;
    @Autowired
    private hoadonRepository HDRepository;

    public List<BINHLUAN> getall()
    {
        return BLRepository.findAll();
    }
    public BINHLUAN findbyId(int id)
    {
        return BLRepository.findById(id).orElseThrow();
    }
    public HOADON hoadonList(int id)
    {
        return HDRepository.findById(id).orElseThrow();
    }
    public void editBL(int id)
    {
        BINHLUAN bl = findbyId(id);
        bl.setTrangThai(true);
        BLRepository.save(bl);
    }
    public List<BINHLUAN> Find(String text)
    {
        if(text.contains("Đợi duyệt"))
        {
            return BLRepository.findbymahdortrangthai(0);
        }else {
            if(text.contains("Đã duyệt"))
            {
                return BLRepository.findbymahdortrangthai(1);
            }
            else {
                int id = Integer.parseInt(text);
                return BLRepository.findbymahdortrangthai(id);
            }
        }
    }
}
