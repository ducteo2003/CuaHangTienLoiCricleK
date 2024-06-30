package com.example.DAMH.Service;


import com.example.DAMH.model.*;
import com.example.DAMH.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

@Service
public class HOADONService {

    private static final Logger logger = LoggerFactory.getLogger(HOADONService.class);

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
    public HOADON createInvoice(List<Integer> barcodes, List<Integer> quantities, List<Double> prices, NHANVIEN nhanvien) {
        logger.debug("Creating invoice with barcodes: {}, quantities: {}, prices: {}", barcodes, quantities, prices);
        try {
            // Create a new invoice
            HOADON hoadon = new HOADON();
            hoadon.setTenKH("Khách lẻ");
            hoadon.setNgayLap(new Date());
            hoadon.setPaymentType("Đã thanh toán");
            hoadon.setNhanvien(nhanvien);
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
        } catch (Exception e) {
            logger.error("Error creating invoice", e);
            throw e;
        }
    }

    public HOADON findById(int invoiceId) {
        return hoadonRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
    }

    public void save(HOADON hoadon) {
        hoadonRepository.save(hoadon);
    }
    public HOADON findByTxnRef(String txnRef) {
        return hoadonRepository.findByTxnRef(txnRef).orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
    }
    public double calculateTotalAmount(HOADON hoadon) {
        return hoadon.getChitiethoadons().stream().mapToDouble(CHITIETHOADON::getTongHoaDon).sum();
    }
}
