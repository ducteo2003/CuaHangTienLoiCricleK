package com.example.DAMH.Service;

import com.example.DAMH.model.*;
import com.example.DAMH.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class SANPHAMServiceEp {

    @Autowired
    private HOADONRepository hoadonRepository;
    @Autowired
    private CHITIETHOADONRepository chitiethoadonRepository;
    @Autowired
    private KHORepository khoRepository;
    @Autowired
    private SANPHAMRepository sanPhamRepository;
    @Autowired
    private LOAISPRepository loaiSPRepository;

    private List<SANPHAM> selectedProducts = new ArrayList<>();

    public List<SANPHAM> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    public List<LOAISP> getAllLoaiSP() {
        return loaiSPRepository.findAll();
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

    public void addProductToSelection(SANPHAM product) {
        selectedProducts.add(product);
    }

    public List<SANPHAM> getSelectedProducts() {
        return selectedProducts;
    }

    /*public int getQuantityByBarcode(int barcode) {
        Optional<KHO> khoOptional = Optional.ofNullable(khoRepository.findById(barcode)); // Sửa đổi phương thức để tìm kiếm theo id
        return khoOptional.map(KHO::getSoLuongTon).orElse(0);
    }

    public boolean processPayment(List<Map<String, Object>> items, String paymentType) {
        try {
            // Tạo hóa đơn mới
            HOADON hoaDon = new HOADON();
            hoaDon.setTenKH("Khách lẻ");
            hoaDon.setNgayLap(new Date());
            hoaDon.setPaymentType(paymentType); // Add payment type to the invoice
            hoaDon = hoadonRepository.save(hoaDon);

            for (Map<String, Object> item : items) {
                int barcode = Integer.parseInt((String) item.get("barcode"));
                int quantity = Integer.parseInt((String) item.get("quantity"));

                // Tìm sản phẩm theo barcode
                List<SANPHAM> sanPhams = sanPhamRepository.findByBarcode(barcode);
                if (sanPhams.isEmpty()) {
                    throw new RuntimeException("Sản phẩm không tồn tại với barcode: " + barcode);
                }
                SANPHAM sanPham = sanPhams.get(0);

                // Lấy số lượng tồn trong kho
                int soLuongTon = getQuantityByBarcode(barcode);
                if (soLuongTon < quantity) {
                    throw new RuntimeException("Không đủ số lượng sản phẩm trong kho");
                }

                // Cập nhật số lượng tồn trong kho
                Optional<KHO> khoOptional = Optional.ofNullable(khoRepository.findById(barcode));
                if (khoOptional.isPresent()) {
                    KHO kho = khoOptional.get();
                    kho.setSoLuongTon(soLuongTon - quantity);
                    khoRepository.save(kho);
                }

                double donGia = sanPham.getDonGia();
                double giaMua = donGia * 0.35;
                double thanhTien = giaMua * quantity;

                // Tạo chi tiết hóa đơn
                CHITIETHOADON chiTietHoaDon = new CHITIETHOADON();
                chiTietHoaDon.setHoadon(hoaDon);
                chiTietHoaDon.setSanpham(sanPham);
                chiTietHoaDon.setSoLuongMua(quantity);
                chiTietHoaDon.setGiaMua(giaMua);
                chiTietHoaDon.setTongHoaDon(thanhTien);
                chitiethoadonRepository.save(chiTietHoaDon);
            }

            return true; // Payment processed successfully
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Payment failed
        }
    }*/



}
