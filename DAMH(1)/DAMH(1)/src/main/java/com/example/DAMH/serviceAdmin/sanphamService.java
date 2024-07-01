package com.example.DAMH.serviceAdmin;

import com.example.DAMH.model.SANPHAM;
import com.example.DAMH.repositoryAdmin.sanphamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class sanphamService {
    @Autowired
    private sanphamRepository SPRepository;

    public List<SANPHAM> GetallSanpham()
    {
        return SPRepository.findAll();
    }

    public List<SANPHAM> FindSanphamByName(String name)//TÌM KIẾM THEO TÊN
    {
        return SPRepository.findSanphamByName(name);
    }

    public Optional<SANPHAM> SanphamDetail(int id)//lấy ra SP được chọn
    {
        return SPRepository.findById(id);
    }
    public void AddSappham(SANPHAM sp)
    {
        int maLoai = sp.getLoaisp().getMaLoai();
        int maKM = sp.getKhuyenmai() != null ? sp.getKhuyenmai().getMaKM() : 0; // kiểm tra null cho mã khuyến mãi
        Optional<SANPHAM> sanpham = SPRepository.findSanpham(sp.getTenSP(), maLoai, maKM);

        if (sanpham.isEmpty()) { // nếu chưa tồn tại sản phẩm nào có thông tin tương tự trong database thì thêm mới
            SPRepository.save(sp);
        } else {
            UpdateSanpham(sp);
        }
    }
    public void UpdateSanpham(SANPHAM sp)
    {
        SANPHAM sanpham = SanphamDetail(sp.getBarcode()).orElseThrow();
        sanpham.setDonViTinh(sp.getDonViTinh());
        sanpham.setDonGia(sp.getDonGia());
        sanpham.setNcc(sp.getNcc());
        sanpham.setLoaisp(sp.getLoaisp());
        sanpham.setKhuyenmai(sp.getKhuyenmai());
        sanpham.setVitri(sp.getVitri());
        sanpham.setTenSP(sp.getTenSP());

        SPRepository.save(sanpham);
    }
    public void DeleteSanpham(int id)
    {
        SPRepository.delete(SPRepository.findById(id).orElseThrow());
    }
}
