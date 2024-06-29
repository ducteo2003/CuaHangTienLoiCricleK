package com.example.DAMH.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chitietdathang")
public class CHITIETDATHANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maCTDH;
    private int soLuongDat;
    private Date ngayGiaoDuKien;
    private String diaChi;
    private double giaDat;
    private double tongDat;

    @ManyToOne
    @JoinColumn(name="barcode", referencedColumnName = "barcode", nullable = false)
    private SANPHAM sanpham;

    @ManyToOne
    @JoinColumn(name="maDon", referencedColumnName = "maDon", nullable = false)
    private DONDATHANG dondathang;
}