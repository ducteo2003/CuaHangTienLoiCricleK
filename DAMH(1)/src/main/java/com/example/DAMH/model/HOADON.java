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
    private String tenKH; //có thể để mặc định là khách lẻ
    private Date ngayLap;
    private String paymentType;
    @ManyToOne
    @JoinColumn(name="maNV")
    private NHANVIEN nhanvien;

    @OneToOne
    @JoinColumn(name="maBinhLuan")
    private BINHLUAN binhluan;

    @OneToMany(mappedBy = "hoadon")
    private List<BINHLUAN> binhLuans;

    @OneToMany(mappedBy = "hoadon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CHITIETHOADON> chitiethoadons;


}
