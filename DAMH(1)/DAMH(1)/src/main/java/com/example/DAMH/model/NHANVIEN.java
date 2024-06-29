package com.example.DAMH.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhanvien")
public class NHANVIEN implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNV;

    @Column(name = "tenNV", length = 100)
    private String tenNV;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "namSinh")
    private Long namSinh;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "tenDangNhap", length = 50, unique = true, nullable = false)
    private String tenDangNhap;

    @Column(name = "matKhau", length = 250, nullable = false)
    private String matKhau;

    @Transient
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "nhanvien_chucvu",
            joinColumns = @JoinColumn(name = "nhanvien_id"),
            inverseJoinColumns = @JoinColumn(name = "chucvu_id"))
    private Set<CHUCVU> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getTenChucVu()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public String getUsername() {
        return tenDangNhap;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
