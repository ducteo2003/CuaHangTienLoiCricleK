package com.example.DAMH.serviceAdmin;

import com.example.DAMH.model.NHANVIEN;
import com.example.DAMH.repositoryAdmin.nhanvienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class nhanvienService {
    @Autowired
    private nhanvienRepository nvRepository;


    public List<NHANVIEN> GetallNhanvien() // lấy danh sách nhân viên
    {
        return nvRepository.findAll();
    }
    public void AddNhanvien(NHANVIEN nhanvien) // add thêm 1 nhân viên mới
    {
        nvRepository.save(nhanvien);
    }
    public NHANVIEN findNhanvien(int id) {
        Optional<NHANVIEN> optionalNV = nvRepository.findById(id);
        return optionalNV.orElse(null);
    }
    public List<NHANVIEN> findListNhanvien(String text)
    {
        List<NHANVIEN> nv = nvRepository.FindNhanvienByText(text) ;//tiềm kiếm theo tên nv và tên chức vụ
        return nv;

    }
    public void DeleteNhanvien(int id) {
        NHANVIEN nv = findNhanvien(id);
        if (nv != null) {
            nvRepository.delete(nv);
        } else {
            throw new IllegalArgumentException("Không tìm thấy nhân viên có id = " + id);
        }
    }

    public void EditNhanvien(NHANVIEN nhanvien)//chỉnh sửa
    {
        NHANVIEN nv = findNhanvien(nhanvien.getMaNV());
        nv.setTenNV(nhanvien.getTenNV());
        nv.setEmail(nhanvien.getEmail());
        nv.setTenDangNhap(nhanvien.getTenDangNhap());
        nv.setChucvu(nhanvien.getChucvu());
        nv.setGioiTinh(nhanvien.isGioiTinh());
        /*nv.setMatKhau(nhanvien.getMatKhau());*/
        nv.setNamSinh(nhanvien.getNamSinh());
        nvRepository.save(nv);
    }
}
