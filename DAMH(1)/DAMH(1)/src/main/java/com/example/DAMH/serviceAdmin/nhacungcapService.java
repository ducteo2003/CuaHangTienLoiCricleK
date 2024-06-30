package com.example.DAMH.serviceAdmin;

import com.example.DAMH.model.NHACUNGCAP;
import com.example.DAMH.model.SANPHAM;
import com.example.DAMH.repositoryAdmin.nhacungcapRepository;
import com.example.DAMH.repositoryAdmin.sanphamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class nhacungcapService {
    @Autowired
    private nhacungcapRepository nccRepository;
    @Autowired
    private sanphamRepository sanPhamRepository;

    public List<NHACUNGCAP> Getall()
    {
        return nccRepository.findAll();
    }
    public Optional<NHACUNGCAP> GetbyId(int id)
    {
        return nccRepository.findById(id);
    }
    public void addNewNCC(NHACUNGCAP ncc)
    {
        nccRepository.save(ncc);
    }
    public void editNCC(NHACUNGCAP ncc)
    {
        NHACUNGCAP NCC = GetbyId(ncc.getMaNCC()).orElseThrow();
        NCC.setTenNCC(ncc.getTenNCC());
        nccRepository.save(NCC);
    }
    public void deleteNCC(int id)
    {
        List<SANPHAM> sanphams = sanPhamRepository.findSanphamByIDNCC(id);
        for (SANPHAM sanpham : sanphams) {
            sanpham.setNcc(null);  // Hoặc xử lý theo logic của bạn, ví dụ: cập nhật nhà cung cấp mới
        }
        sanPhamRepository.saveAll(sanphams);
        NHACUNGCAP NCC = GetbyId(id).orElseThrow();
        nccRepository.delete(NCC);
    }
    public List<SANPHAM> finfSpByid(int id)
    {
        List<SANPHAM> listsp = sanPhamRepository.findSanphamByIDNCC(id);
        if (listsp == null) {
            return Collections.emptyList(); // Hoặc có thể trả về null tùy vào yêu cầu logic của bạn
        }
        return listsp;
    }
}
