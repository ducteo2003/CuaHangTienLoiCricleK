package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chitiethoadon")
public class CHITIETHOADON {
    private int soLuongMua;
    private double giaMua; //donGia*35%
    private double tongHoaDon;

    @EmbeddedId
    @ManyToOne
    @JoinColumn (name ="barcode")
    private SANPHAM sanpham;

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name ="maHD")
    private HOADON hoadon;
}
