package com.example.DAMH.Controller;

import com.example.DAMH.model.BINHLUAN;
import com.example.DAMH.model.CHITIETHOADON;
import com.example.DAMH.model.HOADON;
import com.example.DAMH.model.NHANVIEN;
import com.example.DAMH.Service.BINHLUANService;
import com.example.DAMH.Service.HOADONService;
import com.example.DAMH.repository.NHANVIENRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/nhanvien")
public class InvoiceController {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private HOADONService hoadonService;

    @Autowired
    private BINHLUANService binhLuanService;

    @Autowired
    private NHANVIENRepository nhanvienRepository;

    @PostMapping("/checkout")
    @Transactional
    @ResponseBody
    public Map<String, Object> checkout(@RequestParam("barcodes") List<Integer> barcodes,
                                        @RequestParam("quantities") List<Integer> quantities,
                                        @RequestParam("prices") List<Double> prices) {
        if (barcodes.size() != quantities.size() || barcodes.size() != prices.size()) {
            throw new IllegalArgumentException("Mismatched input list sizes.");
        }
        for (int i = 0; i < barcodes.size(); i++) {
            if (barcodes.get(i) == null || quantities.get(i) == null || prices.get(i) == null) {
                throw new IllegalArgumentException("Invalid input data at index " + i);
            }
        }
        NHANVIEN nhanvien = getCurrentNhanVien();
        HOADON hoadon = hoadonService.createInvoice(barcodes, quantities, prices, nhanvien);
        Map<String, Object> response = new HashMap<>();
        response.put("maHD", hoadon.getMaHD());
        return response;
    }


    @GetMapping("/payment-method")
    public String selectPaymentMethod(@RequestParam("invoiceId") int invoiceId, Model model) {
        HOADON hoadon = hoadonService.findById(invoiceId);
        double totalAmount = hoadon.getChitiethoadons().stream().mapToDouble(CHITIETHOADON::getTongHoaDon).sum();

        model.addAttribute("invoiceId", hoadon.getMaHD());
        model.addAttribute("customerName", hoadon.getTenKH());
        model.addAttribute("orderDate", hoadon.getNgayLap());
        model.addAttribute("totalAmount", totalAmount);
        return "nhanvien/paymentMethod";
    }

    @GetMapping("/generate-payment-url")
    @ResponseBody
    public String generatePaymentUrl(@RequestParam("totalAmount") long totalAmount) throws UnsupportedEncodingException {
        // Call PaymentController to generate payment URL
        return new PaymentController().getPay(totalAmount);
    }

    @PostMapping("/complete-payment")
    @Transactional
    @ResponseBody
    public Map<String, String> completePaymentProcess(@RequestParam("invoiceId") int invoiceId,
                                                      @RequestParam("paymentType") String paymentType) {
        HOADON hoadon = hoadonService.findById(invoiceId);
        hoadon.setPaymentType(paymentType);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Payment successful!");
        return response;
    }

    @PostMapping("/comment")
    @ResponseBody
    public Map<String, String> submitComment(@RequestParam("invoiceId") int invoiceId,
                                             @RequestParam("content") String content) {
        BINHLUAN comment = binhLuanService.saveComment(invoiceId, content);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Comment submitted successfully!");
        return response;
    }

    @GetMapping("/vnpay_return")
    public String vnpayReturn(@RequestParam Map<String, String> params, Model model) {
        String vnp_ResponseCode = params.get("vnp_ResponseCode");
        System.out.println("VNPay Response Code: " + vnp_ResponseCode);
        if ("00".equals(vnp_ResponseCode)) {
            model.addAttribute("message", "Thanh toán thành công!");
        } else {
            model.addAttribute("message", "Thanh toán thất bại. Vui lòng thử lại.");
        }
        return "nhanvien/paymentResult";
    }

    private NHANVIEN getCurrentNhanVien() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return nhanvienRepository.findByTenDangNhap(username)
                    .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));
        } else {
            throw new RuntimeException("Nhân viên không tồn tại");
        }
    }
}
