package com.example.DAMH.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chucvu")
public class CHUCVU implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maChucVu;

    @Column(name = "tenChucVu", length = 50, nullable = false)
    private String tenChucVu;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<NHANVIEN> nhanViens = new HashSet<>();

    @Override
    public String getAuthority() {
        return tenChucVu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CHUCVU chucvu = (CHUCVU) o;
        return maChucVu == chucvu.maChucVu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maChucVu);
    }
}