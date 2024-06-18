package com.example.DAMH.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name="maNV")
    private NHANVIEN nhanvien;

    @OneToOne
    @JoinColumn(name="maBinhLuan")
    private BINHLUAN binhluan;
}
