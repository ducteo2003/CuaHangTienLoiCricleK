package com.example.DAMH.Controller;

import com.example.DAMH.Service.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ThongKeController {

    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/thongkeTuan")
    public String getWeeklySales(Model model) {
        List<Object[]> results = thongKeService.getWeeklySales();
        model.addAttribute("results", results);
        model.addAttribute("mode", "weekly");
        return "thongke/thongke";
    }

    @GetMapping("/thongkeThang")
    public String getMonthlySales(Model model) {
        List<Object[]> results = thongKeService.getMonthlySales();
        model.addAttribute("results", results);
        model.addAttribute("mode", "monthly");
        return "thongke/thongke";
    }
}
