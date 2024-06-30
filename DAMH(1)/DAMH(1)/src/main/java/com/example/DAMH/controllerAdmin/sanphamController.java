package com.example.DAMH.controllerAdmin;

import com.example.DAMH.model.*;
import com.example.DAMH.serviceAdmin.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sanphamAdmin")
public class sanphamController {
    @Autowired
    private sanphamService SPService;
    @Autowired
    private nhacungcapService NCCService;
    @Autowired
    private loaisanphamService LSPService;
    @Autowired
    private khuyenmaiService KMService;
    @Autowired
    private vitriService VTService;

    public sanphamController(sanphamService sanphamService, loaisanphamService loaisanphamService,
                             nhacungcapService nhacungcapService, khuyenmaiService khuyenmaiService) {
        SPService = sanphamService;
        LSPService = loaisanphamService;
        NCCService = nhacungcapService;
        KMService = khuyenmaiService;
    }
    @GetMapping
    public String ShowSanpham(Model model)
    {
        model.addAttribute("sanphams", SPService.GetallSanpham());
        return "sanphamAdmin/sanpham_list";
    }
    @GetMapping("/add")
    public String ShowAddSanpham(Model model)
    {
        SANPHAM sanpham = new SANPHAM();
        List<LOAISP> loaispList = LSPService.Getall();
        List<NHACUNGCAP> NCCList = NCCService.Getall();
        List<KHUYENMAI> KMList = KMService.GetallAction();
        List<VITRI> VTList = VTService.Getall();

        model.addAttribute("sanpham", sanpham);
        model.addAttribute("NCCs", NCCList);
        model.addAttribute("LSPs", loaispList);
        model.addAttribute("KMs", KMList);
        model.addAttribute("VTs", VTList);
        return "sanphamAdmin/sanpham_add";
    }
    @PostMapping("/save_add")
    public String AddSanpham(@Valid SANPHAM sanpham)
    {
        SPService.AddSappham(sanpham);
        return "redirect:/sanphamAdmin";
    }
    @GetMapping("/edit/{id}")
    public String ShowUpdateSanpham(@PathVariable int id, Model model)
    {
        model.addAttribute("sanpham", SPService.SanphamDetail(id));
        List<LOAISP> loaispList = LSPService.Getall();
        List<NHACUNGCAP> NCCList = NCCService.Getall();
        List<KHUYENMAI> KMList = KMService.GetallAction();
        List<VITRI> VTList = VTService.Getall();
        model.addAttribute("NCCs", NCCList);
        model.addAttribute("LSPs", loaispList);
        model.addAttribute("KMs", KMList);
        model.addAttribute("VTs", VTList);
        return ("sanphamAdmin/sanpham_edit");
    }
    @PostMapping("/save_update")
    public String ShowUpdateSanpham(@Valid SANPHAM sanpham, Model model)
    {

        SPService.UpdateSanpham(sanpham);
        return "redirect:/sanphamAdmin";
    }
    @GetMapping("/delete/{id}")
    public String DeleteSanpham(@PathVariable int id)
    {
        SPService.DeleteSanpham(id);
        return "redirect:/sanphamAdmin";
    }
    @GetMapping("/search")
    public String SearchSanpham(@RequestParam("text") String text, Model model)
    {
        model.addAttribute("sanphams", SPService.FindSanphamByName(text));
        return "sanphamAdmin/sanpham_list";
    }

}
