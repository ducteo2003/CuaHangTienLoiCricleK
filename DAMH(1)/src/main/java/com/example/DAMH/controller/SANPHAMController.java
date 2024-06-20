package com.example.DAMH.controller;

import com.example.DAMH.model.LOAISP;
import com.example.DAMH.model.SANPHAM;
import com.example.DAMH.service.SANPHAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/nhanvien")
public class SANPHAMController {
    @Autowired
     private SANPHAMService sanphamService;

    public SANPHAMController(SANPHAMService sanphamService){
        this.sanphamService=sanphamService;
    }

    @GetMapping
    public String showSanPhamList(@RequestParam(value = "categoryId", required = false) Integer categoryId, Model model) {
        List<SANPHAM> sanphamList;
        if (categoryId != null) {
            sanphamList = sanphamService.findSANPHAMByLOAISP(categoryId);
        } else {
            sanphamList = sanphamService.getAllSanPham();
        }
        model.addAttribute("sanphams", sanphamList);
        List<LOAISP> loaispList = sanphamService.getAllLoaiSP();
        model.addAttribute("loaisps", loaispList);
        return "nhanvien/sanpham-list"; // Trả về tên mẫu Thymeleaf
    }
}


