package com.example.DAMH.Controller;

import com.example.DAMH.Service.KhoService;
import com.example.DAMH.Service.OrderService;
import com.example.DAMH.Service.SANPHAMService;
import com.example.DAMH.model.KHO;
import com.example.DAMH.model.ProductOrderDTO;
import com.example.DAMH.model.ProductOrderForm;
import com.example.DAMH.model.SANPHAM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/order")
    public String showOrderForm(@RequestParam(value = "productBarcodes", required = false) List<Integer> productBarcodes, Model model) {
        if (productBarcodes == null || productBarcodes.isEmpty()) {
            model.addAttribute("error", "Không có sản phẩm nào được chọn.");
            return "redirect:/product/list";
        }
        List<SANPHAM> selectedProducts = new ArrayList<>();
        for (int barcode : productBarcodes) {
            sanphamService.findByBarcode(barcode).ifPresent(selectedProducts::add);
        }
        List<KHO> khos = khoService.getAllKhos();
        model.addAttribute("khos", khos);
        model.addAttribute("products", selectedProducts);
        model.addAttribute("productOrderForm", new ProductOrderForm());
        return "product/order";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@ModelAttribute ProductOrderForm productOrderForm, RedirectAttributes redirectAttributes) {
        List<ProductOrderDTO> productOrders = productOrderForm.getProducts();
        List<SANPHAM> sanphams = new ArrayList<>();
        List<Integer> soLuongs = new ArrayList<>();

        for (ProductOrderDTO productOrder : productOrders) {
            if (productOrder.getBarcode() != 0) {
                Optional<SANPHAM> product = sanphamService.findByBarcode(productOrder.getBarcode());
                if (product.isPresent()) {
                    sanphams.add(product.get());
                    soLuongs.add(productOrder.getSoLuong());
                }
            }
        }

        if (!sanphams.isEmpty()) {
            try {
                int maKho = Integer.parseInt(productOrderForm.getMaKho()); // Chuyển đổi String sang int
                orderService.placeOrder(sanphams, soLuongs, productOrderForm.getDiaChi(), maKho);
                redirectAttributes.addFlashAttribute("message", "Đặt hàng thành công");
            } catch (NumberFormatException e) {
                redirectAttributes.addFlashAttribute("error", "Mã kho không hợp lệ: " + e.getMessage());
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi đặt hàng: " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Không có sản phẩm nào được đặt hàng");
        }
        return "redirect:/product/list";
    }
}
