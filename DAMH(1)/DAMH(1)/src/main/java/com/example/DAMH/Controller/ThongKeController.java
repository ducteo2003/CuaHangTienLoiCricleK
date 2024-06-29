package com.example.DAMH.Controller;

import com.example.DAMH.Service.ThongKeService;
import com.example.DAMH.model.BINHLUAN;
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
        List<BINHLUAN> comments = thongKeService.getAllComments();
        model.addAttribute("results", results);
        model.addAttribute("mode", "weekly");
        model.addAttribute("comments", comments);
        return "thongke/thongke";
    }

    @GetMapping("/thongkeThang")
    public String getMonthlySales(Model model) {
        List<Object[]> results = thongKeService.getMonthlySales();
        List<BINHLUAN> comments = thongKeService.getAllComments();
        model.addAttribute("results", results);
        model.addAttribute("mode", "monthly");
        model.addAttribute("comments", comments);
        return "thongke/thongke";
    }
}
