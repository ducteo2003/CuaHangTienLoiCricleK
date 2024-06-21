package com.example.DAMH.controller;

import com.example.DAMH.model.LOAISP;
import com.example.DAMH.model.SANPHAM;
import com.example.DAMH.service.SANPHAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showSanPhamList(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                                  @RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "barcode", required = false) Integer barcode, Model model) {
        List<SANPHAM> sanphamList;
        if (categoryId != null) {
            sanphamList = sanphamService.findSANPHAMByLOAISP(categoryId);
        } else if (barcode != null) {
            sanphamList = sanphamService.searchSanPhamByBarcode(barcode);
        } else if (search != null) {
            sanphamList = sanphamService.searchSanPhamByTenSP(search);
        } else {
            sanphamList = sanphamService.getAllSanPham();
        }
        model.addAttribute("sanphams", sanphamList);
        List<LOAISP> loaispList = sanphamService.getAllLoaiSP();
        model.addAttribute("loaisps", loaispList);
        return "/nhanvien/sanpham-list";
    }
    @GetMapping("/search")
    @ResponseBody
    public List<SANPHAM> searchSanPham(@RequestParam(value = "search", required = false) String search,
                                       @RequestParam(value = "barcode", required = false) Integer barcode) {
        if (barcode != null) {
            return sanphamService.searchSanPhamByBarcode(barcode);
        } else if (search != null) {
            return sanphamService.searchSanPhamByTenSP(search);
        } else {
            return sanphamService.getAllSanPham();
        }
    }
}


