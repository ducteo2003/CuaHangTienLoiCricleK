package com.example.DAMH.service;

import com.example.DAMH.model.*;
import com.example.DAMH.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class HOADONService {

    @Autowired
    private SANPHAMRepository sanphamRepository;

    @Autowired
    private HOADONRepository hoadonRepository;

    @Autowired
    private CHITIETHOADONRepository chitiethoadonRepository;

    @Autowired
    private KHORepository khoRepository;

    @Autowired
    private LUUKHORepository luukhoRepository;

    @Autowired
    private PHIEULUUKHORepository phieuluukhoRepository;

    @Autowired
    private CHITIETDATHANGRepository chitietdathangRepository;

    @Autowired
    private DONDATHANGRepository dondathangRepository;

    @Transactional
    public HOADON createInvoice(List<Integer> barcodes, List<Integer> quantities, List<Double> prices) {
        // Create a new invoice
        HOADON hoadon = new HOADON();
        hoadon.setTenKH("Khách lẻ");
        hoadon.setNgayLap(new java.util.Date());
        hoadon.setPaymentType("Chưa thanh toán");
        hoadon = hoadonRepository.save(hoadon);

        // Create and save invoice details
        for (int i = 0; i < barcodes.size(); i++) {
            SANPHAM sanpham = sanphamRepository.findById(barcodes.get(i)).orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

            CHITIETHOADON chitietHoaDon = new CHITIETHOADON();
            chitietHoaDon.setHoadon(hoadon);
            chitietHoaDon.setSanpham(sanpham);
            chitietHoaDon.setSoLuongMua(quantities.get(i));
            chitietHoaDon.setGiaMua(prices.get(i));
            chitietHoaDon.setTongHoaDon(prices.get(i) * quantities.get(i));
            chitiethoadonRepository.save(chitietHoaDon);
        }

        return hoadon;
    }

    @Transactional
    public void updateWarehouseQuantity(HOADON hoadon) {
        for (CHITIETHOADON detail : hoadon.getChitiethoadons()) {
            List<CHITIETDATHANG> chitietdathangs = chitietdathangRepository.findBySanpham(detail.getSanpham());
            for (CHITIETDATHANG chitietdathang : chitietdathangs) {
                PHIEULUUKHO phieuluukho = phieuluukhoRepository.findByChitietdathang(chitietdathang);
                if (phieuluukho != null) {
                    List<LUUKHO> luukhos = luukhoRepository.findByPhieuluukho(phieuluukho);
                    for (LUUKHO luukho : luukhos) {
                        KHO kho = luukho.getKho();
                        if (kho != null) {
                            kho.setSoLuongTon(kho.getSoLuongTon() - detail.getSoLuongMua());
                            khoRepository.save(kho);
                        }
                    }
                }
            }
        }
    }

    public HOADON findById(int invoiceId) {
        return hoadonRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
    }
}