package com.example.DAMH.controllerAdmin;

import com.example.DAMH.model.LOAISP;
import com.example.DAMH.serviceAdmin.loaisanphamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("loaisanpham")
public class loaispController {
    private loaisanphamService loaiService;
    public loaispController(loaisanphamService loaisanphamService)
    {
         loaiService=loaisanphamService;
    }
    @GetMapping("")
    public String Show(Model model)
    {
        model.addAttribute("loais",loaiService.Getall());
        return "loaispAdmin/loaisp_list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id)
    {
        loaiService.DeleteLSP(id);
        return "redirect:/loaisanpham";
    }
    @PostMapping("/add")
    public String addHinhthuc(@RequestParam("noidung")String noidung)
    {
        LOAISP loaisp = new LOAISP();
        loaisp.setTenLoai(noidung);
        loaiService.AddLSP(loaisp);
        return "redirect:/loaisanpham";
    }
    @GetMapping("/detail/{id}")
    public String detailLoaisp(@PathVariable int id, Model model) {
        model.addAttribute("SPS", loaiService.getallSPinLoaisp(id));
        model.addAttribute("LSP", loaiService.findByID(id));
        return "loaispAdmin/loaisp_detail";
    }

}
