package com.example.DAMH.controllerAdmin;

import com.example.DAMH.Service.SANPHAMService;
import com.example.DAMH.model.BINHLUAN;
import com.example.DAMH.model.HOADON;
import com.example.DAMH.serviceAdmin.binhluanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/binhluan")
public class binhluanController {
    @Autowired
    private binhluanService BLService;

    @Autowired
    private SANPHAMService SPService;

    public binhluanController(binhluanService binhluanService, SANPHAMService sanphamService)
    {
        BLService = binhluanService;
        SPService = sanphamService;
    }

    @GetMapping("")
    public String showall(Model model)
    {
        model.addAttribute("binhluans", BLService.getall());
        return "binhluanAdmin/binhluan_list";
    }
    @GetMapping("/detail/{id}")
    public String detailBinhluan(@PathVariable int id, Model model)
    {
        BINHLUAN BL = BLService.findbyId(id);
        if(BL.getHoadon() != null)
        {
            HOADON hd = BLService.hoadonList(BL.getHoadon().getMaHD());
            model.addAttribute("hd", hd);
        }

        model.addAttribute("bl", BL);

        return "binhluanAdmin/binhluan_detail";
    }
    @PostMapping("/edit/{id}")
    public String EditTrangthaiBL(@PathVariable int id)
    {
        BLService.editBL(id);
        return "redirect:/binhluan";
    }
    @GetMapping("search")
    public String Find(@RequestParam("text") String text, Model model)
    {
        model.addAttribute("binhluan", BLService.Find(text));
        return "binhluanAdmin/binhluan_list";
    }
}
