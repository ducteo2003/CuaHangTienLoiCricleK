package com.example.DAMH.controllerAdmin;

import com.example.DAMH.model.HINHTHUC;
import com.example.DAMH.serviceAdmin.hinhthucService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hinhthuc")
public class hinhthucController {

    private hinhthucService htService;
    public hinhthucController(hinhthucService hinhthucService)
    {
        htService = hinhthucService;
    }
    @GetMapping("")
    public String Showall(Model model)
    {
        model.addAttribute("ht", htService.Getall());
        return "hinhthucAdmin/hinhthuc_list";
    }
    @GetMapping("/delete/{id}")
    public String deleteHinhthuc(@PathVariable int id) {
        htService.deleteHinhthuc(id);
        return "redirect:/hinhthuc";
    }
    @PostMapping("/add")
    public String addHinhthuc(@RequestParam("noidung")String noidung)
    {
        HINHTHUC ht = new HINHTHUC();
        ht.setTenHinhThuc(noidung);
        htService.addHinhthuc(ht);
        return "redirect:/hinhthuc";
    }

}