package com.example.DAMH.Service;

import com.example.DAMH.model.*;
import com.example.DAMH.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private SANPHAMRepository sanphamRepository;

    @Autowired
    private DONDATHANGRepository dondathangRepository;

    @Autowired
    private CHITIETDATHANGRepository chitietdathangRepository;

    @Autowired
    private KHORepository khoRepository;

    @Transactional
    public void placeOrder(List<SANPHAM> sanphams, List<Integer> soLuongs, String diaChi, int maKho) {
        KHO kho = khoRepository.findById(maKho).orElseThrow(() -> new RuntimeException("Kho không tồn tại"));

        DONDATHANG dondathang = new DONDATHANG();
        dondathang.setNgayTao(new Date());
        dondathang.setGhiChu("Đơn đặt hàng mới");
        dondathang = dondathangRepository.save(dondathang);

        for (int i = 0; i < sanphams.size(); i++) {
            SANPHAM sanpham = sanphams.get(i);
            int soLuong = soLuongs.get(i);

            CHITIETDATHANG chitietdathang = new CHITIETDATHANG();
            chitietdathang.setSanpham(sanpham);
            chitietdathang.setSoLuongDat(soLuong);
            chitietdathang.setDondathang(dondathang);

            LocalDate ngayGiaoDuKien = LocalDate.now().plusDays(3);
            chitietdathang.setNgayGiaoDuKien(Date.from(ngayGiaoDuKien.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            chitietdathang.setDiaChi(diaChi);
            chitietdathang.setGiaDat(sanpham.getDonGia());
            chitietdathang.setTongDat(sanpham.getDonGia() * soLuong);

            chitietdathang = chitietdathangRepository.save(chitietdathang);

            // Cập nhật thông tin tồn kho trực tiếp
            kho.setSoLuongTon(kho.getSoLuongTon() + soLuong);
            khoRepository.save(kho);
        }

        dondathangRepository.save(dondathang);
    }
}

//@Service
//public class OrderService {
//
//    @Autowired
//    private SANPHAMRepository sanphamRepository;
//
//    @Autowired
//    private DONDATHANGRepository dondathangRepository;
//
//    @Autowired
//    private CHITIETDATHANGRepository chitietdathangRepository;
//
//    @Autowired
//    private PHIEULUUKHORepository phieuluukhoRepository;
//
//    @Autowired
//    private LUUKHORepository luukhoRepository;
//
//    @Autowired
//    private KHORepository khoRepository;
//
//    @Transactional
//    public void placeOrder(List<SANPHAM> sanphams, List<Integer> soLuongs, String diaChi, int maKho) {
//        KHO kho = khoRepository.findById(maKho).orElseThrow(() -> new RuntimeException("Kho không tồn tại"));
//
//        DONDATHANG dondathang = new DONDATHANG();
//        dondathang.setNgayTao(new Date());
//        dondathang.setGhiChu("Đơn đặt hàng mới");
//        dondathang = dondathangRepository.save(dondathang);
//
//        for (int i = 0; i < sanphams.size(); i++) {
//            SANPHAM sanpham = sanphams.get(i);
//            int soLuong = soLuongs.get(i);
//
//            CHITIETDATHANG chitietdathang = new CHITIETDATHANG();
//            chitietdathang.setSanpham(sanpham);
//            chitietdathang.setSoLuongDat(soLuong);
//            chitietdathang.setDondathang(dondathang);
//
//            LocalDate ngayGiaoDuKien = LocalDate.now().plusDays(3);
//            chitietdathang.setNgayGiaoDuKien(Date.from(ngayGiaoDuKien.atStartOfDay(ZoneId.systemDefault()).toInstant()));
//            chitietdathang.setDiaChi(diaChi);
//            chitietdathang.setGiaDat(sanpham.getDonGia());
//            chitietdathang.setTongDat(sanpham.getDonGia() * soLuong);
//
//            chitietdathang = chitietdathangRepository.save(chitietdathang);
//
//            PHIEULUUKHO phieuluukho;
//            if (phieuluukhoRepository.existsByChitietdathangAndDondathang(chitietdathang, dondathang)) {
//                // Nếu PHIEULUUKHO đã tồn tại, cập nhật thông tin nếu cần
//                phieuluukho = phieuluukhoRepository.findByChitietdathangAndDondathang(chitietdathang, dondathang);
//                phieuluukho.setNgayTaoPhieu(new Date());
//            } else {
//                // Nếu PHIEULUUKHO chưa tồn tại, tạo mới
//                phieuluukho = new PHIEULUUKHO();
//                phieuluukho.setNgayTaoPhieu(new Date());
//                phieuluukho.setChitietdathang(chitietdathang);
//                phieuluukho.setDondathang(dondathang);
//            }
//            phieuluukho = phieuluukhoRepository.save(phieuluukho);
//
//            LUUKHO luukho = new LUUKHO();
//            luukho.setPhieuluukho(phieuluukho);
//            luukho.setKho(kho);
//            luukhoRepository.save(luukho);
//
//            kho.setSoLuongTon(kho.getSoLuongTon() + soLuong);
//        }
//
//        dondathangRepository.save(dondathang);
//        khoRepository.save(kho);
//    }
//}
//
//
//



