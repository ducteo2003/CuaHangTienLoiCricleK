package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chitiethoadon")
public class CHITIETHOADON {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int maCTHD;
    private int soLuongMua;
    private double giaMua; //donGia*35%
    private double tongHoaDon;


    @ManyToOne
    @JoinColumn (name ="barcode")
    private SANPHAM sanpham;


    @ManyToOne
    @JoinColumn(name ="maHD")
    private HOADON hoadon;
}
