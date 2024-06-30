package com.example.DAMH.controllerAdmin;

import com.example.DAMH.Service.NHANVIENService;
import com.example.DAMH.model.CHUCVU;
import com.example.DAMH.model.NHANVIEN;
import com.example.DAMH.serviceAdmin.chucvuService;
import com.example.DAMH.serviceAdmin.nhanvienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/nhanvienAdmin")
public class nhanvienController {
    @Autowired
    private nhanvienService nvservice;
    @Autowired
    private chucvuService cvservice;

    @Autowired
    private NHANVIENService NVservice;

    public nhanvienController(nhanvienService nhanvienService, chucvuService chucvuService, NHANVIENService nVService) {
        this.nvservice = nhanvienService;
        this.cvservice = chucvuService;
        NVservice = nVService;
    }

    @GetMapping
    public String ShowNhanvien(Model model) {
        model.addAttribute("nhanviens", nvservice.GetallNhanvien());
        return "/nhanvienAdmin/nhanvien_list";
    }
    @GetMapping("/add")
    public String ShowFormAddNhanvien(Model model) {
        NHANVIEN nhanVien = new NHANVIEN(); // Khởi tạo một đối tượng NHANVIEN mới
        List<CHUCVU> chucvus = cvservice.GetallChucvu();
        model.addAttribute("nhanVien", nhanVien); // Truyền đối tượng nhân viên vào model
        model.addAttribute("chucvus", chucvus);
        return "nhanvienAdmin/nhanvien_add";
    }
    @PostMapping("/save")
    public String saveNhanvien(@Valid NHANVIEN nhanVien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Nếu có lỗi, hiển thị lại form với các lỗi
            List<CHUCVU> chucvus = cvservice.GetallChucvu();
            model.addAttribute("chucvus", chucvus);
            return "nhanvienAdmin/nhanvien_add";
        }

        // Xử lý lưu nhân viên khi không có lỗi
        try {
            NVservice.save(nhanVien);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Sau khi lưu thành công, chuyển hướng về trang danh sách nhân viên
        return "redirect:/nhanvienAdmin";
    }


    @GetMapping("/delete/{id}")
    public String DeleteNhanvien(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            nvservice.DeleteNhanvien(id);
            redirectAttributes.addFlashAttribute("message", "Xóa nhân viên thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi xóa nhân viên");
            // Log exception if needed
            e.printStackTrace(); // Log the exception for debugging
        }
        return "redirect:/nhanvienAdmin";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        NHANVIEN nv = nvservice.findNhanvien(id);
        model.addAttribute("nhanvien", nv);
        List<CHUCVU> cvs = cvservice.GetallChucvu();
        model.addAttribute("chucvus", cvservice.GetallChucvu());
        return "nhanvienAdmin/nhanvien_edit"; // Không có dấu gạch chéo ở đầu
    }

    @PostMapping("/update/{id}")
    public String updateNhanvien(@PathVariable int id, @Valid NHANVIEN nv, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<CHUCVU> cvs = cvservice.GetallChucvu();
            model.addAttribute("chucvus", cvservice.GetallChucvu());
            nv.setMaNV(id);  // Ensuring the ID is retained on error
            return "nhanvienAdmin/nhanvien_edit"; // Không có dấu gạch chéo ở đầu
        }

        try {
            nvservice.editNhanvien(nv);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/nhanvienAdmin";
    }


    @GetMapping("/search")
    public String Search(@RequestParam("text") String text, Model model) {

        List<NHANVIEN> NV = nvservice.findListNhanvien(text);
        model.addAttribute("nhanviens", NV);
        return "nhanvienAdmin/nhanvien_list"; // Không có dấu gạch chéo ở đầu
    }

}
