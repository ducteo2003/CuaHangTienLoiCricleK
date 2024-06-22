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
    private double giaDat; //giaDat=donGia(SANPHAM)
    private double tongDat; //giaDat*soLuongDat


    @ManyToOne
    @JoinColumn(name="barcode", nullable = false)
    private SANPHAM sanpham;


    @ManyToOne
    @JoinColumn(name="maDon", nullable = false)
    private DONDATHANG dondathang;
}
