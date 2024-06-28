package com.example.DAMH.controller;

import com.example.DAMH.model.BINHLUAN;
import com.example.DAMH.model.CHITIETHOADON;
import com.example.DAMH.model.HOADON;
import com.example.DAMH.service.BINHLUANService;
import com.example.DAMH.service.HOADONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/nhanvien")
public class InvoiceController {

    @Autowired
    private HOADONService hoadonService;

    @Autowired
    private BINHLUANService binhLuanService;

    @PostMapping("/checkout")
    @Transactional
    @ResponseBody
    public HOADON checkout(@RequestParam("barcode") List<Integer> barcodes, @RequestParam("quantity") List<Integer> quantities, @RequestParam("price") List<Double> prices) {
        HOADON hoadon = hoadonService.createInvoice(barcodes, quantities, prices);
        return hoadon;
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
        hoadonService.updateWarehouseQuantity(hoadon);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Payment successful!");
        return response;
    }

    @PostMapping("/comment")
    @ResponseBody
    public Map<String, String> submitComment(@RequestParam("invoiceId") int invoiceId, @RequestParam("content") String content) {
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

}