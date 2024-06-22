package com.example.DAMH.Controller;

import com.example.DAMH.Service.KhoService;
import com.example.DAMH.Service.OrderService;
import com.example.DAMH.Service.SANPHAMService;
import com.example.DAMH.model.KHO;
import com.example.DAMH.model.SANPHAM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private SANPHAMService sanphamService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private KhoService khoService;

    @GetMapping("/list")
    public String listProducts(Model model) {
        List<SANPHAM> products = sanphamService.findAll();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/order/{barcode}")
    public String showOrderForm(@PathVariable int barcode, Model model) {
        SANPHAM product = sanphamService.findById(barcode).orElse(null);
        List<KHO> khos = khoService.getAllKhos();
        model.addAttribute("khos", khos);
        model.addAttribute("product", product);
        return "product/order";
    }

    @PostMapping("/order")
    public String placeOrder(@RequestParam int barcode, @RequestParam int soLuong, @RequestParam String diaChi, @RequestParam int maKho, RedirectAttributes redirectAttributes) {
        SANPHAM product = sanphamService.findById(barcode).orElse(null);
        if (product != null) {
            orderService.placeOrder(product, soLuong, diaChi, maKho);
            redirectAttributes.addFlashAttribute("message", "Đặt hàng thành công");
        } else {
            redirectAttributes.addFlashAttribute("message", "Không tìm thấy sản phẩm");
        }
        return "redirect:/product/list";
    }
}
