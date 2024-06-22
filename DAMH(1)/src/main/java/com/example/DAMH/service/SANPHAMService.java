package com.example.DAMH.service;

import com.example.DAMH.model.*;
import com.example.DAMH.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SANPHAMService {
    @Autowired
    private KHUYENMAIRepository khuyenMaiRepository;

    @Autowired
    private VITRIRepository viTriRepository;

    @Autowired
    private LOAISPRepository loaiSPRepository;

    @Autowired
    private SANPHAMRepository sanPhamRepository;

    @Autowired
    private HINHTHUCRepository hinhThucRepository;

    @Autowired
    private NHACUNGCAPRepository nhaCungCapRepository;

    public List<SANPHAM> getAllSanPham(){
        return sanPhamRepository.findAll();
    }

    public List<LOAISP> getAllLoaiSP(){
        return loaiSPRepository.findAll();
    }

    public List<VITRI> getAllViTri(){
        return viTriRepository.findAll();
    }

    public List<HINHTHUC> getAllHinhThuc(){
        return hinhThucRepository.findAll();
    }

    public List<KHUYENMAI> getAllKhuyenMai(){
        return khuyenMaiRepository.findAll();

    }

    public List<NHACUNGCAP> getAllNhaCungCap(){
        return nhaCungCapRepository.findAll();
    }

    public Optional<SANPHAM> getSanPhamById(Integer id){
        return sanPhamRepository.findById(id);
    }
    public List<SANPHAM> findSANPHAMByLOAISP(Integer maLoai) {
        return sanPhamRepository.findByLoaispMaLoai(maLoai);
    }

    public List<SANPHAM> searchSanPhamByTenSP(String tenSP) {
        return sanPhamRepository.findByTenSPContaining(tenSP);
    }

    public List<SANPHAM> searchSanPhamByBarcode(Integer barcode) {
        return sanPhamRepository.findByBarcode(barcode);
    }

}
