package com.example.DAMH.controllerAdmin;

import com.example.DAMH.model.NHACUNGCAP;
import com.example.DAMH.model.SANPHAM;
import com.example.DAMH.serviceAdmin.nhacungcapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/NCC")
public class nhacungcapController {

    @Autowired
    private nhacungcapService NCCService;

    @GetMapping()
    public String ShowallNCC(Model model) {
        model.addAttribute("NCCs", NCCService.Getall());
        return "nhacungcapAdmin/nhacungcap_list";
    }

    @GetMapping("/delete/{id}")
    public String deleteNCC(@PathVariable int id) {
        NCCService.deleteNCC(id);
        return "redirect:/NCC";
    }


    // Hiển thị form sửa nhà cung cấp
    @GetMapping("/editForm/{maNCC}")
    public String editFormNCC(@PathVariable("maNCC") int maNCC, Model model) {
        Optional<NHACUNGCAP> NCC = NCCService.GetbyId(maNCC);
        model.addAttribute("NCC", NCC.orElse(new NHACUNGCAP())); // Tránh NullPointerException
        return "nhacungcapAdmin/nhacungcap_edit";
    }

    // Lưu thông tin sau khi sửa nhà cung cấp
    @PostMapping("/update")
    public String updateNCC(@ModelAttribute("NCC") NHACUNGCAP NCC) {
        NCCService.editNCC(NCC);
        return "redirect:/NCC";
    }

    // Hiển thị form thêm mới nhà cung cấp
    @GetMapping("/addForm")
    public String addFormNCC(Model model) {
        model.addAttribute("NCC", new NHACUNGCAP());
        return "nhacungcapAdmin/nhacungcap_add";
    }

    // Lưu thông tin sau khi thêm mới nhà cung cấp
    @PostMapping("/save_add")
    public String saveAddNCC(@ModelAttribute("NCC") NHACUNGCAP NCC) {
        NCCService.addNewNCC(NCC);
        return "redirect:/NCC";
    }
    @GetMapping("/detail/{maNCC}")
    public String detailNCC(@PathVariable("maNCC") int maNCC, Model model) {
        Optional<NHACUNGCAP> NCC = NCCService.GetbyId(maNCC);
        model.addAttribute("NCC", NCC.orElse(new NHACUNGCAP())); // Tránh NullPointerException

        List<SANPHAM> Sp = NCCService.finfSpByid(maNCC);
        model.addAttribute("SP", Sp); // Tránh NullPointerException
        return "nhacungcapAdmin/nhacungcap_detail";
    }
}
