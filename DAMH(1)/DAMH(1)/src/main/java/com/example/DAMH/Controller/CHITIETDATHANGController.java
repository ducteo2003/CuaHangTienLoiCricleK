package com.example.DAMH.Controller;

import com.example.DAMH.model.CHITIETDATHANG;
import com.example.DAMH.Service.CHITIETDATHANGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CHITIETDATHANGController {

    @Autowired
    private CHITIETDATHANGService chitietdathangService;

    @GetMapping("/chitietdathang")
    public String viewChiTietDatHang(Model model) {
        List<CHITIETDATHANG> chitietdathangList = chitietdathangService.getAllChiTietDatHang();
        model.addAttribute("chitietdathangList", chitietdathangList);
        return "chitietdathang/chitietdathanglist";
    }
}
