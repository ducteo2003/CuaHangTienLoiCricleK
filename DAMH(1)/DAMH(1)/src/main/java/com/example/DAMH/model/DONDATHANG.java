package com.example.DAMH.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dondathang")
public class DONDATHANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDon;
    private Date ngayTao;
    private String ghiChu;

    @OneToMany(mappedBy = "dondathang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CHITIETDATHANG> chitietdathangs = new ArrayList<>();

    @OneToMany(mappedBy = "dondathang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PHIEULUUKHO> phieuluukhos = new ArrayList<>(); // Đảm bảo PHIEULUUKHO là một @Entity
}
