package com.example.DAMH.Service;

import com.example.DAMH.model.*;
import com.example.DAMH.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private SANPHAMRepository sanphamRepository;

    @Autowired
    private DONDATHANGRepository dondathangRepository;

    @Autowired
    private CHITIETDATHANGRepository chitietdathangRepository;

    @Autowired
    private PHIEULUUKHORepository phieuluukhoRepository;

    @Autowired
    private LUUKHORepository luukhoRepository;

    @Autowired
    private KHORepository khoRepository;

    @Transactional
    public void placeOrder(SANPHAM sanpham, int soLuong, String diaChi, int maKho) {
        // Lấy đối tượng kho từ cơ sở dữ liệu
        KHO kho = khoRepository.findById(maKho).orElseThrow(() -> new RuntimeException("Kho không tồn tại"));

        // Tạo đơn đặt hàng mới
        DONDATHANG dondathang = new DONDATHANG();
        dondathang.setNgayTao(new Date());
        dondathang.setGhiChu("Đơn đặt hàng mới");

        // Lưu đơn đặt hàng vào cơ sở dữ liệu
        dondathang = dondathangRepository.save(dondathang);

        // Tạo chi tiết đặt hàng
        CHITIETDATHANG chitietdathang = new CHITIETDATHANG();
        chitietdathang.setSanpham(sanpham);
        chitietdathang.setSoLuongDat(soLuong);
        chitietdathang.setNgayGiaoDuKien(new Date());
        chitietdathang.setDiaChi(diaChi);
        chitietdathang.setGiaDat(sanpham.getDonGia());
        chitietdathang.setTongDat(sanpham.getDonGia() * soLuong);
        chitietdathang.setDondathang(dondathang);

        // Lưu chi tiết đặt hàng vào cơ sở dữ liệu
        chitietdathangRepository.save(chitietdathang);

        // Tạo phiếu lưu kho
        PHIEULUUKHO phieuluukho = new PHIEULUUKHO();
        phieuluukho.setNgayTaoPhieu(new Date());
        phieuluukho.setChitietdathang(chitietdathang);

        // Lưu phiếu lưu kho vào cơ sở dữ liệu
        phieuluukho = phieuluukhoRepository.save(phieuluukho);

        dondathangRepository.save(dondathang);

        // Tạo lưu kho
        LUUKHO luukho = new LUUKHO();
        luukho.setPhieuluukho(phieuluukho);
        luukho.setKho(kho);

        // Lưu vào kho
        luukhoRepository.save(luukho);

        // Cập nhật số lượng tồn kho
        kho.setSoLuongTon(kho.getSoLuongTon() + soLuong);
        khoRepository.save(kho);
    }
}
