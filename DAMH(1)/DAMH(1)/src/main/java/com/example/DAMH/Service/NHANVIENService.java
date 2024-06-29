package com.example.DAMH.Service;

import com.example.DAMH.model.CHUCVU;
import com.example.DAMH.model.NHANVIEN;
import com.example.DAMH.repository.CHUCVURepository;
import com.example.DAMH.repository.NHANVIENRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class NHANVIENService implements UserDetailsService {
    @Autowired
    private NHANVIENRepository userRepository;
    @Autowired
    private CHUCVURepository roleRepository;

    // Lưu người dùng mới vào cơ sở dữ liệu sau khi mã hóa mật khẩu.
    public void save(@NotNull NHANVIEN user) {
        user.setMatKhau(new BCryptPasswordEncoder().encode(user.getMatKhau()));
        userRepository.save(user);
    }

    // Gán vai trò mặc định cho người dùng dựa trên tên người dùng.
    public void setDefaultRole(String tenDangNhap) {
        userRepository.findByTenDangNhap(tenDangNhap).ifPresentOrElse(
                user -> {
                    CHUCVU role = roleRepository.findRoleByTenChucVu("USER");
                    if (role != null) {
                        user.getRoles().add(role);
                        userRepository.save(user);
                    }
                },
                () -> { throw new UsernameNotFoundException("User not found"); }
        );
    }

    // Tải thông tin chi tiết người dùng để xác thực.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NHANVIEN user = userRepository.findByTenDangNhap(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getTenDangNhap())
                .password(user.getMatKhau())
                .authorities(user.getAuthorities())
                .accountExpired(!user.isAccountNonExpired())
                .accountLocked(!user.isAccountNonLocked())
                .credentialsExpired(!user.isCredentialsNonExpired())
                .disabled(!user.isEnabled())
                .build();
    }

    // Tìm kiếm người dùng dựa trên tên đăng nhập.
    public Optional<NHANVIEN> findByTenDangNhap(String tenDangNhap) throws UsernameNotFoundException {
        return userRepository.findByTenDangNhap(tenDangNhap);
    }
}
