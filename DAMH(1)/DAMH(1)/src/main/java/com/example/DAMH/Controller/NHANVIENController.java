package com.example.DAMH.Controller;

import com.example.DAMH.Service.NHANVIENService;
import com.example.DAMH.model.NHANVIEN;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class NHANVIENController {
    private final NHANVIENService nhanVienService;

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("nhanVien", new NHANVIEN());
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("nhanVien") NHANVIEN nhanVien,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "users/register";
        }

        if (!nhanVien.getMatKhau().equals(nhanVien.getConfirmPassword())) {
            model.addAttribute("passwordMismatchError", "Mật khẩu không khớp");
            return "users/register";
        }

        nhanVienService.save(nhanVien);
        nhanVienService.setDefaultRole(nhanVien.getTenDangNhap());
        return "redirect:/login";
    }
}
