package com.example.DAMH.controllerAdmin;

import com.example.DAMH.model.HINHTHUC;
import com.example.DAMH.model.KHUYENMAI;
import com.example.DAMH.model.SANPHAM;
import com.example.DAMH.serviceAdmin.hinhthucService;
import com.example.DAMH.serviceAdmin.khuyenmaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/khuyenmai")
public class khuyenmaiController {

    @Autowired
    private khuyenmaiService khuyenmaiService;
    @Autowired
    private hinhthucService HTService;

    public khuyenmaiController(khuyenmaiService km, hinhthucService hinhthucService)
    {
            khuyenmaiService = km;
            HTService = hinhthucService;
    }
    @GetMapping("")
    public String getAllKhuyenmai(Model model) {
        List<KHUYENMAI> khuyenmais = khuyenmaiService.Getall();
        model.addAttribute("khuyenmais", khuyenmais);
        return "khuyenmaiAdmin/khuyenmai_list";
    }

    @GetMapping("/addForm")
    public String addKhuyenmaiForm(Model model ) {
        KHUYENMAI khuyenmai = new KHUYENMAI();
        model.addAttribute("khuyenmai", khuyenmai);
        List<HINHTHUC> ht = HTService.Getall();
        model.addAttribute("hinhthucs", ht);
        return "khuyenmaiAdmin/khuyenmai_add";
    }

    @PostMapping("/save_add")
    public String saveAddKhuyenmai(@ModelAttribute("khuyenmai") KHUYENMAI khuyenmai,
                                   @RequestParam("dateBatDau") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date dateBatDau,
                                   @RequestParam("dateKetThuc") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date dateKetThuc) {

        // Set ngày bắt đầu và ngày kết thúc cho đối tượng KHUYENMAI
        khuyenmai.setNgayBatDau(dateBatDau);
        khuyenmai.setNgayKetThuc(dateKetThuc);

        // Tính khoảng thời gian giữa ngày bắt đầu và ngày kết thúc
        Duration duration = Duration.between(dateBatDau.toInstant(), dateKetThuc.toInstant());
        long days = duration.toDays(); // Lấy số ngày

        // Lưu vào đối tượng KHUYENMAI
        khuyenmai.setThoiHan((int) days + 1);


        // Process save logic if no validation errors
        khuyenmaiService.AddKhuyenmai(khuyenmai);
        return "redirect:/khuyenmai";
    }



    @GetMapping("/delete/{maKM}")
    public String deleteKhuyenmai(@PathVariable("maKM") int maKM) {
        khuyenmaiService.DeleteKhuyenmai(maKM);
        return "redirect:/khuyenmai";
    }

    @GetMapping("/detail/{maKM}")
    public String detailKhuyenmai(@PathVariable("maKM") int maKM, Model model) {
        KHUYENMAI km =  khuyenmaiService.findbyId(maKM).orElseThrow();
        List<SANPHAM> sps = khuyenmaiService.getAllSPinKM(maKM);

        model.addAttribute("km", km);
        model.addAttribute("sps",sps);

        return "khuyenmaiAdmin/khuyenmai_detail";
    }
}
