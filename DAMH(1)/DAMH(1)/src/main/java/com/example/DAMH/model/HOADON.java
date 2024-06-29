package com.example.DAMH.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoadon")
public class HOADON {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maHD;
    private String tenKH;
    private Date ngayLap;

    @ManyToOne
    @JoinColumn(name="maNV")
    private NHANVIEN nhanvien;

    @OneToMany(mappedBy = "hoadon")
    private List<BINHLUAN> binhluans;
}
